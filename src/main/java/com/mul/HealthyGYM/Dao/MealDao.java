package com.mul.HealthyGYM.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mul.HealthyGYM.Dto.BbsCommentDto;
import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Dto.BbsFoodDto;
import com.mul.HealthyGYM.Dto.FoodDto;
import com.mul.HealthyGYM.Dto.LikeDto;
import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.ProfileDto;

@Mapper
@Repository
public interface MealDao{
	public int allGet();
	public int insertMeal(BbsDto bbsdto);
	public int getLastInsertId();
	public int writemeal2(FoodDto foodDto);
	public List<BbsDto> selectBbsList(Map<String, Object> params);
	public List<FoodDto> selectFoodList(Map<String, Integer> params);
	public int deletemealpost(int bbsseq);
	public int islikeFoodBbs(Map<String, Integer> params);
	public int likemealpostsearch(LikeDto likedto);
	public int likemealpost(LikeDto likedto);
	public int likeupmealCount(LikeDto likedto);
	public int dislikemealpost(LikeDto likedto);
	public int likedownmealCount(LikeDto likedto);
	public int getcmtcnt(int bbsseq);
	public int wrtiemealcomment(HashMap<String, Object> params);
	public List<BbsCommentDto> getmealcomments(int bbsseq);
	public MemberDto getwritorprofile(int memberseq);
	public int writemealcomment2(HashMap<String, Object> params);
}

/*
public class MealDao {

	@Autowired
	SqlSession sqlSession;
	
	String ns = "Mealinfo.";
	
	public int allGet() {
		return sqlSession.selectOne(ns + "allGet");
	}

	public boolean writemeal1(BbsDto bbsdto) {
		// System.out.println(bbsdto.toString());
		return sqlSession.insert(ns + "insertMeal", bbsdto)>0?true:false;
	}

	public boolean writemeal2(List<FoodDto> foodDtoList) {
		int count = 0;
		
		int bbsseq = sqlSession.selectOne(ns + "getLastInsertId");
		// 어떤 게시물에 해당하는건지 글 작성 시 얻어오기.
		
		
		for (FoodDto foodDto : foodDtoList) {
			foodDto.setBbsseq(bbsseq);		// 어떤 게시물에 해당하는 건지 객체마다 설정.
			
			sqlSession.insert(ns + "writemeal2", foodDto);
		}
		
		return count>0?true:false;
	}

	
	public List<BbsFoodDto> getPosts(int offset, int limit, int memberseq) {
	    Map<String, Integer> params = new HashMap<>();
	    params.put("offset", offset);
	    params.put("limit", limit);

	    List<BbsDto> bbslist = sqlSession.selectList(ns + "selectBbsList", params);

	    List<BbsFoodDto> result = new ArrayList<>();

	    for (BbsDto bbs : bbslist) {
	        Map<String, Integer> params2 = new HashMap<>();
	        params2.put("bbsseq", bbs.getBbsseq());
	        List<FoodDto> foodlist = sqlSession.selectList(ns + "selectFoodList", params2);
	        int commentcnt = sqlSession.selectOne(ns + "getcmtcnt", bbs.getBbsseq());
	        // System.out.println(commentcnt);
	        
	        
	        params2.put("memberseq", memberseq);
	        boolean islike = false;		// memberseq가 
	        int n = sqlSession.selectOne(ns + "islikeFoodBbs", params2);
	        if(n==1) {	// 좋아요를 누른경우
	        	islike = true;
	        }
	        
	        BbsFoodDto bbsFoodDto = new BbsFoodDto(bbs, foodlist.toArray(new FoodDto[0]), islike, commentcnt);
	        // System.out.println(bbsFoodDto.toString());
	        result.add(bbsFoodDto);
	    }
	    
	    // System.out.println(result.toString());

	    return result;    
	}

	public int likemealpost(int bbsseq, int memberseq) {
		// 객체 만들기
		LikeDto like = new LikeDto(bbsseq, memberseq);
		// System.out.println(like.toString());
		
		int count = 0;	// like 혹은 unlike 수행했는지 확인하기 위한 용도.
		
		int n = sqlSession.selectOne(ns + "likemealpostsearch", like);	// 현재 좋아요를 누른상태인지 확인하기 위한 용도.
		// System.out.println(n);
		
		if(n==0) {
			// 해당 게시물에대한 좋아요가 처음이거나, 없는 경우. insert into like bbs 테이블 // user_id와 bbs_seq
			// // 그리고, post테이블의 bbs_seq가 일치하는 것 likecount + 1
			count = sqlSession.insert(ns + "likemealpost", like);
			count += sqlSession.update(ns + "likeupmealCount", like);
		} else {
			// 좋아요가 이미 되어있는 경우
			count = sqlSession.delete(ns + "dislikemealpost", like);
			count += sqlSession.update(ns + "likedownmealCount", like);
		}
		
		
		
		return count;
	}

	public int deletemealpost(int bbsseq) {
		int n = 0;
		n = sqlSession.delete(ns + "deletemealpost", bbsseq);
		return n;
	}

	public boolean wrtiemealcomment(int bbsseq, int memberseq, String commentcontent) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("memberseq", memberseq);
		params.put("bbsseq", bbsseq); 
		params.put("cmtcontent", commentcontent); 
		
		int n = sqlSession.insert(ns + "wrtiemealcomment", params);
		return n>0?true:false;
	}

	
	public List<BbsCommentDto> getmealcomments(int bbsseq) {
		
		List<BbsCommentDto> commentlist = sqlSession.selectList(ns + "getmealcomments", bbsseq);
		
		return commentlist;
	}
	
	
	


	
}*/
