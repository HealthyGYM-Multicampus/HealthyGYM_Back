package com.mul.HealthyGYM.Dto;

public class LikeDto {
	int memberseq;
	int bbsseq;
	
	// 추가적으로 member가 무엇을 좋아요 하는지 파악에따라 추가하고 생성자 생성하시면 됩니다.
	
	
	public LikeDto() {
		super();
	}

	public LikeDto(int bbsseq, int memberseq) {
		super();
		this.memberseq = memberseq;
		this.bbsseq = bbsseq;
	}

	public int getMemberseq() {
		return memberseq;
	}

	public void setMemberseq(int memberseq) {
		this.memberseq = memberseq;
	}

	public int getBbsseq() {
		return bbsseq;
	}

	public void setBbsseq(int bbsseq) {
		this.bbsseq = bbsseq;
	}

	@Override
	public String toString() {
		return "LikeDto [memberseq=" + memberseq + ", bbsseq=" + bbsseq + "]";
	}
	

	
	
	

}
