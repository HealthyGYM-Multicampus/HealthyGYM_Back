package com.mul.HealthyGYM.Dto;

public class MemberDto {
    private int memberseq;
    private String email;
    private String pwd;
    private int auth;
    private String profile;
    private int mailauth;
    private String mailkey;
    private String nickname;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
