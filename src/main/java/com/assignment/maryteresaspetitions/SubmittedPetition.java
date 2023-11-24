package com.assignment.maryteresaspetitions;


// This class implements a data structure required to pass the data (petition title, description,
// name and email of the person that created the petition) between the "view" to "controller"
// of the MVC architecture.
public class SubmittedPetition {
    private String title;
    private String desc;
    private String signedby;
    private String email;

    public SubmittedPetition(String title, String desc, String signedby, String email) {
        this.title = title;
        this.desc = desc;
        this.signedby = signedby;
        this.email = email;
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
        return "SubmittedPetition{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", signedby='" + signedby + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
