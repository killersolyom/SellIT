package com.sell.it.Communication;

public interface InputCallbackInterface {

    default boolean isReady() {
        return true;
    }

    default void showStatus(boolean isError) {
    }

}
