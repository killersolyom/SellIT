package com.sell.it.Utility;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sell.it.Activity.MainActivity;
import com.sell.it.Communication.DrawerInterface;
import com.sell.it.Fragment.AdvertisementFragment;
import com.sell.it.Fragment.BaseFragment;
import com.sell.it.Fragment.LoginFragment;
import com.sell.it.Fragment.RegistrationFragment;
import com.sell.it.Fragment.SettingsFragment;
import com.sell.it.Model.Event;
import com.sell.it.R;

import static com.sell.it.Model.Constant.Values.EventAction.CLOSE_DRAWER_ACTION;
import static com.sell.it.Model.Constant.Values.EventAction.DISABLE_DRAWER_ACTION;
import static com.sell.it.Model.Constant.Values.EventAction.ENABLE_DRAWER_ACTION;

public class FragmentNavigation {

    private static long mLastBackPressTime;
    private static final long mExitTimeLimit = 350;
    private static FragmentManager mFragmentManager;
    private static DrawerInterface mMainInterface;
    private static FragmentManager.OnBackStackChangedListener mBackStackChangedListener;

    static void initComponents(MainActivity activity, DrawerInterface mainInterface) {
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

    private static void showFragment(BaseFragment fragment) {
        BaseFragment fragmentFromBackStack =
                castToBaseFragment(mFragmentManager.findFragmentByTag(fragment.TAG));
        boolean isInBackStack = fragmentFromBackStack != null;

        beforeFragmentLoaded(isInBackStack ? fragmentFromBackStack : fragment, isInBackStack);

        if (isInBackStack) {
            createTransaction().show(fragmentFromBackStack);
        } else {
            createTransaction().replace(R.id.fragment_container, fragment, fragment.TAG)
                    .addToBackStack(fragment.TAG)
                    .commit();
        }
    }

    private static FragmentTransaction createTransaction() {
        return mFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.enter_from_right, R.anim.exit_to_left,
                R.anim.enter_from_left, R.anim.exit_to_right);
    }

    private static BaseFragment castToBaseFragment(Fragment fragment) {
        return fragment instanceof BaseFragment ? (BaseFragment) fragment : null;
    }

    private static void clearBackStack(boolean clearAll) {
        for (int i = clearAll ? 0 : 1; i < mFragmentManager.getBackStackEntryCount(); ++i) {
            mFragmentManager.popBackStack();
        }
    }

    private static void beforeFragmentLoaded(BaseFragment fragment, boolean isInBackStack) {
        if (fragment instanceof AdvertisementFragment) {
            clearBackStack(!isInBackStack);
        }
    }

    private static void handleBackStackChangeEvent() {
        EventDispatcher.offerEvent(new Event(Event.DRAWER_MENU, generateDrawerLayoutControl()));
    }

    private static int generateDrawerLayoutControl() {
        BaseFragment fragment = getTopFragment();
        return fragment instanceof AdvertisementFragment || fragment instanceof SettingsFragment
                ? ENABLE_DRAWER_ACTION : DISABLE_DRAWER_ACTION;
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
            case R.id.nav_settings:
                showSettingsFragment();
                break;
            case R.id.nav_exit:
                exit();
                break;
        }
        EventDispatcher.offerEvent(new Event(Event.DRAWER_MENU, CLOSE_DRAWER_ACTION));
        return false;
    }

    public static void onBackPressed() {
        if (mMainInterface.isDrawerOpen()) {
            EventDispatcher.offerEvent(new Event(Event.DRAWER_MENU, CLOSE_DRAWER_ACTION));
        } else if (isDoubleBackPressPerformed()) {
            if (shouldExit()) {
                exit();
            } else {
                //TODO press again notification
            }
        } else if (shouldPop()) {
            popBackStack();
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

}