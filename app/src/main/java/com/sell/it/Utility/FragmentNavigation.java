package com.sell.it.Utility;

import android.view.MenuItem;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.sell.it.Activity.MainActivity;
import com.sell.it.Fragment.AdvertisementFragment;
import com.sell.it.Fragment.BaseFragment;
import com.sell.it.Fragment.LoginFragment;
import com.sell.it.Fragment.RegistrationFragment;
import com.sell.it.Fragment.SettingsFragment;
import com.sell.it.R;

public class FragmentNavigation {

    private static FragmentManager mFragmentManager;

    public static void initComponents(MainActivity activity) {
        if (mFragmentManager == null || mFragmentManager.isDestroyed()) {
            mFragmentManager = activity.getSupportFragmentManager();
        }
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
        (mFragmentManager.beginTransaction())
                .replace(R.id.fragment_container, fragment, fragment.TAG)
                .addToBackStack(fragment.TAG)
                .commit();
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
            case R.id.nav_sounds:
                clearBackStack(false);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_settings:
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
        if (shouldExit()) {
            exit();
        } else {
            popBackStack();
        }
    }

    private static boolean shouldExit() {
        return (getTopFragment() instanceof LoginFragment ||
                mFragmentManager.getBackStackEntryCount() == 1);
    }
}