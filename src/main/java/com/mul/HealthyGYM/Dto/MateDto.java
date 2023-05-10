package com.mul.HealthyGYM.Dto;

public class MateDto {
	
	private int bbsseq;
	private String addressfirst;
	private String addresssecond;
	private String center;
	private String mdate;
	private String mtime;
	
	private boolean back;
	private boolean chest;
	private boolean shoulder;
	private boolean arm;
	private boolean abs;
	private boolean leg;
	private boolean run;
	
	public MateDto() {}

	public MateDto(int bbsseq, String addressfirst, String addresssecond, String center, String mdate, String mtime,
			boolean back, boolean chest, boolean shoulder, boolean arm, boolean abs, boolean leg, boolean run) {
		super();
		this.bbsseq = bbsseq;
		this.addressfirst = addressfirst;
		this.addresssecond = addresssecond;
		this.center = center;
		this.mdate = mdate;
		this.mtime = mtime;
		this.back = back;
		this.chest = chest;
		this.shoulder = shoulder;
		this.arm = arm;
		this.abs = abs;
		this.leg = leg;
		this.run = run;
	}

	public int getBbsseq() {
		return bbsseq;
	}

	public void setBbsseq(int bbsseq) {
		this.bbsseq = bbsseq;
	}

	public String getAddressfirst() {
		return addressfirst;
	}

	public void setAddressfirst(String addressfirst) {
		this.addressfirst = addressfirst;
	}

	public String getAddresssecond() {
		return addresssecond;
	}

	public void setAddresssecond(String addresssecond) {
		this.addresssecond = addresssecond;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
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

	public boolean isBack() {
		return back;
	}

	public void setBack(boolean back) {
		this.back = back;
	}

	public boolean isChest() {
		return chest;
	}

	public void setChest(boolean chest) {
		this.chest = chest;
	}

	public boolean isShoulder() {
		return shoulder;
	}

	public void setShoulder(boolean shoulder) {
		this.shoulder = shoulder;
	}

	public boolean isArm() {
		return arm;
	}

	public void setArm(boolean arm) {
		this.arm = arm;
	}

	public boolean isAbs() {
		return abs;
	}

	public void setAbs(boolean abs) {
		this.abs = abs;
	}

	public boolean isLeg() {
		return leg;
	}

	public void setLeg(boolean leg) {
		this.leg = leg;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	@Override
	public String toString() {
		return "MateDto [bbsseq=" + bbsseq + ", addressfirst=" + addressfirst + ", addresssecond=" + addresssecond
				+ ", center=" + center + ", mdate=" + mdate + ", mtime=" + mtime + ", back=" + back + ", chest=" + chest
				+ ", shoulder=" + shoulder + ", arm=" + arm + ", abs=" + abs + ", leg=" + leg + ", run=" + run + "]";
	}
	
}
