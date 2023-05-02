package com.mul.HealthyGYM.Dto;

import java.io.Serializable;

public class BbsParam implements Serializable {
	
	private int bbstag;
	private String order;
	private int page;
	private int start;
	private int end;
	
	public BbsParam() {}
	
	public BbsParam(int bbstag, String order, int page, int start, int end) {
		super();
		this.bbstag = bbstag;
		this.order = order;
		this.page = page;
		this.start = start;
		this.end = end;
	}
	
	public int getBbstag() {
		return bbstag;
	}
	public void setBbstag(int bbstag) {
		this.bbstag = bbstag;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
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
		return "BbsParam [bbstag=" + bbstag + ", order=" + order + ", page=" + page + ", start=" + start + ", end="
				+ end + "]";
	}
	
}
