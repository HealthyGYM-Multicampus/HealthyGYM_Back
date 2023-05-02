package com.mul.HealthyGYM.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		System.out.println("find ");
		
		return ResponseEntity.ok(service.findByEmail(SecurityUtil.getCurrentMemberId()));
	}
	
	@GetMapping("/checkemail")
	public String checkemail(String email) {
		System.out.println("checkemail ");
		if(!service.existsByEmail(email)) {
			return "OK";
		}
		
		return service.checkProvider(email);
	}
	
	@GetMapping("/checknickname")
	public boolean checknickname(String nickname) {
		System.out.println("checknickname ");
		return service.existsByNickname(nickname);
	}
	
	@GetMapping(value = "authemail")
	public String authCodeEmail(String email) throws Exception {

		// 인증키 생성
		String mailKey = new TempKey().getKey(6, true); // 랜덤키 길이 설정
		System.out.println(mailKey);
		
		// 인증코드 이메일 발송
		service.authCodeEmail(email, mailKey);		
		
		// 인증키 프론트에 보내주기
		return mailKey;
	}
	
	@GetMapping(value = "pwdResetEmail")
	public String pwdResetEmail(MemberDto dto) throws Exception {

		// 인증키 생성
		String mailKey = new TempKey().getKey(10, false); // 랜덤키 길이 설정
		System.out.println(mailKey);
		
		dto.setMailkey(mailKey);
		
		// 비밀번호 재설정 이메일 발송
		service.pwdResetEmail(dto, mailKey);
		service.updateMailKey(dto);

		// 인증키 프론트에 보내주기
		return mailKey;
	}
	
}
