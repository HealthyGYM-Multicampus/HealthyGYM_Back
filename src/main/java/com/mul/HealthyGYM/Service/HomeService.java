package com.mul.HealthyGYM.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mul.HealthyGYM.Dao.HomeDao;
import com.mul.HealthyGYM.Dto.BbsDto;

@Service
@Transactional
public class HomeService {
	
	@Autowired
	HomeDao dao;
	
	public List<BbsDto> homeBest() {
		return dao.homeBest();
	}
	
	public List<BbsDto> homeBbs(int bbstag) {
		return dao.homeBbs(bbstag);
	}
}
