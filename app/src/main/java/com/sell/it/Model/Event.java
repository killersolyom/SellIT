package com.sell.it.Model;

import android.os.Bundle;

import androidx.annotation.Nullable;

public class Event {

    private int mEventType;
    private int mEventAction;
    private Bundle mExtras;
    private boolean mIsConsumed;
    private int mCloneNumber;

    public static final int TYPE_FIREBASE = 0;
    public static final int TYPE_CONTROL = 1;
    public static final int TYPE_DRAWER_MENU = 2;
    public static final int TYPE_IMAGE_PICKER = 3;

    public static final int ACTION_CLOSE_DRAWER = 0;
    public static final int ACTION_ENABLE_DRAWER = 1;
    public static final int ACTION_DISABLE_DRAWER = 2;
    public static final int ACTION_LANGUAGE_CHANGE = 3;
    public static final int ACTION_REGISTRATION_SUCCESS = 4;
    public static final int ACTION_REGISTRATION_FAIL = 5;
    public static final int ACTION_LOGIN_SUCCESS = 6;
    public static final int ACTION_LOGIN_FAIL = 7;
    public static final int ACTION_VERIFICATION_FAIL = 8;
    public static final int ACTION_VERIFICATION_SUCCESS = 9;
    public static final int ACTION_LOCK_ORIENTATION = 10;
    public static final int ACTION_UNLOCK_ORIENTATION = 11;
    public static final int ACTION_PICK_IMAGE = 12;
    public static final int ACTION_ADD_IMAGE = 13;
    public static final int ACTION_GET_ALL_ADVERTISEMENT = 14;
    public static final int ACTION_GET_CATEGORY_ADVERTISEMENT = 15;
    public static final int ACTION_GET_ITEM_TYPE_ADVERTISEMENT = 16;
    public static final int ACTION_UPLOAD_SUCCESS = 17;
    public static final int ACTION_UPLOAD_FAIL = 18;



    public Event(int mEventType, int mEventAction) {
        this.mEventType = mEventType;
        this.mEventAction = mEventAction;
        this.mIsConsumed = false;
        this.mCloneNumber = 0;
    }

    public Event(int mEventType, int mEventMessage, Bundle mBundle) {
        this.mEventType = mEventType;
        this.mEventAction = mEventMessage;
        this.mExtras = mBundle;
        this.mIsConsumed = false;
        this.mCloneNumber = 0;
    }

    private Event(int mEventType, int mEventMessage, Bundle mBundle, int cloneNumber) {
        this.mEventType = mEventType;
        this.mEventAction = mEventMessage;
        this.mExtras = mBundle;
        this.mIsConsumed = false;
        this.mCloneNumber = cloneNumber;
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

    public boolean isConsumed() {
        return mIsConsumed || mCloneNumber > 5;
    }

    public void consume() {
        mIsConsumed = true;
    }

    public Event clone() {
        return new Event(mEventType, mEventAction, mExtras, mCloneNumber + 1);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Event) {
            Event cmp = (Event) obj;
            return mEventType == cmp.mEventType && this.mEventAction == cmp.mEventAction;
        }
        return false;
    }

}
