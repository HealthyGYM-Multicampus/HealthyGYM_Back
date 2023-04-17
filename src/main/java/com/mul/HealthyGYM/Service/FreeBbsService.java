package com.mul.HealthyGYM.Service;

import java.util.List;
import java.util.Map;

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
	
	public List<Map<String, Object>> freeBbsDetail(int bbsseq) {
		return dao.freeBbsDetail(bbsseq);
	}
	
	public List<Map<String, Object>> freeBbsComment(int bbsseq) {
		return dao.freeBbsComment(bbsseq);
	}
	
	public boolean writeBbs(BbsDto dto) {
		return dao.writeBbs(dto) > 0;
	}
	
	public boolean uploadFile(String filename) {
		return dao.uploadFile(filename) > 0;
	}
}
