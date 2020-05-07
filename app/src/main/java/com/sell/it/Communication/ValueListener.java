package com.sell.it.Communication;

public interface ValueListener {

    default void writeValue(int value) {
    }

    default void writeValue(float value) {
    }

    default void writeValue(String value) {
    }

}
