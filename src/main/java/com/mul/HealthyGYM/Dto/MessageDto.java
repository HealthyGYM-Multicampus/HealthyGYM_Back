package com.mul.HealthyGYM.Dto;

public class MessageDto {

	private int msgseq;
	private int memberseq;
	private int target;
	private String message;
	private int isread;
	private String wdate;
	
	
	public MessageDto() {
		super();
	}

	public MessageDto(int msgseq, int memberseq, int target, String message, int isread, String wdate) {
		super();
		this.msgseq = msgseq;
		this.memberseq = memberseq;
		this.target = target;
		this.message = message;
		this.isread = isread;
		this.wdate = wdate;
	}	// 메시지 얻어오기 위한 dto



	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getMsgseq() {
		return msgseq;
	}

	public void setMsgseq(int msgseq) {
		this.msgseq = msgseq;
	}

	public int getMemberseq() {
		return memberseq;
	}

	public void setMemberseq(int memberseq) {
		this.memberseq = memberseq;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getIsread() {
		return isread;
	}

	public void setIsread(int isread) {
		this.isread = isread;
	}

	@Override
	public String toString() {
		return "MessageDto [msgseq=" + msgseq + ", memberseq=" + memberseq + ", target=" + target + ", message="
				+ message + ", isread=" + isread + ", wdate=" + wdate + "]";
	}

	
	
	
}
