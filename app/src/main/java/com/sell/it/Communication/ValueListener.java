package com.sell.it.Communication;

public interface ValueListener {

    default void writeValue(Float value) {
    }

    default void writeValue(String value) {
    }

    default void writeValue(Boolean value) {
    }

    void registerCallback(InputCallbackInterface callback);
}
