package com.mul.HealthyGYM.Dto;

import java.io.Serializable;

public class BbsDto implements Serializable {
	
	private int bbsseq;
	private int memberseq;
	private String title;
	private String content;
	private int bbstag;
	private String wdate;
	private String rdate;
	
	private String nickname;
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	private int likecount;
	private int readcount;
	private int ref;
	private int del;
	
	private String thumnail;
	private int report;
	
	public BbsDto() {}

	public BbsDto(int memberseq, String title, String content, int bbstag) {
		super();
		this.memberseq = memberseq;
		this.title = title;
		this.content = content;
		this.bbstag = bbstag;
	}

	public BbsDto(int bbsseq, int memberseq, String title, String content, int bbstag, String wdate, String rdate,
			String nickname, int likecount, int readcount, int ref, int del, String thumnail, int report) {
		super();
		this.bbsseq = bbsseq;
		this.memberseq = memberseq;
		this.title = title;
		this.content = content;
		this.bbstag = bbstag;
		this.wdate = wdate;
		this.rdate = rdate;
		this.nickname = nickname;
		this.likecount = likecount;
		this.readcount = readcount;
		this.ref = ref;
		this.del = del;
		this.thumnail = thumnail;
		this.report = report;
	}

	public BbsDto(int bbsseq, int memberseq, String title, String content, int bbstag, String wdate, String rdate,
			int likecount, int readcount, int ref, int del, String thumnail, int report) {
		super();
		this.bbsseq = bbsseq;
		this.memberseq = memberseq;
		this.title = title;
		this.content = content;
		this.bbstag = bbstag;
		this.wdate = wdate;
		this.rdate = rdate;
		this.likecount = likecount;
		this.readcount = readcount;
		this.ref = ref;
		this.del = del;
		this.thumnail = thumnail;
		this.report = report;
	}

	public int getBbsseq() {
		return bbsseq;
	}

	public void setBbsseq(int bbsseq) {
		this.bbsseq = bbsseq;
	}

	public int getMemberseq() {
		return memberseq;
	}

	public void setMemberseq(int memberseq) {
		this.memberseq = memberseq;
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

	public int getBbstag() {
		return bbstag;
	}

	public void setBbstag(int bbstag) {
		this.bbstag = bbstag;
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
		return "BbsDto [bbsseq=" + bbsseq + ", memberseq=" + memberseq + ", title=" + title + ", content=" + content
				+ ", bbstag=" + bbstag + ", wdate=" + wdate + ", rdate=" + rdate + ", likecount=" + likecount
				+ ", readcount=" + readcount + ", ref=" + ref + ", del=" + del + ", thumnail=" + thumnail + ", report="
				+ report + "]";
	}

}
