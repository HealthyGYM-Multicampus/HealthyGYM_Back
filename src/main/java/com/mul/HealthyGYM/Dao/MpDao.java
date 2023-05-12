package com.mul.HealthyGYM.Dao;

import com.mul.HealthyGYM.Dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    List<InbodyDto> bodycomList(int memberseq);

    void followUpdate(String beforename, String newname);

    List<Map<String, Object>> myBbsList(UserBbsParam userBbsParam);

    List<Map<String, Object>> myAllBbsList(UserBbsParam userBbsParam);

    void bodyComSave(InbodyDto inbodyDto);

    List<Map<String, Object>> myCmtBbsList(UserBbsParam userBbsParam);

    List<Map<String, Object>> myAllCmtBbsList(UserBbsParam userBbsParam);

    List<Map<String, Object>> myLikeBbsList(UserBbsParam userBbsParam);

    List<Map<String, Object>> myAllLikeBbsList(UserBbsParam userBbsParam);

}
