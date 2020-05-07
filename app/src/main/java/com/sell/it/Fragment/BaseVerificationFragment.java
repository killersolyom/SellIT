package com.sell.it.Fragment;

import android.view.View;

import com.sell.it.Model.Event;
import com.sell.it.R;
import com.sell.it.Utility.FragmentNavigation;
import com.sell.it.Utility.SnackBarUtility;

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
                switch (event.getAction()) {
                    case Event.ACTION_VERIFICATION_FAIL:
                        SnackBarUtility.showWithText(R.string.invalid_id, true);
                        return true;
                    case Event.ACTION_VERIFICATION_SUCCESS:
                        FragmentNavigation.showAddAdvertisementFragment();
                        return true;
                }
                return true;
        }
        return false;
    }

}
