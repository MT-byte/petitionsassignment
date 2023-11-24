package com.assignment.maryteresaspetitions;


// This class implements the petition data structure. The member variables include
//     num: the unique identifier of the petition
//     title: the title of the petition
//     desc: the description field of the petition
//     signedBy: the filed that stores the names of people that signed the petition
//     email: this field stores the emails of the people that signed the petition
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
