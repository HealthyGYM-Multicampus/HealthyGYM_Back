package com.mul.HealthyGYM.Dto;

import java.util.Arrays;

public class BbsFoodDto {
	BbsDto Bbsdto;
	FoodDto[] Fooddto;
	boolean islike;	// 현재 로그인한 사람이 좋아요를 누른 게시물인지 파악하기 위한 초기 렌더링용
	int commentcnt; // 게시물당 코멘트의 개수를 파악하기 위한 용도
	
	
	public BbsFoodDto() {
		super();
	}
	public BbsFoodDto(BbsDto bbsdto, FoodDto[] fooddto) {
		super();
		Bbsdto = bbsdto;
		Fooddto = fooddto;
	}
	
//	public BbsFoodDto(BbsDto bbsdto, FoodDto[] fooddto, boolean islike) {
//		super();
//		Bbsdto = bbsdto;
//		Fooddto = fooddto;
//		this.islike = islike;
//	}
	
	public BbsFoodDto(BbsDto bbsdto, FoodDto[] fooddto, boolean islike, int commentcnt) {
		super();
		Bbsdto = bbsdto;
		Fooddto = fooddto;
		this.islike = islike;
		this.commentcnt = commentcnt;
	}
	
	public int getCommentcnt() {
		return commentcnt;
	}
	public void setCommentcnt(int commentcnt) {
		this.commentcnt = commentcnt;
	}
	public boolean isIslike() {
		return islike;
	}
	public void setIslike(boolean islike) {
		this.islike = islike;
	}
	public BbsDto getBbsdto() {
		return Bbsdto;
	}
	public void setBbsdto(BbsDto bbsdto) {
		Bbsdto = bbsdto;
	}
	public FoodDto[] getFooddto() {
		return Fooddto;
	}
	public void setFooddto(FoodDto[] fooddto) {
		Fooddto = fooddto;
	}
	
	@Override
	public String toString() {
		return "BbsFoodDto [Bbsdto=" + Bbsdto + ", Fooddto=" + Arrays.toString(Fooddto) + ", islike=" + islike
				+ ", commentcnt=" + commentcnt + "]";
	}
	

	

	
	
	
	
}
