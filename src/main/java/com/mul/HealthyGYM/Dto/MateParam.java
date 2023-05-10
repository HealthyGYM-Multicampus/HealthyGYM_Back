package com.mul.HealthyGYM.Dto;

import java.util.Arrays;

public class MateParam {
	
	private int addressfirst;
	private int addresssecond;
	private String mdate;
	private String mtime;
	private String[] bodypart;
	private int page;
	private int start;
	private int end;
	
	public MateParam() {}

	public MateParam(int addressfirst, int addresssecond, String mdate, String mtime, String[] bodypart,
			int page, int start, int end) {
		super();
		this.addressfirst = addressfirst;
		this.addresssecond = addresssecond;
		this.mdate = mdate;
		this.mtime = mtime;
		this.bodypart = bodypart;
		this.page = page;
		this.start = start;
		this.end = end;
	}

	public int getAddressfirst() {
		return addressfirst;
	}

	public void setAddressfirst(int addressfirst) {
		this.addressfirst = addressfirst;
	}

	public int getAddresssecond() {
		return addresssecond;
	}

	public void setAddresssecond(int addresssecond) {
		this.addresssecond = addresssecond;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	public String[] getBodypart() {
		return bodypart;
	}

	public void setBodypart(String[] bodypart) {
		this.bodypart = bodypart;
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
		return "MateParam [addressfirst=" + addressfirst + ", addresssecond=" + addresssecond
				+ ", mdate=" + mdate + ", mtime=" + mtime + ", bodypart=" + Arrays.toString(bodypart) + ", page=" + page
				+ ", start=" + start + ", end=" + end + "]";
	}
	
}
