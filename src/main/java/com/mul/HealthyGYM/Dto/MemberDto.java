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
    private String mailkey;

	private int isreadcnt;	// 대화 목록당 읽지 않은 메시지의 개수
	private String lastletter;	// 가장 최근의 메시지
	private String wdate;	// 가장 최근 메시지의 시간


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
			String authority, String provider, String mailkey) {
		super();
		this.memberseq = memberseq;
		this.email = email;
		this.pwd = pwd;
		this.profile = profile;
		this.nickname = nickname;
		this.auth = auth;
		this.authority = authority;
		this.provider = provider;
		this.mailkey = mailkey;
	}

    public MemberDto(int memberseq, String email, String pwd, int auth, String profile, String mailkey,
			String nickname, int isreadcnt, String lastletter, String wdate) {
		super();
		this.memberseq = memberseq;
		this.email = email;
		this.pwd = pwd;
		this.auth = auth;
		this.profile = profile;
		this.mailkey = mailkey;
		this.nickname = nickname;
		this.isreadcnt = isreadcnt;
		this.lastletter = lastletter;
		this.wdate = wdate;
	}



	public int getIsreadcnt() {
		return isreadcnt;
	}

	public void setIsreadcnt(int isreadcnt) {
		this.isreadcnt = isreadcnt;
	}

	public String getLastletter() {
		return lastletter;
	}

	public void setLastletter(String lastletter) {
		this.lastletter = lastletter;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
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
				+ ", mailkey=" + mailkey + "]";
	}

}
