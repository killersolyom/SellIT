package com.sell.it.Model;

public class User {
    private String mEmailAddress;
    private String mFirstName;
    private String mLastName;
    private String mUsername;

    public User() {
    }

    public User(String mEmailAddress, String mFirstName, String mLastName, String mUsername) {
        this.mEmailAddress = mEmailAddress;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mUsername = mUsername;
    }

    public String getmEmailAddress() {
        return mEmailAddress;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public String getmUsername() {
        return mUsername;
    }
}
