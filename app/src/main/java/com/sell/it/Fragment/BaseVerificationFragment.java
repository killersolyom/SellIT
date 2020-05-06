package com.sell.it.Fragment;

import android.view.View;

import com.sell.it.Model.Event;

public class BaseVerificationFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void findView(View view) {

    }

    @Override
    public boolean onEvent(Event event) {
        switch (event.getEventType()) {
            case Event.TYPE_FIREBASE:
                return true;
        }
        return false;
    }

}
