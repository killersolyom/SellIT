package com.sell.it.Model;

import android.os.Bundle;

public class Event {

    private String mEventType;
    private String mEventMessage;
    private Bundle mExtras;

    public static final String SCREEN_ORIENTATION = "ORIENTATION";
    public static final String FIREBASE = "FIREBASE";


    public Event(String mEventType, String mEventMessage) {
        this.mEventType = mEventType;
        this.mEventMessage = mEventMessage;
    }

    public Event(String mEventType, String mEventMessage,Bundle mBundle) {
        this.mEventType = mEventType;
        this.mEventMessage = mEventMessage;
        this.mExtras = mBundle;
    }

    public Bundle getExtras() {
        return mExtras;
    }

    public String getEventType() {
        return mEventType;
    }

    public String getEvent() {
        return mEventMessage;
    }
}
