package com.mul.HealthyGYM.Dto;

public class MemberDto {
    private int memberSeq;
    private String email;
    private String pwd;
    private int auth;
    private String profile;
    private int mailAuth;
    private String mailKey;
    private String nickname;

    public int getMemberSeq() {
        return memberSeq;
    }

    public void setMemberSeq(int memberSeq) {
        this.memberSeq = memberSeq;
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

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getMailAuth() {
        return mailAuth;
    }

    public void setMailAuth(int mailAuth) {
        this.mailAuth = mailAuth;
    }

    public String getMailKey() {
        return mailKey;
    }

    public void setMailKey(String mailKey) {
        this.mailKey = mailKey;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
