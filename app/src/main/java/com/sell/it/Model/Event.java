package com.sell.it.Model;

import android.os.Bundle;

import androidx.annotation.Nullable;

public class Event {

    private int mEventType;
    private int mEventAction;
    private Bundle mExtras;

    public static final int FIREBASE = 0;
    public static final int CONTROL = 1;
    public static final int DRAWER_MENU = 2;

    public Event(int mEventType, int mEventMessage) {
        this.mEventType = mEventType;
        this.mEventAction = mEventMessage;
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
