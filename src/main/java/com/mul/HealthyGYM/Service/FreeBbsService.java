package com.mul.HealthyGYM.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mul.HealthyGYM.Dao.FreeBbsDao;
import com.mul.HealthyGYM.Dto.BbsDto;

@Service
@Transactional
public class FreeBbsService {
	
	@Autowired
	FreeBbsDao dao;
	
	public BbsDto freeBbsDetail(int bbs_seq) {
		return dao.freeBbsDetail(bbs_seq);
	}
	
	public boolean writeBbs(BbsDto dto) {
		return dao.writeBbs(dto) > 0;
	}
	
	public boolean uploadFile(String filename) {
		return dao.uploadFile(filename) > 0;
	}
}
