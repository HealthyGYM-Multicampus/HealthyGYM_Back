package com.mul.HealthyGYM.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mul.HealthyGYM.Dao.HealthDao;
import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Dto.MateDto;
import com.mul.HealthyGYM.Dto.MateParam;

@Service
@Transactional
public class HealthService {
	
	@Autowired
	HealthDao dao;
	
	public List<Map<String, Object>> getList(MateParam param) {
		return dao.getList(param);
	}
	public List<Map<String, Object>> getDetail(int bbsseq) {
		return dao.getDetail(bbsseq);
	}
	
	public boolean writeMateBbs(BbsDto bbsdto, MateDto matedto) {
		boolean b = dao.writeBbs(bbsdto) > 0;
		matedto.setBbsseq(bbsdto.getBbsseq());
		
		return b & dao.writeMate(matedto) > 0;
	}
	
	public boolean updateMateBbs(BbsDto bbsdto, MateDto matedto) {
		boolean b = dao.updateBbs(bbsdto) > 0;
		matedto.setBbsseq(bbsdto.getBbsseq());

		return b & dao.updateMate(matedto) > 0;
	}
	
	public boolean readcountUp(int bbsseq) {
		return dao.readcountUp(bbsseq) > 0;
	}
}
