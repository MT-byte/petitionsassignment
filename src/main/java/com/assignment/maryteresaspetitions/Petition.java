package com.assignment.maryteresaspetitions;

public class Petition {
    private int num;
    private String title;
    private String desc;
    private String signedby;

    private String email;


    public Petition(int num, String title, String desc, String signedby, String email) {
        this.num = num;
        this.title = title;
        this.desc = desc;
        this.signedby = signedby;
        this.email = email;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSignedby() {
        return signedby;
    }

    public void setSignedby(String signedby) {
        this.signedby = signedby;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Petition{" +
                "num=" + num +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", signedby='" + signedby + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
