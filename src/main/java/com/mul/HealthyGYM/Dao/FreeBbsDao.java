package com.mul.HealthyGYM.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mul.HealthyGYM.Dto.BbsDto;

@Mapper
@Repository
public interface FreeBbsDao {
	
	BbsDto freeBbsDetail(int bbs_seq);
	int writeBbs(BbsDto dto);
	int uploadFile(String filename);
}
