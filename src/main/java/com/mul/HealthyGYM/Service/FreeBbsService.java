package com.mul.HealthyGYM.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mul.HealthyGYM.Dao.FreeBbsDao;
import com.mul.HealthyGYM.Dto.BbsCommentDto;
import com.mul.HealthyGYM.Dto.BbsCommentParam;
import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Dto.BbsParam;

@Service
@Transactional
public class FreeBbsService {
	
	@Autowired
	FreeBbsDao dao;
	
	public List<Map<String, Object>> bbsList(BbsParam param) {
		return dao.bbsList(param);
	}
	
	public List<Map<String, Object>> bbsDetail(int bbsseq) {
		return dao.bbsDetail(bbsseq);
	}
	
	public boolean checkLiking(BbsDto dto) {
		return dao.checkLiking(dto) > 0;
	}
	
	public List<Map<String, Object>> bbsComment(BbsCommentParam param) {
		return dao.bbsComment(param);
	}
	
	public boolean writeBbs(BbsDto dto) {
		return dao.writeBbs(dto) > 0;
	}
	
	public boolean writeBbsComment(BbsCommentDto dto) {
		return dao.writeBbsComment(dto) > 0;
	}
	
	public boolean writeBbsReply(BbsCommentDto dto) {
		return dao.writeBbsReply(dto) > 0;
	}
	
	public boolean likeBbs(BbsDto dto) {
		return dao.likeBbs(dto) > 0;
	}
	
	public boolean unlikeBbs(BbsDto dto) {
		return dao.unlikeBbs(dto) > 0;
	}
	
	public boolean likecountUp(int bbsseq) {
		return dao.likecountUp(bbsseq) > 0;
	}
	
	public boolean likecountDown(int bbsseq) {
		return dao.likecountDown(bbsseq) > 0;
	}
	
	public boolean deleteBbs(int bbsseq) {
		return dao.deleteBbs(bbsseq) > 0;
	}
	
	public boolean updateBbs(BbsDto dto) {
		return dao.updateBbs(dto) > 0;
	}
	
	public boolean updateBbsComment(BbsCommentDto dto) {
		return dao.updateBbsComment(dto) > 0;
	}
	
	public boolean deleteBbsComment(int commentseq) {
		return dao.deleteBbsComment(commentseq) > 0;
	}
	
	public boolean readcountUp(int bbsseq) {
		return dao.readcountUp(bbsseq) > 0;
	}
}
