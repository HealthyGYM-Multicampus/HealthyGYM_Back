package com.mul.HealthyGYM.Dto;

public class MemberinfoDto {
    private String memberseq;
    private String name;
    private String age;
    private String gender;
    private String phone;
    private String mbti;

    public MemberinfoDto() {
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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