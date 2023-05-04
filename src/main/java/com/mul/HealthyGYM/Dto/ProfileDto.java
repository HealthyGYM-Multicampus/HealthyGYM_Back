package com.mul.HealthyGYM.Dto;

import org.springframework.web.multipart.MultipartFile;

public class ProfileDto {
    private int memberseq;
    private String email;
    private String nickname;
    private String gender;
    private String name;
    private int age;
    private String phone;
    private String mbti;
    private String profile;

    private MultipartFile image;
    private MultipartFile uploadFile;

    public ProfileDto() {
    }

    public ProfileDto(int memberseq, String email, String nickname, String gender, String name, int age, String phone, String mbti, String profile, MultipartFile image) {
        this.memberseq = memberseq;
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.mbti = mbti;
        this.profile = profile;
        this.image = image;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "ProfileDto{" +
                "memberseq=" + memberseq +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", mbti='" + mbti + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public MultipartFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(MultipartFile uploadFile) {
        this.uploadFile = uploadFile;
    }
}
