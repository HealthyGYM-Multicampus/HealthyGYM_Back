package com.mul.HealthyGYM.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mul.HealthyGYM.Dao.MemberDao;
import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Util.MailHandler;

@Service
@Transactional
public class MemberService {
	
	@Autowired
	MemberDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	JavaMailSender mailSender;
	
	public String findProfile(int memberseq) {
		return dao.findProfile(memberseq);
	}
	
	public MemberDto findByEmail(String email) {
		MemberDto dto = dao.findByEmail(email);
		if(dto == null) {
			return null;
		}
		return dto;
	}
	
	public int findSeqByEmail(String email) {
		return dao.findSeqByEmail(email);
	}
	
	public boolean existsByEmail(String email) {
		return dao.existsByEmail(email) > 0;
	}
	
	public boolean existsByNickname(String nickname) {
		return dao.existsByNickname(nickname) > 0;
	}
	
	public String checkProvider(String email) {
		return dao.checkProvider(email);
	}
	
	public boolean regiMemberinfo(String email) {
		return dao.regiMemberinfo(email) > 0;
	}
	
	public boolean updatePwd(MemberDto dto) {
		dto.setPwd(passwordEncoder.encode(dto.getPwd()));
		return dao.updatePwd(dto) > 0;
	}
	
	public int updateMailKey(MemberDto dto) {
		return dao.updateMailKey(dto);
	}
	
	// 이메일 인증번호 발송
	public void authCodeEmail(String email, String mailKey) throws Exception {
				
		String mailContent = "<div style='text-align:center;'>" + 
                "<h1 style='color:black;'>건강해ZYM 메일인증</h1>" +
                "<p style='font-size:18px; color:#333; margin-top:30px; margin-bottom:20px;'>환영합니다!</p>" +
                "<p style='font-size:16px; color:#555; margin-top:20px; margin-bottom:30px;'>아래 인증번호를 확인해주세요.</p>" +
                "<div style='background-color:#E9ECEF; color:#333; font-size:20px; padding:10px; display:inline-block; border-radius:5px; margin-bottom:30px;'>" + mailKey + "</div>" +
                "<p style='font-size:14px; color:#999; margin-top:30px;'>본 이메일은 발신 전용입니다. 문의 사항은 고객센터를 이용해주세요.</p>" +
                "<p style='font-size:14px; color:#999;'>건강해ZYM</p>" +
            "</div>";

		MailHandler mailHandler = new MailHandler(mailSender);
		mailHandler.setSubject("[건강해ZYM] 회원가입 인증메일 입니다.");
		mailHandler.setText(mailContent);
		mailHandler.setFrom("healthyzym@gmail.com", "건강해ZYM");
		mailHandler.setTo(email);
		mailHandler.send();
	}
	
	// 비밀번호 재설정 이메일 발송
	public void pwdResetEmail(MemberDto dto) throws Exception {
		
		// 메일 내용
		String mailContent = "<div style='text-align:center;'>" +
                "<h1 style='color:black;'>건강해ZYM 비밀번호 재설정</h1>" +
                "<p style='font-size:18px; color:#333; margin-top:30px; margin-bottom:20px;'>안녕하세요, " + dto.getNickname() + "님!</p>" +
                "<p style='font-size:16px; color:#555; margin-top:20px; margin-bottom:30px;'>비밀번호를 재설정 하시려면 아래 버튼을 클릭해주세요.</p>" +
                "<a href='http://localhost:9100/password/update?email=" + dto.getEmail() + "&mail_key=" + dto.getMailkey() + "' style='display:inline-block; background-color:#FF4136; color:#fff; font-size:16px; text-align:center; padding:12px 20px; border-radius:5px; text-decoration:none; margin-bottom:30px;'>비밀번호 재설정</a>" +
                "<p style='font-size:14px; color:#999; margin-top:30px;'>본 이메일은 발신 전용입니다. 문의 사항은 고객센터를 이용해주세요.</p>" +
                "<p style='font-size:14px; color:#999;'>건강해ZYM</p>" +
                "</div>";		

		// 인증을 위한 이메일 발송	
		MailHandler mailHandler = new MailHandler(mailSender);
		mailHandler.setSubject("[건강해ZYM] " + dto.getNickname() + "님 비밀번호 재설정 이메일입니다."); // 메일제목
		mailHandler.setText(mailContent);
		mailHandler.setFrom("healthyzym@gmail.com", "건강해ZYM");
		mailHandler.setTo(dto.getEmail());
		mailHandler.send();
	}	
}
