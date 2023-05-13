package com.mul.HealthyGYM.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mul.HealthyGYM.Dto.BbsParam;
import com.mul.HealthyGYM.Dto.SearchParam;

@Mapper
@Repository
public interface TopicBestDao {
	
	//토픽 베스트
	List<Map<String, Object>> findAllBest(BbsParam param);
	
	//검색
	List<Map<String, Object>> findBbsByKeyword(SearchParam search);
}
