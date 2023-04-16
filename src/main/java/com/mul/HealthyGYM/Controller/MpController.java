package com.mul.HealthyGYM.Controller;

import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Service.MpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MpController {

    @Autowired
    MpService service;

    // 세션에 저장된 memberSeq값으로 member 정보 가져오기
    @GetMapping(value = "/members/findmember")
    public MemberDto findMember(HttpSession session){

        // <-- 임시 세팅
        session.setAttribute("memberSeq", 1);
        // 임시 세팅 -->

        int memberSeq = (int) session.getAttribute("memberSeq");
        MemberDto memberDto = service.findMemberById(memberSeq);
        return memberDto;
    }
}
