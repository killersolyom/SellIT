package com.sell.it.Model;

import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;

public class User implements Serializable {
    private String mEmailAddress;
    private String mFirstName;
    private String mLastName;
    private String mUsername;
    private String mPassword;

    public User(DataSnapshot dataSnapshot) {
        this.mEmailAddress = getValueFromSnapshot(dataSnapshot.child("emailAddress").getValue());
        this.mFirstName = getValueFromSnapshot(dataSnapshot.child("firstName").getValue());
        this.mLastName = getValueFromSnapshot(dataSnapshot.child("lastName").getValue());
        this.mUsername = getValueFromSnapshot(dataSnapshot.child("username").getValue());
        this.mPassword = getValueFromSnapshot(dataSnapshot.child("password").getValue());
    }

    public User(String mEmailAddress, String mFirstName, String mLastName, String mUsername, String mPassword) {
        this.mEmailAddress = mEmailAddress;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    private String getValueFromSnapshot(Object item) {
        return item instanceof String ? item.toString() : "";
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
