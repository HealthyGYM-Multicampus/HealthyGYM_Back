package com.mul.HealthyGYM.Dao;

import com.mul.HealthyGYM.Dto.*;
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

    List<FollowDto> followerMembers(int memberseq);

    void pwdUpdate(MemberDto dto);

    void inbodySave(InbodyDto dto);

    List<InbodyDto> inbodyList(int memberseq);

    void followUpdate(String beforename, String newname);

}
