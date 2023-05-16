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
	void saveBody(BbsDto dto);
	
	// 게시글 수정
	void updateBody(BbsDto dto);
	
	// 게시글 삭제
	void deleteBodyById(int bbsseq);
	
	// 상세 게시글 조회
	Map<String, Object> findBodyById(int bbsseq);
	
	// 조회수 증가
	void updateBodyReadcount(int bbsseq);
	
	// 좋아요 확인
    int findBodyLike(LikeDto likeDto);
    
    // 좋아요 추가
    void saveBodyLike(LikeDto likeDto);
    
    // 좋아요 취소
    void deleteBodyLike(LikeDto likeDto);
    
    // 좋아요 갯수 업데이트
    void updateBodyLikecount(int bbsseq);
    
    // 신고 기능
    void updateBodyReport(int bbsseq);
    
    // 댓글 작성
    void saveBodyComment(BbsCommentDto bbsCommentDto);
    
    // 대댓글 작성
    void saveBodyReply(BbsCommentDto bbsCommentDto);
    
    // 댓글 조회
    List<Map<String, Object>> findAllBodyComment(int bbsseq);
    
    // 댓글 수정
    void updateBodyComment(BbsCommentDto bbsCommentDto);
    
    // 대댓글 있는 경우 댓글 삭제
    void updateComment(BbsCommentDto bbsCommentDto);
    
    // 대댓글이 없는 경우 댓글 삭제
    void deleteCommentWithoutReply(int commentseq);
    
    // 댓글이 있는 경우 대댓글 삭제
    void updateReply(BbsCommentDto bbsCommentDto);
    
    // 댓글, 대댓글 일괄 삭제
    void deleteAllReplies(int commentseq);
	
}
