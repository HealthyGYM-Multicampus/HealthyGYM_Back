package com.mul.HealthyGYM.Service;

import com.mul.HealthyGYM.Dao.MpDao;
import com.mul.HealthyGYM.Dto.FollowDto;
import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.MemberinfoDto;
import com.mul.HealthyGYM.Dto.ProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Transactional
public class MpService {

    @Autowired
    MpDao dao;

    public MemberDto findMemberById(int memberseq) {
        MemberDto memberDto = dao.findMemberById(memberseq);
        return memberDto;
    }

    public MemberinfoDto findMemberinfoById(int memberseq) {
        MemberinfoDto memberinfoDto = dao.findMemberinfoById(memberseq);
        return memberinfoDto;
    }

    public void profileUpdate(ProfileDto profileDto) throws IOException {

        System.out.println("profileUpdate 진입");

        if(profileDto.getImage()!=null) {
            MultipartFile imageFile = profileDto.getImage();
            String storedFileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();

            //String savePath = "C:/upload/" + storedFileName; //window OS
            String savePath = "/Users/admin/springboot_img/" + storedFileName;  //mac OS
            imageFile.transferTo(new File(savePath));

            profileDto.setProfile(storedFileName);
        }
        dao.profileUpdate(profileDto);
    }

    public List<FollowDto> followingMembers(int memberseq) {
        List<FollowDto> followDtoList = dao.followingMembers(memberseq);
        return followDtoList;
    }

    public List<FollowDto> followerembers(int memberseq) {

        //nickname으로 조회
        String nickname = dao.findMemberById(memberseq).getNickname();
        System.out.println(nickname);

        List<FollowDto> followDtoList = dao.followerMembers(nickname);
        return followDtoList;
    }

    public void pwdUpdate(MemberDto dto) {
        System.out.println("service진입");
        dao.pwdUpdate(dto);
        System.out.println("service통과");
    }
}
