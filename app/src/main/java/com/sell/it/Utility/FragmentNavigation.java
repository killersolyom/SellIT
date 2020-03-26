package com.sell.it.Utility;

import android.view.MenuItem;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.sell.it.Activity.MainActivity;
import com.sell.it.Fragment.AdvertisementFragment;
import com.sell.it.Fragment.LoginFragment;
import com.sell.it.Fragment.SettingsFragment;
import com.sell.it.R;

public class FragmentNavigation {

    private static FragmentManager mFragmentManager;

    public static void initComponents(MainActivity activity) {
        mFragmentManager = activity.getSupportFragmentManager();
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

    private static void showFragment(Fragment fragment) {
        (mFragmentManager.beginTransaction())
                .replace(R.id.fragment_container, fragment, fragment.getClass().getCanonicalName())
                .addToBackStack(fragment.getTag())
                .commit();
    }

    private static void clearBackStack() {
        for (int i = 1; i < mFragmentManager.getBackStackEntryCount(); ++i) {
            mFragmentManager.popBackStack();
        }
    }

    private static void clearAllBackStack() {
        for (int i = 0; i < mFragmentManager.getBackStackEntryCount(); ++i) {
            mFragmentManager.popBackStack();
        }
    }

    private static Fragment getTopFragment() {
        return mFragmentManager.getFragments().get(mFragmentManager.getFragments().size() - 1);
    }

    private static void exit() {
        clearAllBackStack();
        System.exit(0);
    }

    private static void popBackStack() {
        mFragmentManager.popBackStack();
    }

    public static void handleNavigationItem(MenuItem menuItem, DrawerLayout drawerLayout) {
        switch (menuItem.getItemId()) {
            case R.id.nav_sounds:
                clearBackStack();
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_settings:
                clearBackStack();
                showSettingsFragment();
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_exit:
                exit();
                break;
        }
    }

    public static void onBackPressed() {
        if (getTopFragment() instanceof LoginFragment) {
            exit();
        } else {
            popBackStack();
        }
    }
}