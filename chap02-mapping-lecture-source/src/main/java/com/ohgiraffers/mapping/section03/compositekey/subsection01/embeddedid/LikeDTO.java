package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedid;

public class LikeDTO {

    private int likedMemberNo;
    private int likedaBookNo;

    public LikeDTO(int likedMemberNo, int likedaBookNo) {
        this.likedMemberNo = likedMemberNo;
        this.likedaBookNo = likedaBookNo;
    }

    public int getLikedMemberNo() {
        return likedMemberNo;
    }

    public int getLikedaBookNo() {
        return likedaBookNo;
    }

    @Override
    public String toString() {
        return "LikeDTO{" +
                "likedMemberNo=" + likedMemberNo +
                ", likedaBookNo=" + likedaBookNo +
                '}';
    }
}
