package com.sell.it.Communication;

import java.util.ArrayList;

public interface ValueListener {

    default void writeValue(Float value) {
    }

    default void writeValue(String value) {
    }

    default void writeValue(Boolean value) {
    }

    default void writeValue(Class<?> itemClass) {
    }

    default void writeValue(ArrayList<String> valueList) {
    }

    default void registerCallback(InputCallbackInterface callback) {

    }
}
