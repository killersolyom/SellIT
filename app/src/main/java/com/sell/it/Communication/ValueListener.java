package com.sell.it.Communication;

import com.sell.it.Model.CustomUri;

import java.util.ArrayList;

public interface ValueListener {

    default String getStringValue() {
        return "";
    }

    default boolean getBooleanValue() {
        return false;
    }

    default ArrayList<CustomUri> getItemList() {
        return new ArrayList<>();
    }

    default void writeValue(Float value) {
    }

    default void writeValue(String value) {
    }

    default void writeValue(Boolean value) {
    }

    default void writeValue(Class<?> itemClass) {
    }

    default void writeValue(ArrayList<CustomUri> valueList) {
    }

    default void registerCallback(InputCallbackInterface callback) {

    }
}
