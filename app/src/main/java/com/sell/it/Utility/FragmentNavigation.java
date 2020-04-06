package com.sell.it.Utility;

import android.view.MenuItem;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sell.it.Activity.MainActivity;
import com.sell.it.Communication.ActivityCallbackInterface;
import com.sell.it.Fragment.AdvertisementFragment;
import com.sell.it.Fragment.BaseFragment;
import com.sell.it.Fragment.LoginFragment;
import com.sell.it.Fragment.RegistrationFragment;
import com.sell.it.Fragment.SettingsFragment;
import com.sell.it.R;

public class FragmentNavigation {

    private static FragmentManager mFragmentManager;
    private static ActivityCallbackInterface mMainInterface;

    public static void initComponents(MainActivity activity, ActivityCallbackInterface mainInterface) {
        if (mFragmentManager == null || mFragmentManager.isDestroyed()) {
            mFragmentManager = activity.getSupportFragmentManager();
        }
        mMainInterface = mainInterface;
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
        BaseFragment fragmentFromBackStack = castToBaseFragment(mFragmentManager.findFragmentByTag(fragment.TAG));

        if ((fragmentFromBackStack != null)) {
            createTransaction().show(fragmentFromBackStack);
            onBackStackChanged(fragmentFromBackStack);
        } else {
            createTransaction().replace(R.id.fragment_container, fragment, fragment.TAG)
                    .addToBackStack(fragment.TAG)
                    .commit();
            onBackStackChanged(fragment);
        }
    }

    private static void onBackStackChanged(Fragment fragment) {
        if (fragment instanceof AdvertisementFragment || fragment instanceof SettingsFragment) {
            mMainInterface.enableDrawerLayout();
        } else {
            mMainInterface.disableDrawerLayout();
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


    private static Fragment getTopFragment() {
        return mFragmentManager.getFragments().stream().findFirst().get();
    }

    private static void exit() {
        clearBackStack(true);
        System.exit(0);
    }

    private static void popBackStack() {
        mFragmentManager.popBackStack();
    }

    public static void handleNavigationItem(MenuItem menuItem, DrawerLayout drawerLayout) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                clearBackStack(false);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_settings:
                showSettingsFragment();
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_exit:
                exit();
                break;
        }
    }

    public static void onBackPressed() {
        if (shouldExit()) {
            exit();
        } else {
            popBackStack();
            onBackStackChanged(getTopFragment());
        }
    }

    private static boolean shouldExit() {
        return (getTopFragment() instanceof LoginFragment ||
                mFragmentManager.getBackStackEntryCount() == 1);
    }
}