package com.mul.HealthyGYM.Dto;

import java.io.Serializable;

public class ImgDto implements Serializable{
	
	private int imgfile_seq;
	private String imgfile_name;
	private String imgfile_path;
	
	public ImgDto() {}

	public ImgDto(String imgfile_name) {
		this.imgfile_name = imgfile_name;
	}

	public ImgDto(int imgfile_seq, String imgfile_name, String imgfile_path) {
		super();
		this.imgfile_seq = imgfile_seq;
		this.imgfile_name = imgfile_name;
		this.imgfile_path = imgfile_path;
	}

	public int getImgfile_seq() {
		return imgfile_seq;
	}

	public void setImgfile_seq(int imgfile_seq) {
		this.imgfile_seq = imgfile_seq;
	}

	public String getImgfile_name() {
		return imgfile_name;
	}

	public void setImgfile_name(String imgfile_name) {
		this.imgfile_name = imgfile_name;
	}

	public String getImgfile_path() {
		return imgfile_path;
	}

	public void setImgfile_path(String imgfile_path) {
		this.imgfile_path = imgfile_path;
	}

	@Override
	public String toString() {
		return "ImgDto [imgfile_seq=" + imgfile_seq + ", imgfile_name=" + imgfile_name + ", imgfile_path="
				+ imgfile_path + "]";
	}
	
}
