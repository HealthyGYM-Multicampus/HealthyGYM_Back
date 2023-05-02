package com.mul.HealthyGYM.Dto;

public class MemberDto {
    private int memberseq;
    private String email;
    private String pwd;
    private String profile;
    private String nickname;
    private int auth;
    private String authority;
    private String provider;	// own, google, kakao
    private int mailauth;
    private String mailkey;
    
	public MemberDto() {}
	
	public MemberDto(String email, String pwd) {
		super();
		this.email = email;
		this.pwd = pwd;
	}

	public MemberDto(String email, String nickname, String authority, String provider) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.authority = authority;
		this.provider = provider;
	}

	public MemberDto(String email, String pwd, String nickname, String authority, String provider) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.nickname = nickname;
		this.authority = authority;
		this.provider = provider;
	}

	public MemberDto(int memberseq, String email, String pwd, String profile, String nickname, int auth,
			String authority, String provider, int mailauth, String mailkey) {
		super();
		this.memberseq = memberseq;
		this.email = email;
		this.pwd = pwd;
		this.profile = profile;
		this.nickname = nickname;
		this.auth = auth;
		this.authority = authority;
		this.provider = provider;
		this.mailauth = mailauth;
		this.mailkey = mailkey;
	}

	public int getMemberseq() {
		return memberseq;
	}

	public void setMemberseq(int memberseq) {
		this.memberseq = memberseq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public int getMailauth() {
		return mailauth;
	}

	public void setMailauth(int mailauth) {
		this.mailauth = mailauth;
	}

	public String getMailkey() {
		return mailkey;
	}

	public void setMailkey(String mailkey) {
		this.mailkey = mailkey;
	}

	@Override
	public String toString() {
		return "MemberDto [memberseq=" + memberseq + ", email=" + email + ", pwd=" + pwd + ", profile=" + profile
				+ ", nickname=" + nickname + ", auth=" + auth + ", authority=" + authority + ", provider=" + provider
				+ ", mailauth=" + mailauth + ", mailkey=" + mailkey + "]";
	}

}
