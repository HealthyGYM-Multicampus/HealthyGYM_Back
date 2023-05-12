package com.mul.HealthyGYM.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mul.HealthyGYM.Dto.BbsCommentDto;
import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Dto.BbsParam;
import com.mul.HealthyGYM.Dto.LikeDto;

@Mapper
@Repository
public interface BodyGalleryDao {

	// 전체 게시글 조회
	List<Map<String, Object>> findAllBody(BbsParam param);
	
	// 게시글 작성
	int saveBody(BbsDto dto);
	
	// 게시글 수정
	int updateBody(BbsDto dto);
	
	// 게시글 삭제
	int deleteBodyById(int bbsseq);
	
	// 상세 게시글 조회
	Map<String, Object> findBodyById(int bbsseq);
	
	// 조회수
	int updateBodyReadcount(int bbsseq);
	
	// 신고
	void updateBodyReport(int bbsseq);
	
	// 좋아요
	int findBodyLike(LikeDto like);
	void saveBodyLike(LikeDto like);
	void deleteBodyLike(LikeDto like);
	void updateBodyLikecount(int bbsseq);
	
	// 대댓글 확인 필요 -> 에러 가능성 있음
	// dao에만 작성하고 댓글 확인 후 service까지 구현해보기
	// 댓글 작성
	int saveBodyComment(BbsCommentDto cmt);
	
	// 대댓글 작성
	int saveBodyReply(BbsCommentDto cmt);
	
	// 댓글 리스트 조회
	List<Map<String, Object>> findAllBodyComment();
	
	// 댓글 수정
	int updateBodyComment(BbsCommentDto cmt);
	
	// 댓글 삭제 시 대댓글이 있는 경우 댓글 수정
	
}
