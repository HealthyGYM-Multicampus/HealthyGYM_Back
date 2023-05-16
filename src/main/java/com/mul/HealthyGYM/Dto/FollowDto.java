package com.mul.HealthyGYM.Dto;

public class FollowDto {
    private int followseq;
    private int memberseq;
    private String foltarget;
    private String profile;
    private String gender;
    private String mbti;


    public FollowDto(int followseq, int memberseq, String foltarget, String profile, String gender, String mbti) {
        this.followseq = followseq;
        this.memberseq = memberseq;
        this.foltarget = foltarget;
        this.profile = profile;
        this.gender = gender;
        this.mbti = mbti;
    }

    public FollowDto() {
    }

    public int getFollowseq() {
        return followseq;
    }

    public void setFollowseq(int followseq) {
        this.followseq = followseq;
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
                "followseq=" + followseq +
                ", memberseq=" + memberseq +
                ", foltarget='" + foltarget + '\'' +
                ", profile='" + profile + '\'' +
                ", gender='" + gender + '\'' +
                ", mbti='" + mbti + '\'' +
                '}';
    }
}
