package com.mul.HealthyGYM.Dto;

public class UserBbsParam {

    private int bbstag;
    private int memberseq;

    public UserBbsParam() {
    }

    public UserBbsParam(int bbstag, int memberseq) {
        this.bbstag = bbstag;
        this.memberseq = memberseq;
    }

    public int getBbstag() {
        return bbstag;
    }

    public void setBbstag(int bbstag) {
        this.bbstag = bbstag;
    }

    public int getMemberseq() {
        return memberseq;
    }

    public void setMemberseq(int memberseq) {
        this.memberseq = memberseq;
    }

    @Override
    public String toString() {
        return "UserBbsParam{" +
                "bbstag=" + bbstag +
                ", memberseq=" + memberseq +
                '}';
    }
}
