package com.mul.HealthyGYM.Dto;

public class RefreshTokenDto {
	private String email;
	private String rvalue;
	
	public RefreshTokenDto() {}

	public RefreshTokenDto(String email, String rvalue) {
		super();
		this.email = email;
		this.rvalue = rvalue;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRvalue() {
		return rvalue;
	}

	public void setRvalue(String rvalue) {
		this.rvalue = rvalue;
	}

	@Override
	public String toString() {
		return "RefreshTokenDto [email=" + email + ", rvalue=" + rvalue + "]";
	}

}
