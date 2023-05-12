package com.mul.HealthyGYM.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Dto.MateDto;
import com.mul.HealthyGYM.Dto.MateParam;

@Mapper
@Repository
public interface HealthDao {
	
	List<Map<String, Object>> getList(MateParam param);
	List<Map<String, Object>> getDetail(int bbsseq);
	
	int writeBbs(BbsDto dto);
	int writeMate(MateDto dto);
	
}
