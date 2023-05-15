package com.mul.HealthyGYM.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mul.HealthyGYM.Dto.BbsCommentDto;
import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Dto.FoodDto;
import com.mul.HealthyGYM.Dto.LikeDto;
import com.mul.HealthyGYM.Dto.MemberDto;

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
	public String getnickname(int memberseq);
	public BbsDto selectBbsDto(Map<String, Integer> params);
	public int updatemeal1(BbsDto bbsdto);
	public int updatemeal2(FoodDto foodDto);
	public void deletemeal(int bbsseq);
	public int deletemealcomment(int commentseq);
	public String getbbsprofile(int bbsseq);
}


