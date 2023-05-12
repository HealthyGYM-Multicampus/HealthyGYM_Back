package com.mul.HealthyGYM.Dto;

public class InbodyDto {

    private int inbodyseq;
    private int memberseq;
    private String uploaddate;
    private double weight;
    private double musclemass;
    private double bodyfatmass;
    private String imgpath;
    private String newimgpath;
    private int bodycomseq;

    public InbodyDto() {
    }

    public InbodyDto(int memberseq, double weight, double musclemass, double bodyfatmass, String imgpath) {
        this.memberseq = memberseq;
        this.weight = weight;
        this.musclemass = musclemass;
        this.bodyfatmass = bodyfatmass;
        this.imgpath = imgpath;
    }

    public InbodyDto(int inbodyseq, int memberseq, String uploaddate, double weight, double musclemass, double bodyfatmass, String imgpath, String newimgpath, int bodycomseq) {
        this.inbodyseq = inbodyseq;
        this.memberseq = memberseq;
        this.uploaddate = uploaddate;
        this.weight = weight;
        this.musclemass = musclemass;
        this.bodyfatmass = bodyfatmass;
        this.imgpath = imgpath;
        this.newimgpath = newimgpath;
        this.bodycomseq = bodycomseq;
    }


    public int getInbodyseq() {
        return inbodyseq;
    }

    public void setInbodyseq(int inbodyseq) {
        this.inbodyseq = inbodyseq;
    }

    public int getMemberseq() {
        return memberseq;
    }

    public void setMemberseq(int memberseq) {
        this.memberseq = memberseq;
    }

    public String getUploaddate() {
        return uploaddate;
    }

    public void setUploaddate(String uploaddate) {
        this.uploaddate = uploaddate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getMusclemass() {
        return musclemass;
    }

    public void setMusclemass(double musclemass) {
        this.musclemass = musclemass;
    }

    public double getBodyfatmass() {
        return bodyfatmass;
    }

    public void setBodyfatmass(double bodyfatmass) {
        this.bodyfatmass = bodyfatmass;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getNewimgpath() {
        return newimgpath;
    }

    public void setNewimgpath(String newimgpath) {
        this.newimgpath = newimgpath;
    }


    public int getBodycomseq() {
        return bodycomseq;
    }

    public void setBodycomseq(int bodycomseq) {
        this.bodycomseq = bodycomseq;
    }

    @Override
    public String toString() {
        return "InbodyDto{" +
                "inbodyseq=" + inbodyseq +
                ", memberseq=" + memberseq +
                ", uploaddate='" + uploaddate + '\'' +
                ", weight=" + weight +
                ", musclemass=" + musclemass +
                ", bodyfatmass=" + bodyfatmass +
                ", imgpath='" + imgpath + '\'' +
                ", newimgpath='" + newimgpath + '\'' +
                ", bodycomseq=" + bodycomseq +
                '}';
    }
}
