package com.assignment.maryteresaspetitions;


// This class implements a data structure required to pass the data (name and email of the person
// that signs the petition) between the "view" to "controller" of the MVC architecture.
public class SignName {

    private String signName;
    private String email;


    public SignName(String signName, String email) {
        this.signName = signName;
        this.email = email;
    }


    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SignName{" +
                "signName='" + signName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
