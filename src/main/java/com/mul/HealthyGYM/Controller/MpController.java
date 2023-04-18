package com.mul.HealthyGYM.Controller;

import com.mul.HealthyGYM.Dto.FollowDto;
import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.MemberinfoDto;
import com.mul.HealthyGYM.Dto.ProfileDto;
import com.mul.HealthyGYM.Service.MpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MpController {

    public static int temp = 1;

    @Autowired
    MpService service;

    // 세션에 저장된 memberseq값으로 member 정보 가져오기
    @GetMapping(value = "/members/findmember")
    public MemberDto findMember(HttpSession session) {

        // <-- 임시 세팅
        session.setAttribute("memberseq", temp);
        // 임시 세팅 -->

        int memberseq = (int) session.getAttribute("memberseq");
        MemberDto memberDto = service.findMemberById(memberseq);
        return memberDto;
    }

    @GetMapping(value = "/members/findmemberinfo")
    public MemberinfoDto findMemberinfo(HttpSession session) {

        // <-- 임시 세팅
        session.setAttribute("memberseq", temp);
        // 임시 세팅 -->

        int memberseq = (int) session.getAttribute("memberseq");
        MemberinfoDto memberinfoDto = service.findMemberinfoById(memberseq);
        return memberinfoDto;
    }

    @PostMapping(value = "/members/profileupdate")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String profileUpdate(@RequestBody ProfileDto profileDto) {
        System.out.println("수정 완료 : " + profileDto);
        service.profileUpdate(profileDto);
        return "ok";
    }

    @GetMapping(value = "/members/follow")
    public Map<String, Object> followingMembers(HttpSession session){

        // <-- 임시 세팅
        session.setAttribute("memberseq", temp);
        // 임시 세팅 -->

        int memberseq = (int) session.getAttribute("memberseq");

        List<FollowDto> followDtoList = service.followingMembers(memberseq);
        int followNum = followDtoList.size();
        System.out.println(followDtoList.toString());

        Map<String, Object> map = new HashMap<>();
        map.put("followDtoList", followDtoList);
        map.put("followNum", followNum);
        return map;
    }
}
