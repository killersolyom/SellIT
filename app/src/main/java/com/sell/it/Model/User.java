package com.sell.it.Model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.PropertyName;

import java.io.Serializable;

public class User implements Serializable {

    private static final String USERNAME_KEY = "USER_NAME";
    private static final String EMAIL_KEY = "EMAIL_ADDRESS";
    private static final String FIRST_NAME_KEY = "FIRST_NAME";
    private static final String LAST_NAME_KEY = "LAST_NAME";
    private static final String PASSWORD_KEY = "PASSWORD";

    private String mEmailAddress;
    private String mFirstName;
    private String mLastName;
    private String mUsername;
    private String mPassword;

    public User(DataSnapshot dataSnapshot) {
        this.mEmailAddress = getValueFromSnapshot(dataSnapshot.child(EMAIL_KEY).getValue());
        this.mFirstName = getValueFromSnapshot(dataSnapshot.child(FIRST_NAME_KEY).getValue());
        this.mLastName = getValueFromSnapshot(dataSnapshot.child(LAST_NAME_KEY).getValue());
        this.mUsername = getValueFromSnapshot(dataSnapshot.child(USERNAME_KEY).getValue());
        this.mPassword = getValueFromSnapshot(dataSnapshot.child(PASSWORD_KEY).getValue());
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

    @PropertyName(EMAIL_KEY)
    public String getEmailAddress() {
        return mEmailAddress;
    }

    @PropertyName(FIRST_NAME_KEY)
    public String getFirstName() {
        return mFirstName;
    }

    @PropertyName(LAST_NAME_KEY)
    public String getLastName() {
        return mLastName;
    }

    @PropertyName(USERNAME_KEY)
    public String getUsername() {
        return mUsername;
    }

    @PropertyName(PASSWORD_KEY)
    public String getPassword() {
        return mPassword;
    }
    
}
