package com.mul.HealthyGYM.Controller;

import com.mul.HealthyGYM.Dto.*;
import com.mul.HealthyGYM.Service.MpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

@RestController
public class MpController {

    /*Window Os*/
    public static String localPath =  "C:/upload/";

    /*Mac Os*/
    //public static String localPath = "/Users/admin/springboot_img/";

    @Autowired
    MpService service;

    @PostMapping(value = "/members/findmember")
    public MemberDto findMember(@RequestBody MemberDto dto) {
        System.out.println("findMember " + new Date());

        int memberseq = dto.getMemberseq();

        MemberDto memberDto = service.findMemberById(memberseq);
        return memberDto;
    }

    @PostMapping(value = "/members/findmemberinfo")
    public MemberinfoDto findMemberinfo(@RequestBody MemberDto dto) {
        System.out.println("findMemberinfo " + new Date());

        int memberseq = dto.getMemberseq();

        MemberinfoDto memberinfoDto = service.findMemberinfoById(memberseq);
        return memberinfoDto;
    }

    @PostMapping(value = "/members/profileupdate")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String profileUpdate(@ModelAttribute ProfileDto profileDto) throws IOException {
        System.out.println("profileUpdate " + new Date());
        service.profileUpdate(profileDto);
        String profile = service.findMemberById(profileDto.getMemberseq()).getProfile();
        return profile;
    }

    // Multipart file이 null일 경우 프로필이미지를 제외한 나머지 회원정보 업데이트
    @PostMapping(value = "/members/profileupdatenull")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String profileUpdateNull(@RequestBody ProfileDto profileDto) throws IOException {
        System.out.println("profileUpdateNull " + new Date());
        service.profileUpdate(profileDto);
        String profile = service.findMemberById(profileDto.getMemberseq()).getProfile();
        return profile;
    }

    @PostMapping(value = "/members/follow")
    public Map<String, Object> followingMembers(@RequestBody MemberDto dto) {
        System.out.println("followingMembers " + new Date());

        int memberseq = dto.getMemberseq();

        List<FollowDto> followDtoList = service.followingMembers(memberseq);
        int followNum = followDtoList.size();

        Map<String, Object> map = new HashMap<>();
        map.put("followDtoList", followDtoList);
        map.put("followNum", followNum);

        return map;
    }

    @PostMapping(value = "/members/follower")
    public Map<String, Object> followerMembers(@RequestBody MemberDto dto) {
        System.out.println("followerMembers " + new Date());

        int memberseq = dto.getMemberseq();

        List<FollowDto> followDtoList = service.followerembers(memberseq);
        int followerNum = followDtoList.size();

        Map<String, Object> map = new HashMap<>();
        map.put("followDtoList", followDtoList);
        map.put("followerNum", followerNum);

        return map;
    }

    @PostMapping(value = "/members/pwdupdate")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String pwdUpdate(@RequestBody MemberDto memberDto) {
        System.out.println("pwdUpdate " + new Date());
        MemberDto dto = new MemberDto();

        int memberseq = memberDto.getMemberseq();
        String pwd = memberDto.getPwd();

        dto.setMemberseq(memberseq);
        dto.setPwd(pwd);

        service.pwdUpdate(dto);
        return "ok";
    }

    @GetMapping(value = "/images/{folderName}/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<FileSystemResource> getImage(@PathVariable String folderName, @PathVariable String imageName) {
        System.out.println("getImage " + new Date());

        String imagePath = localPath + folderName + "/" + imageName;

        File imageFile = new File(imagePath);
        return ResponseEntity.ok()
                .contentLength(imageFile.length())
                .body(new FileSystemResource(imageFile));
    }

    @PostMapping(value = "/ocr_fileUpload")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public InbodyDto ocr_fileUpload(@ModelAttribute ProfileDto profileDto) {
        System.out.println("ocr_fileUpload " + new Date());
        InbodyDto inbodyDto = new InbodyDto();

        int memberseq = profileDto.getMemberseq();

        MultipartFile uploadFile = profileDto.getUploadFile();

        String originalFileName = uploadFile.getOriginalFilename();    //오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));    //파일 확장자

        // UUID 사용하여 파일명 중복 문제 처리
        String filename = UUID.randomUUID() + extension;
        String filepath = localPath + "inbody/" + filename;

        System.out.println("업로드 경로 : " + filepath);

        try {
            // 업로드된 파일을 임시 파일로 저장
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            os.write(uploadFile.getBytes());
            os.close();

            // 이미지 크기를 변경하여 저장
            BufferedImage originalImage = ImageIO.read(new File(filepath));
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            double aspectRatio = (double) width / height;
            int newWidth = 1000;
            int newHeight = (int) (newWidth / aspectRatio);
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();
            ImageIO.write(resizedImage, "png", new File(filepath)); // 이미지 파일 확장자명에 상관없이 png 파일로 저장

        } catch (Exception e) {
            e.printStackTrace();
            inbodyDto.setImgpath("이미지 저장 실패");
            return inbodyDto;
        }

        InbodyDto result = service.ocr(filepath, memberseq, filename);

        System.out.println("result:"+result);
        return result;
    }

    @PostMapping(value = "/bodycomlist")
    public Map<String, Object> inbodyList(@RequestBody MemberDto dto) {
        System.out.println("bodycomList " + new Date());

        int memberseq = dto.getMemberseq();

        List<InbodyDto> list = service.bodycomList(memberseq);

        Map<String, Object> map = new HashMap<>();
        map.put("list", list);

        return map;
    }

    @PostMapping(value = "/mypage/mybbs")
    public List<Map<String, Object>> bbsImageList(@RequestBody UserBbsParam userBbsParam) {
        System.out.println("bbsImageList" + new Date());

        int tag = userBbsParam.getBbstag();
        if(tag != 0){
            return service.myBbsList(userBbsParam);
        } else {
            return service.myAllBbsList(userBbsParam);
        }
    }

    @PostMapping(value = "/mypage/mycmtbbs")
    public List<Map<String, Object>> myCmtBbs(@RequestBody UserBbsParam userBbsParam) {
        System.out.println("myCmtBbs" + new Date());
        int tag = userBbsParam.getBbstag();
        if(tag != 0){
            return service.myCmtBbsList(userBbsParam);
        } else {
            return service.myAllCmtBbsList(userBbsParam);
        }
    }

    @PostMapping(value = "/mypage/mylikebbs")
    public List<Map<String, Object>> myLikeBbs(@RequestBody UserBbsParam userBbsParam) {
        System.out.println("myCmtBbs" + new Date());
        int tag = userBbsParam.getBbstag();
        if(tag != 0){
            return service.myLikeBbsList(userBbsParam);
        } else {
            return service.myAllLikeBbsList(userBbsParam);
        }
    }

    @PostMapping(value = "/userbodycom")
    public String bodyComSave(@RequestBody InbodyDto inbodyDto){
        System.out.println("userBodyCom" + new Date());

        service.bodyComSave(inbodyDto);

        return "ok";
    }

    @PostMapping(value = "/bodycomdelete")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String bodyComDelete(@RequestBody InbodyDto inbodyDto) {
        System.out.println("bodyComDelete " + new Date());
        int bodycomseq = inbodyDto.getBodycomseq();

        service.bodyComDelete(bodycomseq);

        return "ok";
    }

    @PostMapping(value = "/memberdelete")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String memberDelete(@RequestBody MemberDto memberDto) {
        System.out.println("memberDelete " + new Date());
        int memberseq = memberDto.getMemberseq();

        MemberDto dto = service.findMemberById(memberseq);

        String ranEmail = UUID.randomUUID() + "@healthygym.com";
        dto.setEmail(ranEmail);
        dto.setAuth(3);

        service.memberDelete(dto);

        return "ok";
    }

    @PostMapping(value = "/request/follow")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public boolean reqFollow(@RequestBody FollowDto followDto) {
        System.out.println("reqFollow " + new Date());

        service.reqFollow(followDto);

        return true;
    }

    @PostMapping(value = "/request/unfollow")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public boolean reqUnFollow(@RequestBody FollowDto followDto) {
        System.out.println("reqUnFollow " + new Date());

        service.reqUnFollow(followDto);

        return true;
    }

    @PostMapping(value = "/confirm/follow")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public boolean confirmFollow(@RequestBody FollowDto followDto) {
        System.out.println("confirmFollow " + new Date());

        int result = service.confirmFollow(followDto);

        if(result == 0){
            return false;
        } else {
            return true;
        }
    }

    @PostMapping(value = "/confirm/mate")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public boolean confirmMate(@RequestBody FollowDto followDto) {
        System.out.println("confirmMate " + new Date());

        if(followDto.getMemberseq() == followDto.getUserseq()) {
            return true;
        } else if(followDto.getMemberseq()==0){
            return false;
        } else {
            FollowDto followDto1 = new FollowDto();
            FollowDto followDto2 = new FollowDto();

            int loginMemberseq = followDto.getMemberseq();
            String loginNickname = service.findMemberById(loginMemberseq).getNickname();

            int userMemberseq = followDto.getUserseq();
            String userNickname = service.findMemberById(userMemberseq).getNickname();

            followDto1.setMemberseq(loginMemberseq);
            followDto2.setMemberseq(userMemberseq);
            followDto1.setFoltarget(userNickname);
            followDto2.setFoltarget(loginNickname);

            int result1 = service.confirmFollow(followDto1);
            int result2 = service.confirmFollow(followDto2);

            if (result1 != 0 && result2 != 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    @PostMapping(value = "/confirm/follow/me")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public boolean confirmFollowMe(@RequestBody MemberDto memberDto) {
        System.out.println("confirmFollowMe " + new Date());

        int memberseq = memberDto.getMemberseq();
        String nickname = service.findMemberById(memberseq).getNickname();

        if(memberDto.getNickname().equals(nickname)){
            return true;
        } else {
            return false;
        }
    }
}
