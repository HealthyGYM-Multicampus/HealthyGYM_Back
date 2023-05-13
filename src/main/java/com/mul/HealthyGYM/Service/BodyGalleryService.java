package com.mul.HealthyGYM.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mul.HealthyGYM.Dao.BodyGalleryDao;
import com.mul.HealthyGYM.Dto.BbsCommentDto;
import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Dto.BbsParam;
import com.mul.HealthyGYM.Dto.LikeDto;

@Service
@Transactional
public class BodyGalleryService {
	
	@Autowired
	BodyGalleryDao dao;
	
	public List<Map<String, Object>> findAllBody(BbsParam param) {
		return dao.findAllBody(param);
	}
	
	public boolean saveBody(BbsDto dto) {
		int count = dao.saveBody(dto);
		return count>0?true:false;
	}
	
	public boolean updateBody(BbsDto dto) {
		int count = dao.updateBody(dto);
		return count>0?true:false;
	}
	
	public boolean deleteBodyById(int bbsseq) {
		int count = dao.deleteBodyById(bbsseq);
		return count>0?true:false;
	}
	
	public Map<String, Object> findBodyById(int bbsseq) {
		return dao.findBodyById(bbsseq);
	}
	
	public boolean updateBodyReadcount(int bbsseq) {
		int count = dao.updateBodyReadcount(bbsseq);
		return count>0?true:false;
	}
	
	public void insertBodyLike(LikeDto like) {
		dao.saveBodyLike(like);
		dao.updateBodyLikecount(like.getBbsseq());
	}
	
	public void deleteBodyLike(LikeDto like) {
		dao.deleteBodyLike(like);
		dao.updateBodyLikecount(like.getBbsseq());
	}
	
	public int getBodyLike(LikeDto like) {
		return dao.findBodyLike(like);
	}
	

}
