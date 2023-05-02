package com.mul.HealthyGYM.Dto;

public class BbsCommentDto {
	
	private int commentseq;
	private int memberseq;
	private int bbsseq;
	private String cmtcontent;
	private String regdate;
	private int cmtdel;
	private int ref;
	private int step;
	
	public BbsCommentDto() {}
	
	public BbsCommentDto(int memberseq, int bbsseq, String cmtcontent) {
		super();
		this.memberseq = memberseq;
		this.bbsseq = bbsseq;
		this.cmtcontent = cmtcontent;
	}

	public BbsCommentDto(int memberseq, int bbsseq, String cmtcontent, int ref, int step) {
		super();
		this.memberseq = memberseq;
		this.bbsseq = bbsseq;
		this.cmtcontent = cmtcontent;
		this.ref = ref;
		this.step = step;
	}

	public BbsCommentDto(int commentseq, int memberseq, int bbsseq, String cmtcontent, String regdate, int cmtdel,
			int ref) {
		super();
		this.commentseq = commentseq;
		this.memberseq = memberseq;
		this.bbsseq = bbsseq;
		this.cmtcontent = cmtcontent;
		this.regdate = regdate;
		this.cmtdel = cmtdel;
		this.ref = ref;
	}

	public int getCommentseq() {
		return commentseq;
	}

	public void setCommentseq(int commentseq) {
		this.commentseq = commentseq;
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

	public String getCmtcontent() {
		return cmtcontent;
	}

	public void setCmtcontent(String cmtcontent) {
		this.cmtcontent = cmtcontent;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getCmtdel() {
		return cmtdel;
	}

	public void setCmtdel(int cmtdel) {
		this.cmtdel = cmtdel;
	}

	public int getRef() {
		return ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	@Override
	public String toString() {
		return "BbsCommentDto [commentseq=" + commentseq + ", memberseq=" + memberseq + ", bbsseq=" + bbsseq
				+ ", cmtcontent=" + cmtcontent + ", regdate=" + regdate + ", cmtdel=" + cmtdel + ", ref=" + ref + "]";
	}
	
}
