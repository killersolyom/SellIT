package com.sell.it.Utility;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.sell.it.Activity.MainActivity;
import com.sell.it.Communication.EventListener;
import com.sell.it.Communication.MainInterface;
import com.sell.it.Dialog.BaseDialogFragment;
import com.sell.it.Dialog.CategorySelectDialog;
import com.sell.it.Dialog.ColumnNumberSelectDialog;
import com.sell.it.Dialog.ConfirmDialog;
import com.sell.it.Dialog.LanguageSelectDialog;
import com.sell.it.Dialog.TransactionDialog;
import com.sell.it.Fragment.AddAdvertisementFragment;
import com.sell.it.Fragment.AdvertisementFragment;
import com.sell.it.Fragment.BaseFragment;
import com.sell.it.Fragment.DetailsFragment;
import com.sell.it.Fragment.LoginFragment;
import com.sell.it.Fragment.ProfileFragment;
import com.sell.it.Fragment.RegistrationFragment;
import com.sell.it.Fragment.SettingsFragment;
import com.sell.it.Model.Event;
import com.sell.it.Model.ViewHolderItem.Advertisements.DefaultAdvertisementItem;
import com.sell.it.R;

public class FragmentNavigation {

    private static long mLastBackPressTime;
    private static final long mExitTimeLimit = 350;
    private static FragmentManager mFragmentManager;
    private static MainInterface mMainInterface;
    private static FragmentManager.OnBackStackChangedListener mBackStackChangedListener;

    static void initComponents(MainActivity activity, MainInterface mainInterface) {
        EventDispatcher.subscribe(mEventListener);
        if (shouldInit()) {
            mFragmentManager = activity.getSupportFragmentManager();
            mBackStackChangedListener = FragmentNavigation::handleBackStackChangeEvent;
            mMainInterface = mainInterface;
            mFragmentManager.addOnBackStackChangedListener(mBackStackChangedListener);
            mLastBackPressTime = System.currentTimeMillis();
        }
    }

    private static boolean shouldInit() {
        return mFragmentManager == null || mMainInterface == null ||
                mBackStackChangedListener == null || mFragmentManager.isDestroyed();
    }

    public static void showCategorySelectorDialog() {
        showDialogFragment(new CategorySelectDialog());
    }

    public static void showColumnNumberSelectorDialog() {
        showDialogFragment(new ColumnNumberSelectDialog());
    }

    public static void showLanguageSelectorDialog() {
        showDialogFragment(new LanguageSelectDialog());
    }

    public static void showConfirmDialog(int titleId, Runnable yesOption) {
        showDialogFragment(new ConfirmDialog(titleId, yesOption));
    }

    public static void showTransactionDialog(Event... listenEvent) {
        showDialogFragment(new TransactionDialog(listenEvent));
    }

    public static void showLoginFragment() {
        showFragment(new LoginFragment());
    }

    public static void showAdvertisementFragment() {
        showFragment(new AdvertisementFragment());
    }

    private static void showSettingsFragment() {
        showFragment(new SettingsFragment());
    }

    public static void showRegistrationFragment() {
        showFragment(new RegistrationFragment());
    }

    public static void showProfileFragment() {
        showFragment(new ProfileFragment());
    }

    public static void showDetailsFragment(DefaultAdvertisementItem item) {
        showFragment(DetailsFragment.newInstance(item));
    }

    private static void showDialogFragment(BaseDialogFragment dialogFragment) {
        dismissDialogByTAG(dialogFragment.getClass().getCanonicalName());
        dialogFragment.show(mFragmentManager, dialogFragment.getClass().getCanonicalName());
    }

    public static void dismissDialogByTAG(String tag) {
        if (mFragmentManager.isStateSaved()) {
            return;
        }

        Fragment dialogFragment = mFragmentManager.findFragmentByTag(tag);
        if (dialogFragment instanceof BaseDialogFragment) {
            ((DialogFragment) dialogFragment).dismiss();
        }
    }

    private static void showFragment(BaseFragment fragment) {
        beforeFragmentLoaded(fragment);
        if (!fragment.compare(getTopFragment())) {
            mFragmentManager.beginTransaction().setCustomAnimations(
                    R.anim.enter_from_right, R.anim.exit_to_left,
                    R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(R.id.fragment_container, fragment, fragment.TAG)
                    .addToBackStack(fragment.TAG)
                    .commit();
        }
    }

    private static BaseFragment castToBaseFragment(Fragment fragment) {
        return fragment instanceof BaseFragment ? (BaseFragment) fragment : null;
    }

    private static void clearBackStack(boolean clearAll) {
        for (int i = clearAll ? 0 : 1; i < mFragmentManager.getBackStackEntryCount(); ++i) {
            mFragmentManager.popBackStack();
        }
    }

    private static void beforeFragmentLoaded(BaseFragment fragment) {
        boolean isInBackStack =
                castToBaseFragment(mFragmentManager.findFragmentByTag(fragment.TAG)) != null;

        if (fragment instanceof AdvertisementFragment) {
            clearBackStack(!isInBackStack);
        } else if (fragment instanceof LoginFragment) {
            clearBackStack(true);
        }
    }

    private static void handleBackStackChangeEvent() {
        EventDispatcher.offerEvent(new Event(Event.TYPE_DRAWER_MENU, generateDrawerLayoutControl()));
    }

    private static int generateDrawerLayoutControl() {
        BaseFragment fragment = getTopFragment();
        return (fragment == null || fragment instanceof LoginFragment
                || fragment instanceof RegistrationFragment) ?
                Event.ACTION_DISABLE_DRAWER : Event.ACTION_ENABLE_DRAWER;
    }

    private static BaseFragment getTopFragment() {
        return castToBaseFragment(mFragmentManager
                .getFragments()
                .stream()
                .filter(it -> it instanceof BaseFragment && it.isVisible())
                .findFirst()
                .orElse(null));
    }

    private static void popBackStack() {
        mFragmentManager.popBackStack();
    }

    public static boolean onDrawerItemSelected(int menuItemId) {
        switch (menuItemId) {
            case R.id.home:
                showAdvertisementFragment();
                break;
            case R.id.profile:
                showProfileFragment();
                break;
            case R.id.advertisement:
                verifyUser();
                break;
            case R.id.nav_settings:
                showSettingsFragment();
                break;
            case R.id.nav_exit:
                exit();
                break;
        }
        EventDispatcher.offerEvent(new Event(Event.TYPE_DRAWER_MENU, Event.ACTION_CLOSE_DRAWER));
        return false;
    }

    public static void onBackPressed() {
        if (mMainInterface.isDrawerOpen()) {
            EventDispatcher.offerEvent(new Event(Event.TYPE_DRAWER_MENU, Event.ACTION_CLOSE_DRAWER));
        } else if (isDoubleBackPressPerformed()) {
            if (shouldExit()) {
                exit();
            }
        } else if (shouldPop()) {
            popBackStack();
        }
    }

    public static void verifyUser() {
        if (!(getTopFragment() instanceof AddAdvertisementFragment)) {
            if (!(TextUtils.isEmpty(DataManager.getEmailAddress()) || TextUtils.isEmpty(DataManager.getPassword()))) {
                showTransactionDialog(
                        new Event(Event.TYPE_FIREBASE, Event.ACTION_VERIFICATION_FAIL),
                        new Event(Event.TYPE_FIREBASE, Event.ACTION_VERIFICATION_SUCCESS));
                DatabaseManager.verifyUser(DataManager.getEmailAddress(),
                        TextUtils.decrypt(DataManager.getPassword()));
            }
        }
    }

    private static boolean isDoubleBackPressPerformed() {
        long currentTime = System.currentTimeMillis();
        boolean isUnderTimeLimit = Math.abs(currentTime - mLastBackPressTime) <= mExitTimeLimit;
        mLastBackPressTime = currentTime;
        return isUnderTimeLimit;
    }

    private static boolean shouldExit() {
        BaseFragment fragment = getTopFragment();
        return (fragment instanceof AdvertisementFragment || fragment instanceof LoginFragment);
    }

    private static boolean shouldPop() {
        BaseFragment fragment = getTopFragment();
        return !(fragment instanceof AdvertisementFragment || fragment instanceof LoginFragment);
    }

    private static void exit() {
        mFragmentManager.removeOnBackStackChangedListener(mBackStackChangedListener);
        clearBackStack(true);
        System.exit(0);
    }

    private static EventListener mEventListener = event -> {
        switch (event.getEventType()) {
            case Event.TYPE_FIREBASE:
                switch (event.getAction()) {
                    case Event.ACTION_VERIFICATION_FAIL:
                        SnackBarUtility.showWithText(R.string.invalid_id, true);
                        showLoginFragment();
                        return true;
                    case Event.ACTION_VERIFICATION_SUCCESS:
                        DataCacheUtil.clearCache(AddAdvertisementFragment.class.getCanonicalName());
                        showFragment(new AddAdvertisementFragment());
                        return true;
                    case Event.ACTION_UPLOAD_SUCCESS:
                        SnackBarUtility.showWithText(R.string.advertisement_upload_success, false);
                        return true;
                    case Event.ACTION_UPLOAD_FAIL:
                        SnackBarUtility.showWithText(R.string.advertisement_upload_failed, true);
                        return true;
                }
                return false;
        }
        return false;
    };


}