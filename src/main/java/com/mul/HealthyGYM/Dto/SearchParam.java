package com.mul.HealthyGYM.Dto;

import java.io.Serializable;

public class SearchParam implements Serializable{
	
	private int bbstag;
	private String order;
	private String search;
	private int page;
	private int start;
	private int end;
	
	public SearchParam() {
		
	}

	public SearchParam(int bbstag, String order, String search, int page, int start, int end) {
		super();
		this.bbstag = bbstag;
		this.order = order;
		this.search = search;
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
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
		return "SearchParam [bbstag=" + bbstag + ", order=" + order + ", search=" + search + ", page=" + page
				+ ", start=" + start + ", end=" + end + "]";
	}
	
}
