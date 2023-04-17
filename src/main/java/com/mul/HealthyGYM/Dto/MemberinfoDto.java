package com.mul.HealthyGYM.Dto;

public class MemberinfoDto {
    private String memberseq;
    private String name;
    private String age;
    private int gender;
    private String phone;
    private String mbti;

    public String getMemberseq() {
        return memberseq;
    }

    public void setMemberseq(String memberseq) {
        this.memberseq = memberseq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }
}