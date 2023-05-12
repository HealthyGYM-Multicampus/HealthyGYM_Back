package com.mul.HealthyGYM.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mul.HealthyGYM.Dao.TopicBestDao;
import com.mul.HealthyGYM.Dto.BbsParam;
import com.mul.HealthyGYM.Dto.SearchParam;

@Service
@Transactional
public class TopicBestService {
	
	@Autowired
	TopicBestDao dao;
	
	public List<Map<String, Object>> findAllBest(BbsParam param) {
		return dao.findAllBest(param);
	}
	
	public List<Map<String, Object>> findBbsByKeyword(SearchParam search) {
		return dao.findBbsByKeyword(search);
	}

}
