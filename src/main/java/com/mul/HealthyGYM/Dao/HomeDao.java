package com.mul.HealthyGYM.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mul.HealthyGYM.Dto.BbsDto;

@Mapper
@Repository
public interface HomeDao {
	
	List<BbsDto> homeBest();
	List<BbsDto> homeBbs(int bbstag);
}
