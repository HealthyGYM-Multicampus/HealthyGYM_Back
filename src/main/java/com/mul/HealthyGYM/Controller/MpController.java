package com.mul.HealthyGYM.Controller;

import com.mul.HealthyGYM.Dto.*;
import com.mul.HealthyGYM.Service.MpService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    //public static String localPath =  "C:/upload/"

    /*Mac Os*/
    public static String localPath = "/Users/admin/springboot_img/";

    @Autowired
    MpService service;

    @PostMapping(value = "/members/findmember")
    public MemberDto findMember(@RequestBody UserRequest userRequest) {
        System.out.println("findMember " + new Date());

        String authToken = userRequest.getAuthToken();
        int memberseq = Integer.parseInt(authToken);

        MemberDto memberDto = service.findMemberById(memberseq);
        return memberDto;
    }

    @PostMapping(value = "/members/findmemberinfo")
    public MemberinfoDto findMemberinfo(@RequestBody UserRequest userRequest) {
        System.out.println("findMemberinfo " + new Date());

        String authToken = userRequest.getAuthToken();
        int memberseq = Integer.parseInt(authToken);

        MemberinfoDto memberinfoDto = service.findMemberinfoById(memberseq);
        return memberinfoDto;
    }

    @PostMapping(value = "/members/profileupdate")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String profileUpdate(@ModelAttribute ProfileDto profileDto) throws IOException {
        System.out.println("profileUpdate " + new Date());
        service.profileUpdate(profileDto);
        return "ok";
    }

    // Multipart file이 null일 경우 프로필이미지를 제외한 나머지 회원정보 업데이트
    @PostMapping(value = "/members/profileupdatenull")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String profileUpdateNull(@RequestBody ProfileDto profileDto) throws IOException {
        System.out.println("profileUpdateNull " + new Date());
        service.profileUpdate(profileDto);
        return "ok";
    }

    @PostMapping(value = "/members/follow")
    public Map<String, Object> followingMembers(@RequestBody UserRequest userRequest) {
        System.out.println("followingMembers " + new Date());

        String authToken = userRequest.getAuthToken();
        int memberseq = Integer.parseInt(authToken);

        List<FollowDto> followDtoList = service.followingMembers(memberseq);
        int followNum = followDtoList.size();

        Map<String, Object> map = new HashMap<>();
        map.put("followDtoList", followDtoList);
        map.put("followNum", followNum);
        return map;
    }

    @PostMapping(value = "/members/follower")
    public Map<String, Object> followerMembers(@RequestBody UserRequest userRequest) {
        System.out.println("followerMembers " + new Date());

        String authToken = userRequest.getAuthToken();
        int memberseq = Integer.parseInt(authToken);

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
    public String ocr_fileUpload(@ModelAttribute ProfileDto profileDto) {
        System.out.println("ocr_fileUpload " + new Date());

        MultipartFile uploadFile = profileDto.getUploadFile();

        int memberseq = profileDto.getMemberseq();

        System.out.println("memberseq값은?"+memberseq);
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
            return "fail";
        }

        String result = service.ocr(filepath, memberseq, filename);

        return result;
    }

    @PostMapping(value = "/inbodylist")
    public Map<String, Object> inbodyList(@RequestBody UserRequest userRequest) {
        System.out.println("inbodyList " + new Date());

        String authToken = userRequest.getAuthToken();
        int memberseq = Integer.parseInt(authToken);

        List<InbodyDto> list = service.inbodyList(memberseq);

        Map<String, Object> map = new HashMap<>();
        map.put("list", list);

        return map;
    }
}
