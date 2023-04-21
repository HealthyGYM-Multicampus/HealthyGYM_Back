package com.mul.HealthyGYM.Controller;

import com.mul.HealthyGYM.Dto.FollowDto;
import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.MemberinfoDto;
import com.mul.HealthyGYM.Dto.ProfileDto;
import com.mul.HealthyGYM.Service.MpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
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
    public String profileUpdate(@ModelAttribute ProfileDto profileDto) throws IOException {
        System.out.println("진입");
        service.profileUpdate(profileDto);
        System.out.println("통과");
        return "ok";
    }

    @PostMapping(value = "/members/profileupdatenull")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String profileUpdateNull(@RequestBody ProfileDto profileDto) throws IOException {
        System.out.println("null진입");
        service.profileUpdate(profileDto);
        System.out.println("null통과");
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

    @GetMapping(value = "/members/follower")
    public Map<String, Object> followerMembers(HttpSession session){

        // <-- 임시 세팅
        session.setAttribute("memberseq", temp);
        // 임시 세팅 -->

        int memberseq = (int) session.getAttribute("memberseq");

        List<FollowDto> followDtoList = service.followerembers(memberseq);
        int followerNum = followDtoList.size();
        System.out.println(followDtoList.toString());

        Map<String, Object> map = new HashMap<>();
        map.put("followDtoList", followDtoList);
        map.put("followerNum", followerNum);
        return map;
    }

    @PostMapping(value = "/members/pwdupdate")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String pwdUpdate(HttpSession session, @RequestBody MemberDto memberDto) {
        System.out.println("pwdUpdate진입");
        MemberDto dto = new MemberDto();

        // <-- 임시 세팅
        session.setAttribute("memberseq", temp);
        // 임시 세팅 -->

        int memberseq = (int)session.getAttribute("memberseq");
        String pwd = memberDto.getPwd();

        System.out.println("memberseq:"+memberseq);
        System.out.println("pwd:"+pwd);
        dto.setMemberseq(memberseq);
        dto.setPwd(pwd);
        System.out.println("dto: "+dto.getPwd() + dto.getMemberseq());
        service.pwdUpdate(dto);
        System.out.println("pwdUpdate통과");
        return "ok";
    }

    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<FileSystemResource> getImage(@PathVariable String imageName) {
        //String imagePath = "C:/upload/" + imageName;   //window OS
        String imagePath = "/Users/admin/springboot_img/" + imageName;    //mac OS
        File imageFile = new File(imagePath);
        return ResponseEntity.ok()
                .contentLength(imageFile.length())
                .body(new FileSystemResource(imageFile));
    }
}
