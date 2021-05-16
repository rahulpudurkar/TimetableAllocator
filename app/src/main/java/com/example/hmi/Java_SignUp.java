package com.example.hmi;

public class Java_SignUp {
    private String name;
    private String phone;
    private String subject;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Java_SignUp(String name,String subject, String phone){
        this.name = name;
        this.subject = subject;
        this.phone = phone;


    }
    public Java_SignUp()
    {

    }

}

