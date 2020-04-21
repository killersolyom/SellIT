package com.sell.it.Model;

import android.os.Bundle;

import androidx.annotation.Nullable;

public class Event {

    private int mEventType;
    private int mEventAction;
    private Bundle mExtras;

    public static final int TYPE_FIREBASE = 0;
    public static final int TYPE_CONTROL = 1;
    public static final int TYPE_DRAWER_MENU = 2;

    public static final int ACTION_CLOSE_DRAWER = 0;
    public static final int ACTION_ENABLE_DRAWER = 1;
    public static final int ACTION_DISABLE_DRAWER = 2;
    public static final int ACTION_LANGUAGE_CHANGE = 3;
    public static final int ACTION_REGISTRATION_SUCCESS = 4;
    public static final int ACTION_REGISTRATION_FAIL = 5;
    public static final int ACTION_LOGIN_SUCCESS = 6;
    public static final int ACTION_LOGIN_FAIL = 7;


    public Event(int mEventType, int mEventAction) {
        this.mEventType = mEventType;
        this.mEventAction = mEventAction;
    }

    public Event(int mEventType, int mEventMessage, Bundle mBundle) {
        this.mEventType = mEventType;
        this.mEventAction = mEventMessage;
        this.mExtras = mBundle;
    }

    public Bundle getExtras() {
        return mExtras;
    }

    public boolean hasExtras() {
        return mExtras != null && !mExtras.isEmpty();
    }

    public int getEventType() {
        return mEventType;
    }

    public int getAction() {
        return mEventAction;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Event) {
            Event cmp = (Event) obj;
            return mEventType == cmp.mEventType && this.mEventAction == cmp.mEventAction && mExtras == cmp.mExtras;
        }
        return false;
    }
}
