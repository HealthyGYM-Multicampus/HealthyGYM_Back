package com.mul.HealthyGYM.Controller;

import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.MemberinfoDto;
import com.mul.HealthyGYM.Service.MpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MpController {

    @Autowired
    MpService service;

    // 세션에 저장된 memberseq값으로 member 정보 가져오기
    @GetMapping(value = "/members/findmember")
    public MemberDto findMember(HttpSession session){

        // <-- 임시 세팅
        session.setAttribute("memberseq", 3);
        // 임시 세팅 -->

        int memberseq = (int) session.getAttribute("memberseq");
        MemberDto memberDto = service.findMemberById(memberseq);
        return memberDto;
    }

    @GetMapping(value = "/members/findmemberinfo")
    public MemberinfoDto findMemberinfo(HttpSession session){

        // <-- 임시 세팅
        session.setAttribute("memberseq", 3);
        // 임시 세팅 -->

        int memberseq = (int) session.getAttribute("memberseq");
        MemberinfoDto memberinfoDto = service.findMemberinfoById(memberseq);
        return memberinfoDto;
    }
}
