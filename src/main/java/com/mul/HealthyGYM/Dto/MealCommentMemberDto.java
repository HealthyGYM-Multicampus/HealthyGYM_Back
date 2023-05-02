package com.mul.HealthyGYM.Dto;

public class MealCommentMemberDto {


	BbsCommentDto bbscommentdto;
	MemberDto memberdto;
	
	public MealCommentMemberDto() {
		super();
	}

	public MealCommentMemberDto(BbsCommentDto bbscommentdto, MemberDto memberdto) {
		super();
		this.bbscommentdto = bbscommentdto;
		this.memberdto = memberdto;
	}

	public BbsCommentDto getBbscommentdto() {
		return bbscommentdto;
	}

	public void setBbscommentdto(BbsCommentDto bbscommentdto) {
		this.bbscommentdto = bbscommentdto;
	}

	public MemberDto getMemberdto() {
		return memberdto;
	}

	public void setMemberdto(MemberDto memberdto) {
		this.memberdto = memberdto;
	}

	@Override
	public String toString() {
		return "MealCommentMemberDto [bbscommentdto=" + bbscommentdto + ", memberdto=" + memberdto + "]";
	}

	
	
	
	
	

	
	
	
}
