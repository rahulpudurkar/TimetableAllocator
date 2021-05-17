package com.example.hmi;

public class Users {

    private String NAME;
    private String EMAIL;
    private String SUBJECT;
    private String SHIFT;
    private String DATE;


    public Users() {
    }

    public Users(String NAME) {
        this.NAME = NAME;
    }

    public Users(String NAME,String SHIFT) {
        this.NAME = NAME;
        this.SHIFT = SHIFT;
    }

    public Users(String NAME, String EMAIL, String SUBJECT) {
        this.NAME = NAME;
        this.EMAIL = EMAIL;
        this.SUBJECT = SUBJECT;
    }

    public Users(String NAME, String SHIFT, String SUBJECT,String DATE) {
        this.NAME = NAME;
        this.SHIFT = SHIFT;
        this.SUBJECT = SUBJECT;
        this.DATE = DATE;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getSUBJECT() {
        return SUBJECT;
    }

    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }

    public String getSHIFT() {
        return SHIFT;
    }
}
