package com.sell.it.Utility;

import com.sell.it.Communication.EventListener;
import com.sell.it.Model.Event;

import java.util.ArrayList;
import java.util.Objects;

public class EventDispatcher {

    private static ArrayList<EventListener> mListenerList = new ArrayList<>();

    public static void offerEvent(Event event) {
        if (event != null && !mListenerList.isEmpty()) {
            dispatchEvent(event);
        }
    }

    private static void dispatchEvent(Event event) {
        for (EventListener it : mListenerList) {
            if (it != null && it.onEvent(event)) {
                break;
            }
        }
    }

    public static void subscribe(EventListener eventListener) {
        if (!mListenerList.contains(eventListener)) {
            mListenerList.add(eventListener);
        }
        clearListeners();
    }

    public static void unSubscribe(EventListener eventListener) {
        mListenerList.remove(eventListener);
        clearListeners();
    }

    private static void clearListeners() {
        mListenerList.removeIf(Objects::isNull);
    }

}
