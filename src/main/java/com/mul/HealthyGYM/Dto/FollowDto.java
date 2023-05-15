package com.mul.HealthyGYM.Dto;

public class FollowDto {
    private int memberseq;
    private String foltarget;
    private String profile;
    private String gender;
    private String mbti;

    public FollowDto() {
    }

    public FollowDto(int memberseq, String foltarget, String profile, String gender, String mbti) {
        this.memberseq = memberseq;
        this.foltarget = foltarget;
        this.profile = profile;
        this.gender = gender;
        this.mbti = mbti;
    }

    public int getMemberseq() {
        return memberseq;
    }

    public void setMemberseq(int memberseq) {
        this.memberseq = memberseq;
    }

    public String getFoltarget() {
        return foltarget;
    }

    public void setFoltarget(String foltarget) {
        this.foltarget = foltarget;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    @Override
    public String toString() {
        return "FollowDto{" +
                "memberseq=" + memberseq +
                ", foltarget='" + foltarget + '\'' +
                ", profile='" + profile + '\'' +
                ", gender='" + gender + '\'' +
                ", mbti='" + mbti + '\'' +
                '}';
    }
}
