package com.mul.HealthyGYM.Dto;

public class BbsCommentParam {
	
	private int bbsseq;
	private int page;
	private int start;
	private int end;
	
	public BbsCommentParam() {}
	
	public BbsCommentParam(int bbsseq, int page, int start, int end) {
		super();
		this.bbsseq = bbsseq;
		this.page = page;
		this.start = start;
		this.end = end;
	}

	public int getBbsseq() {
		return bbsseq;
	}

	public void setBbsseq(int bbsseq) {
		this.bbsseq = bbsseq;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "BbsCommentParam [bbsseq=" + bbsseq + ", page=" + page + ", start=" + start + ", end=" + end + "]";
	}
	
}
