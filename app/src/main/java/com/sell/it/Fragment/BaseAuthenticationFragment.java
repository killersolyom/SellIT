package com.sell.it.Fragment;

import android.view.View;

import com.sell.it.Model.Event;
import com.sell.it.Utility.FragmentNavigation;

public class BaseAuthenticationFragment extends BaseFragment {
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
            case Event.TYPE_FIREBASE: {
                switch (event.getAction()) {
                    case Event.ACTION_LOGIN_FAIL:

                        return true;
                    case Event.ACTION_LOGIN_SUCCESS:
                    case Event.ACTION_REGISTRATION_SUCCESS:
                        FragmentNavigation.showAdvertisementFragment();
                        return true;
                    case Event.ACTION_REGISTRATION_FAIL:

                        return true;
                }
            }
        }
        return false;
    }
}
