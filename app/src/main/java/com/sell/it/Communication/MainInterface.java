package com.sell.it.Communication;

import android.content.Context;
import android.view.View;

public interface MainInterface {
    View getView();

    Context getContext();

    boolean isDrawerOpen();
}
