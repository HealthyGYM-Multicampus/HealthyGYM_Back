package com.mul.HealthyGYM.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mul.HealthyGYM.Dto.MemberDto;

@Mapper
@Repository
public interface MemberDao {
	
	String findProfile(int memberseq);
	MemberDto findBySeq(int memberseq);
	MemberDto findByEmail(String email);
	int findSeqByEmail(String email);
	int existsByEmail(String email);
	int existsByNickname(String nickname);
	String checkProvider(String email);
	
	int signup(MemberDto dto);
	int signupOAuth(MemberDto dto);
	int regiMemberinfo(String email);
	
	int updatePwd(MemberDto dto);
	int updateMailKey(MemberDto dto);
}
