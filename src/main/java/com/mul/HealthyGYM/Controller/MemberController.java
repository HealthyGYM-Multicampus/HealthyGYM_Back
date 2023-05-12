package com.mul.HealthyGYM.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Service.MemberService;
import com.mul.HealthyGYM.Util.SecurityUtil;
import com.mul.HealthyGYM.Util.TempKey;

@RestController
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("/me")
	public ResponseEntity<MemberDto> findMyEmail() {
		System.out.println("findMyEmail ");
		
		return ResponseEntity.ok(service.findByEmail(SecurityUtil.getCurrentMemberId()));
	}
	
	@GetMapping("/findprofile")
	public String findProfile(int memberseq) {
		System.out.println("findProfile " + memberseq);
		String s = service.findProfile(memberseq);
		System.out.println(s);
		return s;
	}
	
	@GetMapping("/checkemail")
	public String checkEmail(String email) {
		System.out.println("checkEmail ");
		if(!service.existsByEmail(email)) {
			return "OK";
		}
		
		return service.checkProvider(email);
	}
	
	@GetMapping("/checknickname")
	public boolean checkNickname(String nickname) {
		System.out.println("checkNickname ");
		return service.existsByNickname(nickname);
	}
	
	@PostMapping("/updatepwd")
	public boolean updatePwd(MemberDto dto) {
		System.out.println("updatePwd ");
		
		String mailKey = service.findByEmail(dto.getEmail()).getMailkey();
		if(!mailKey.equals(dto.getMailkey())) {
			return false;
		}
		
		return service.updatePwd(dto);
	}
	
	@GetMapping("/authemail")
	public String authEmail(String email) throws Exception {

		// 인증키 생성
		String mailKey = new TempKey().getKey(6, true); // 랜덤키 길이 설정
		System.out.println(mailKey);
		
		// 인증코드 이메일 발송
		service.authCodeEmail(email, mailKey);		
		
		// 인증키 프론트에 보내주기
		return mailKey;
	}
	
	@GetMapping("/pwdemail")
	public String pwdEmail(String email) throws Exception {
		
		MemberDto dto = service.findByEmail(email);
		
		if(dto == null) {
			return "NO";
		} else if(dto.getProvider().equals("kakao")) {
			return "kakao";
		} else if(dto.getProvider().equals("google")) {
			return "google";
		}
		
		// 인증키 생성
		String mailKey = new TempKey().getKey(10, false); // 랜덤키 길이 설정
		System.out.println(mailKey);
		
		dto.setMailkey(mailKey);
		
		// 비밀번호 재설정 이메일 발송
		service.pwdResetEmail(dto);
		service.updateMailKey(dto);

		// 인증키 프론트에 보내주기
		return mailKey;
	}
	
}
