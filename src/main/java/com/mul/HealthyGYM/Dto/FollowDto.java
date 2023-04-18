package com.mul.HealthyGYM.Dto;

public class FollowDto {
    private int memberseq;
    private String foltarget;

    public FollowDto() {
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

    @Override
    public String toString(){
        return "FollowDto [memberseq=" + memberseq + ", foltarget=" + foltarget+ "]";
    }
}
