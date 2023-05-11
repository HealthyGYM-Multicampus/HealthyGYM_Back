package com.mul.HealthyGYM.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mul.HealthyGYM.Dao.MealDao;
import com.mul.HealthyGYM.Dto.BbsCommentDto;
import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Dto.BbsFoodDto;
import com.mul.HealthyGYM.Dto.FoodDto;
import com.mul.HealthyGYM.Dto.LikeDto;
import com.mul.HealthyGYM.Dto.MealCommentMemberDto;
import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.ProfileDto;

@Service
@Transactional
public class MealService {

	@Autowired
	private MealDao mealDao;

	
	public int allGet() {
		return mealDao.allGet();
	}


	public int writemeal1(BbsDto bbsdto) {
		mealDao.insertMeal(bbsdto);
		int bbsseq = bbsdto.getBbsseq();
		
		return bbsseq;
	}


	public boolean writemeal2(List<FoodDto> foodDtoList, int bbsseq) {
		int count = 0;
		System.out.println("writemeal2: bbsseq = " + bbsseq);
		
		for (FoodDto foodDto : foodDtoList) {
			foodDto.setBbsseq(bbsseq);		// 어떤 게시물에 해당하는 건지 객체마다 설정.
			
			count += mealDao.writemeal2(foodDto);
			System.out.println(foodDto.toString());
		}
		System.out.println(count + " : count");
		return count>0?true:false;
	}


	public List<BbsFoodDto> getPosts(int offset, int limit, int memberseq, String search, String select) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("offset", offset*5);
	    params.put("limit", limit);
	    params.put("search", search);
	    params.put("select", select);
	    params.put("memberseq", memberseq);
	    
	    
	    List<BbsDto> bbslist = mealDao.selectBbsList(params);
	    
	    List<BbsFoodDto> result = new ArrayList<>();
		
	    for (BbsDto bbs : bbslist) {
	        Map<String, Integer> params2 = new HashMap<>();
	        params2.put("bbsseq", bbs.getBbsseq());
	        List<FoodDto> foodlist = mealDao.selectFoodList(params2);
	        int commentcnt = mealDao.getcmtcnt(bbs.getBbsseq());
	        // System.out.println(commentcnt);
	        
	        
	        params2.put("memberseq", memberseq);
	        boolean islike = false;		// memberseq가 
	        int n = mealDao.islikeFoodBbs(params2);
	        if(n==1) {	// 좋아요를 누른경우
	        	islike = true;
	        }
	        
	        BbsFoodDto bbsFoodDto = new BbsFoodDto(bbs, foodlist.toArray(new FoodDto[0]), islike, commentcnt);
	        // System.out.println(bbsFoodDto.toString());
	        result.add(bbsFoodDto);
	    }
	    
	    return result;
	}


	public boolean likemealpost(int bbsseq, int memberseq) {
		// 객체 만들기
		LikeDto like = new LikeDto(bbsseq, memberseq);
		// System.out.println(like.toString());
		
		int count = 0;	// like 혹은 unlike 수행했는지 확인하기 위한 용도.
		
		int n = mealDao.likemealpostsearch(like);
		// System.out.println(n);
		
		if(n==0) {
			// 해당 게시물에대한 좋아요가 처음이거나, 없는 경우. insert into like bbs 테이블 // user_id와 bbs_seq
			// // 그리고, post테이블의 bbs_seq가 일치하는 것 likecount + 1
			count = mealDao.likemealpost(like);
			count += mealDao.likeupmealCount(like);
		} else {
			// 좋아요가 이미 되어있는 경우
			count = mealDao.dislikemealpost(like);
			count += mealDao.likedownmealCount(like);
		}
		
		return  count>0?true:false;
	}


	public boolean deletemealpost(int bbsseq) {
		
		return mealDao.deletemealpost(bbsseq)>0?true:false;
	}


	public boolean wrtiemealcomment(int bbsseq, int memberseq, String commentcontent) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("memberseq", memberseq);
		params.put("bbsseq", bbsseq); 
		params.put("cmtcontent", commentcontent); 
		
		int n = mealDao.wrtiemealcomment(params);
		return n>0?true:false;
		
	}


	
	public List<MealCommentMemberDto> getmealcomments(int bbsseq, int memberseq) {
		
		List<MealCommentMemberDto> result = new ArrayList<>();
		
		List<BbsCommentDto> commentlist = mealDao.getmealcomments(bbsseq);
		for (BbsCommentDto cmt : commentlist) {
			MemberDto member = mealDao.getwritorprofile(memberseq);
			
			MealCommentMemberDto mealcmtmemdto = new MealCommentMemberDto(cmt, member);
			
			result.add(mealcmtmemdto);
		}
		
		
		

		return result;
	}


	public boolean wrtiemealcomment2(int bbsseq, int memberseq, String commentcontent, int ref) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("memberseq", memberseq);
		params.put("bbsseq", bbsseq); 
		params.put("cmtcontent", commentcontent); 
		params.put("ref", ref);
		
		int n = mealDao.writemealcomment2(params);
		
		return n>0?true:false;
	}



	
	


	
	
	
}
