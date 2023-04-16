package com.mul.HealthyGYM.Service;

import com.mul.HealthyGYM.Dao.MpDao;
import com.mul.HealthyGYM.Dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MpService {

    @Autowired
    MpDao dao;

    public MemberDto findMemberById(int memberSeq) {
        MemberDto memberDto = dao.findMemberById(memberSeq);
        return memberDto;
    }
}
