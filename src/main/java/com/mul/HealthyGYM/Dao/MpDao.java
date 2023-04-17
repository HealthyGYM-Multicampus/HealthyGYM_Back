package com.mul.HealthyGYM.Dao;

import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.MemberinfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MpDao {
    MemberDto findMemberById(int memberseq);

    MemberinfoDto findMemberinfoById(int memberseq);
}
