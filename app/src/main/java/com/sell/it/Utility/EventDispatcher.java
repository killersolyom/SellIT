package com.sell.it.Utility;

import com.sell.it.Communication.EventListener;
import com.sell.it.Model.Event;

import java.util.ArrayList;
import java.util.Objects;

public class EventDispatcher {

    private static ArrayList<EventListener> mListenerList = new ArrayList<>();
    private static ArrayList<Event> mEventList = new ArrayList<>();

    public static void offerEvent(Event event) {
        offerEvent(event, false);
    }

    public static void offerEvent(Event event, boolean ignoreConsume) {
        if (event != null && !mListenerList.isEmpty()) {
            dispatchEvent(event, ignoreConsume);
            if (!event.isConsumed()) {
                mEventList.add(event.clone());
            }
        }
    }

    public static void sendUnconsumedEvents() {
        if (!mEventList.isEmpty()) {
            for (Event event : mEventList) {
                dispatchEvent(event, true);
            }
            mEventList.removeIf(Event::isConsumed);
        }
    }

    private static void dispatchEvent(Event event, boolean ignoreConsume) {
        boolean shouldClear = false;
        for (EventListener it : ((ArrayList<EventListener>) mListenerList.clone())) {
            if (it != null) {
                if (it.onEvent(event)) {
                    event.consume();
                    if (!ignoreConsume) {
                        break;
                    }
                }
            } else {
                shouldClear = true;
            }
        }
        if (shouldClear) {
            clearListeners();
        }
    }

    public static void subscribe(EventListener eventListener) {
        if (!mListenerList.contains(eventListener)) {
            mListenerList.add(eventListener);
        }
    }

    public static void unSubscribe(EventListener eventListener) {
        mListenerList.remove(eventListener);
    }

    private static void clearListeners() {
        mListenerList.removeIf(Objects::isNull);
    }

}
