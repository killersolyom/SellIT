package com.sell.it.Model;

import java.io.Serializable;

public class User implements Serializable {
    private String mEmailAddress;
    private String mFirstName;
    private String mLastName;
    private String mUsername;
    private String mPassword;

    public User() {
    }

    public User(String mEmailAddress, String mFirstName, String mLastName, String mUsername, String mPassword) {
        this.mEmailAddress = mEmailAddress;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {return mPassword;}
}
