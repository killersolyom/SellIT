package com.sell.it.Model;

public class Event {

    private String mEventType;
    private String mEventMessage;

    public Event(String mEventType, String mEventMessage) {
        this.mEventType = mEventType;
        this.mEventMessage = mEventMessage;
    }

    public String getEventType() {
        return mEventType;
    }

    public String getEvent() {
        return mEventMessage;
    }
}
