package com.mul.HealthyGYM.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.TokenDto;
import com.mul.HealthyGYM.Service.AuthService;
import com.mul.HealthyGYM.Service.MemberService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	MemberService memberService;
	
	@Value("${Kakao.client.id}")
	private String kakaoId;
	@Value("${Kakao.client.secret}")
	private String kakaoSecret;
	
	@Value("${Google.client.id}")
	private String googleId;
	@Value("${Google.client.secret}")
	private String googleSecret;
	
	// 자체 서비스 회원가입
	@PostMapping("/signup")
	public String signup(MemberDto dto) {
		System.out.println("signup " + dto.toString());
		dto.setProvider("own");
		if(authService.signup(dto)) {
			memberService.regiMemberinfo(dto.getEmail());
			return "OK";
		} else {
			return "NO";
		}
	}
	
	// 토큰 재발급
	@PostMapping("/reissue")
    public TokenDto reissue(@RequestBody TokenDto token) {
		System.out.println("reissue " + new Date());
		return authService.reissue(token);
	}
	
	// 자체 서비스 로그인
	@PostMapping("/login")
    public Map<String, Object> login(@RequestBody MemberDto dto) {
		System.out.println("login " + new Date());
		
		Map<String, Object> map = new HashMap<>();
		
		MemberDto mem = memberService.findByEmail(dto.getEmail());
		if(mem.getAuth() == 2) {
			map.put("token", "deactivated");
			return map;
		}
		map.put("seq", mem.getMemberseq());
		map.put("profile", mem.getProfile());
		map.put("token", authService.login(dto));
		
		return map;
	}

	// 카카오 로그인 및 회원가입
	@GetMapping("/kakao")
	public Map<String, Object> kakao(String code) {
		System.out.println("카카오 " + code);
		
		RestTemplate rt = new RestTemplate();
		Map<String, Object> map = new HashMap<>();
		
		// 1. 프론트에서 넘겨준 code를 이용해서 카카오로부터 Access Token 받기
		// HttpHeader와 HttpBody를 HttpEntity에 담기
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        	params.add("grant_type", "authorization_code");
        	params.add("client_id", kakaoId);
        	params.add("redirect_uri", "http://localhost:9100/login/callback/kakao");
        	params.add("code", code);
        	params.add("client_secret", kakaoSecret);

        HttpEntity<MultiValueMap<String, String>> kakaoRequest1 = new HttpEntity<>(params, headers);

        // HTTP 요청 - POST방식 - response 응답 받기
        ResponseEntity<String> response = rt.exchange(
            "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoRequest1,
            String.class
        );
        JSONObject jsonObject = new JSONObject(response.getBody());
        
        
        // 2. 받아온 Access Token을 이용해서 사용자 정보 받기
        // HttpHeader를 HttpEntity에 담기
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Authorization", "Bearer " + jsonObject.get("access_token"));
        headers1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<HttpHeaders> kakaoRequest2 = new HttpEntity<>(headers1);
        
        // HTTP 요청 - POST방식 - response 응답 받기
        ResponseEntity<String> profileResponse = rt.exchange(
        	"https://kapi.kakao.com/v2/user/me",
        	HttpMethod.POST,
        	kakaoRequest2,
        	String.class
        );
        System.out.println(profileResponse.toString());
        
        JSONObject body = new JSONObject(profileResponse.getBody());
        JSONObject kakao_account = (JSONObject) body.get("kakao_account");
        String email = kakao_account.getString("email");
        String nickname = ((JSONObject)kakao_account.get("profile")).getString("nickname");
        MemberDto mem = new MemberDto(email, "", nickname, "ROLE_USER", "kakao");
        
        // 3. 가입 여부 확인
        if(memberService.existsByEmail(email)) {
        	// 동일한 이메일이 다른 서비스(제공자)로 가입되어있으면 로그인 반려
        	String provider = memberService.checkProvider(email);
        	if(!provider.equals("kakao")) {
        		map.put("provider", provider);
        		System.out.println("다른 서비스(제공자)로 가입되어있음");
        		return map;
        	}
        	// 활동 정지 회원이면 로그인 반려
        	if(memberService.checkAuth(email) == 2) {
        		map.put("provider", "deactivated");
        		System.out.println("활동 정지 회원");
        		return map;
        	}
        }
        // 미가입 이메일이면 회원가입
        else {
    		if(memberService.existsByNickname(nickname)) {
    			// 닉네임 중복 시, 이름 뒤에 현재시간을 36진법으로 바꾼 문자 붙이기
    			nickname += Integer.toString((int)(new Date().getTime()/1000), 36);
    			mem.setNickname(nickname);
    		}
    		authService.signup(mem);
    		memberService.regiMemberinfo(email);
    		System.out.println("회원가입 완료");
    	}
        // 4. 로그인
        mem.setPwd("");
        
        TokenDto token = authService.login(mem);
        System.out.println("토큰 발급");
        
        mem = memberService.findByEmail(email);
        map.put("token", token);
		map.put("seq", mem.getMemberseq());
		map.put("profile", mem.getProfile());
		map.put("provider", "kakao");
		
        return map;
	}
	
	@GetMapping("/google")
	public Map<String, Object> google(String code) {
		System.out.println("구글 " + code);
		
		RestTemplate rt = new RestTemplate();
		Map<String, Object> map = new HashMap<>();
		
		// 1. 프론트에서 넘겨준 code를 이용해서 카카오로부터 Access Token 받기
		// HttpHeader와 HttpBody를 HttpEntity에 담기
		HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        
        MultiValueMap<String, String> params1 = new LinkedMultiValueMap<>();
        	params1.add("grant_type", "authorization_code");
        	params1.add("client_id", googleId);
        	params1.add("redirect_uri", "http://localhost:9100/login/callback/google");
        	params1.add("code", code);
        	params1.add("client_secret", googleSecret);

        HttpEntity<MultiValueMap<String, String>> googleRequest1 = new HttpEntity<>(params1, headers1);

        // HTTP 요청 - POST방식 - response 응답 받기
        ResponseEntity<String> response = rt.exchange(
            "https://oauth2.googleapis.com/token",
            HttpMethod.POST,
            googleRequest1,
            String.class
        );
        System.out.println(response.toString());
        JSONObject jsonObject = new JSONObject(response.getBody());
        
        // 2. 받아온 Access Token을 이용해서 사용자 정보 받기
        // HttpHeader와 HttpBody를 HttpEntity에 담기
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers2.add("Authorization", "Bearer " + jsonObject.get("access_token"));
        
        MultiValueMap<String, String> params2 = new LinkedMultiValueMap<>();
    	params2.add("id_token", jsonObject.getString("id_token"));
    	
        HttpEntity<MultiValueMap<String, String>> googleRequest2 = new HttpEntity<>(params2, headers2);
        
        // HTTP 요청 - GET방식 - response 응답 받기
        ResponseEntity<String> profileResponse = rt.exchange(
        	"https://www.googleapis.com/userinfo/v2/me",
        	HttpMethod.GET,
        	googleRequest2,
        	String.class
        );
        System.out.println(profileResponse.toString());
        
        JSONObject body = new JSONObject(profileResponse.getBody());
        String email = body.getString("email");
        String nickname = body.getString("name");
        MemberDto mem = new MemberDto(email, "", nickname, "ROLE_USER", "google");
        
        // 3. 가입 여부 확인
        if(memberService.existsByEmail(email)) {
        	// 동일한 이메일이 다른 서비스(제공자)로 가입되어있으면 반려
        	String provider = memberService.checkProvider(email);
        	if(!provider.equals("google")) {
        		map.put("provider", provider);
        		System.out.println("다른 서비스(제공자)로 가입되어있음");
        		return map;
        	}
        	// 활동 정지 회원이면 로그인 반려
        	if(memberService.checkAuth(email) == 2) {
        		map.put("provider", "deactivated");
        		System.out.println("활동 정지 회원");
        		return map;
        	}
        }
        // 미가입 이메일이면 회원가입
        else {
    		if(memberService.existsByNickname(nickname)) {
    			// 닉네임 중복 시, 이름 뒤에 현재시간을 36진법으로 바꾼 문자 붙이기
    			nickname += Integer.toString((int)(new Date().getTime()/1000), 36);
    			mem.setNickname(nickname);
    		}
    		authService.signup(mem);
    		memberService.regiMemberinfo(email);
    		System.out.println("회원가입 완료");
    	}
        // 4. 로그인
        mem.setPwd("");
        
        TokenDto token = authService.login(mem);
        System.out.println("토큰 발급");
        
        mem = memberService.findByEmail(email);
        map.put("token", token);
		map.put("seq", mem.getMemberseq());
		map.put("profile", mem.getProfile());
		map.put("provider", "google");
		
        return map;
	}
	
	
}
