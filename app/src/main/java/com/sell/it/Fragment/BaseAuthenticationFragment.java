package com.sell.it.Fragment;

import com.sell.it.Model.Event;
import com.sell.it.Model.User;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;
import com.sell.it.Utility.DataCacheUtil;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.FragmentNavigation;
import com.sell.it.Utility.SnackBarUtility;

import static com.sell.it.Utility.DatabaseManager.USER_KEY;

public abstract class BaseAuthenticationFragment extends BaseFragment {

    @Override
    public boolean onEvent(Event event) {
        switch (event.getEventType()) {
            case Event.TYPE_FIREBASE:
                switch (event.getAction()) {
                    case Event.ACTION_LOGIN_FAIL:
                        SnackBarUtility.showWithText(R.string.login_failed, true);
                        return true;
                    case Event.ACTION_REGISTRATION_FAIL:
                        SnackBarUtility.showWithText(R.string.registration_failed, true);
                        return true;
                    case Event.ACTION_LOGIN_SUCCESS:
                    case Event.ACTION_REGISTRATION_SUCCESS:
                        DataCacheUtil.clearItems();
                        if (BundleUtil.canCast(event.getExtras(), USER_KEY, User.class)) {
                            DataManager.saveUser(BundleUtil.castItem(event.getExtras(), USER_KEY, User.class));
                            FragmentNavigation.showAdvertisementFragment();
                            SnackBarUtility.showWithText(R.string.login_success, false);
                        }
                        return true;
                }
        }
        return false;
    }

}
