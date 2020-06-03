package com.sell.it.Model;

import java.io.Serializable;

public class ClassPackaging implements Serializable {
    Class<?> mClass;

    public ClassPackaging(Class<?> mClass) {
        this.mClass = mClass;
    }

    public Class<?> getItemClass() {
        return mClass;
    }

}
