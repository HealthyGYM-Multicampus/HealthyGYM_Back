package com.mul.HealthyGYM.Dao;

import com.mul.HealthyGYM.Dto.FollowDto;
import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.MemberinfoDto;
import com.mul.HealthyGYM.Dto.ProfileDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MpDao {
    MemberDto findMemberById(int memberseq);

    MemberinfoDto findMemberinfoById(int memberseq);

    void profileUpdate(ProfileDto profileDto);

    List<FollowDto> followingMembers(int memberseq);

    List<FollowDto> followerMembers(String nickname);

    void pwdUpdate(MemberDto dto);

}
