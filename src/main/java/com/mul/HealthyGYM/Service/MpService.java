package com.mul.HealthyGYM.Service;

import com.mul.HealthyGYM.Dao.MpDao;
import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.MemberinfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
