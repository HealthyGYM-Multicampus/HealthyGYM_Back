package com.mul.HealthyGYM.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mul.HealthyGYM.Dto.BbsDto;

@Mapper
@Repository
public interface FreeBbsDao {
	
	List<Map<String, Object>> freeBbsDetail(int bbsseq);
	List<Map<String, Object>> freeBbsComment(int bbsseq);
	int writeBbs(BbsDto dto);
	int uploadFile(String filename);
}
