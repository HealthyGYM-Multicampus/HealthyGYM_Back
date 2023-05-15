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
	
	public void saveBody(BbsDto dto) {
		dao.saveBody(dto);
	}
	
	public void updateBody(BbsDto dto) {
		dao.updateBody(dto);
	}
	
	public void deleteBodyById(int bbsseq) {
		dao.deleteBodyById(bbsseq);
	}
	
	public Map<String, Object> findBodyById(int bbsseq) {
		return dao.findBodyById(bbsseq);
	}
	
	public void updateBodyReadcount(int bbsseq) {
		dao.updateBodyReadcount(bbsseq);
	}
	
	public int findBodyLike(LikeDto likeDto) {
		return dao.findBodyLike(likeDto);
	}

	public void saveBodyLike(LikeDto likeDto) {
		dao.saveBodyLike(likeDto);
		dao.updateBodyLikecount(likeDto.getBbsseq());
	}
	
	public void deleteBodyLike(LikeDto likeDto) {
		dao.deleteBodyLike(likeDto);
		dao.updateBodyLikecount(likeDto.getBbsseq());
	}
	
	public void updateBodyReport(int bbsseq) {
		dao.updateBodyReport(bbsseq);
	}
	
	public void saveBodyComment(Map<String, Object> commentMap) {
		dao.saveBodyComment(commentMap);
	}
	
	public void saveBodyReply(Map<String, Object> replyMap) {
		dao.saveBodyReply(replyMap);
	}
	
	public List<Map<String, Object>> findAllBodyComment(int bbsseq) {
		return dao.findAllBodyComment(bbsseq);
	}
	
	public void updateBodyComment(BbsCommentDto bbsCommentDto) {
		dao.updateBodyComment(bbsCommentDto);
	}
	
	public void deleteCommentWithoutReply(int commentseq) {
		dao.deleteCommentWithoutReply(commentseq);
	}
	
	public void updateComment(Map<String, Object> commentMap) {
		dao.updateComment(commentMap);
	}
	
	public void updateReply(Map<String, Object> replyMap) {
		dao.updateReply(replyMap);
	}
	
	public void deleteAllReplies(int commentseq) {
		dao.deleteAllReplies(commentseq);
	}
}