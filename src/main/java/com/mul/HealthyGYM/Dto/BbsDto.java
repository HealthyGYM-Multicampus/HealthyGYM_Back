package com.mul.HealthyGYM.Dto;

import java.io.Serializable;

public class BbsDto implements Serializable {
	
	private int member_seq;
	private String title;
	private String content;
	private int bbs_tag;
	private String wdate;
	private String rdate;
	
	private int likecount;
	private int readcount;
	private int ref;
	private int del;
	
	private String thumnail;
	private int report;
	
	public BbsDto() {}

	public BbsDto(int member_seq, String title, String content, int bbs_tag) {
		this.member_seq = member_seq;
		this.title = title;
		this.content = content;
		this.bbs_tag = bbs_tag;
	}

	public BbsDto(int member_seq, String title, String content, int bbs_tag, String wdate, String rdate, int likecount,
			int readcount, int ref, int del, String thumnail, int report) {
		super();
		this.member_seq = member_seq;
		this.title = title;
		this.content = content;
		this.bbs_tag = bbs_tag;
		this.wdate = wdate;
		this.rdate = rdate;
		this.likecount = likecount;
		this.readcount = readcount;
		this.ref = ref;
		this.del = del;
		this.thumnail = thumnail;
		this.report = report;
	}

	public int getMember_seq() {
		return member_seq;
	}

	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getBbs_tag() {
		return bbs_tag;
	}

	public void setBbs_tag(int bbs_tag) {
		this.bbs_tag = bbs_tag;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public int getLikecount() {
		return likecount;
	}

	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public String getThumnail() {
		return thumnail;
	}

	public void setThumnail(String thumnail) {
		this.thumnail = thumnail;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}

	@Override
	public String toString() {
		return "BbsDto [member_seq=" + member_seq + ", title=" + title + ", content=" + content + ", bbs_tag=" + bbs_tag
				+ ", wdate=" + wdate + ", rdate=" + rdate + ", likecount=" + likecount + ", readcount=" + readcount
				+ ", ref=" + ref + ", del=" + del + ", thumnail=" + thumnail + ", report=" + report + "]";
	}
	
}
