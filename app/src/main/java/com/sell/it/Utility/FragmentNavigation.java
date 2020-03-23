package com.sell.it.Utility;

import android.view.MenuItem;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.sell.it.Activity.MainActivity;
import com.sell.it.Communication.MainActivityInterface;
import com.sell.it.Fragment.LoginFragment;
import com.sell.it.Fragment.SettingsFragment;
import com.sell.it.R;

public class FragmentNavigation {

    private FragmentManager mFragmentManager;
    private MainActivityInterface mMainInterface;


    private static FragmentNavigation ourInstance;

    public static FragmentNavigation getInstance() {
        if (ourInstance == null) {
            ourInstance = new FragmentNavigation();
        }
        return ourInstance;
    }

    private FragmentNavigation() {
    }

    public void initComponents(MainActivity activity, MainActivityInterface mainInterface) {
        //TODO replace singleton class with static methods
        mFragmentManager = activity.getSupportFragmentManager();
        this.mMainInterface = mainInterface;
    }

    public void showLoginFragment() {
        showFragment(new LoginFragment());
    }

    private void showSettingsFragment() {
        showFragment(new SettingsFragment());
    }

    private void showFragment(Fragment fragment) {
        (mFragmentManager.beginTransaction())
                .replace(R.id.fragment_container, fragment, fragment.getClass().getCanonicalName())
                .addToBackStack(fragment.getTag())
                .commit();
    }


    private void clearBackStack() {
        for (int i = 1; i < mFragmentManager.getBackStackEntryCount(); ++i) {
            mFragmentManager.popBackStack();
        }
    }

    private void clearAllBackStack() {
        for (int i = 0; i < mFragmentManager.getBackStackEntryCount(); ++i) {
            mFragmentManager.popBackStack();
        }
    }

    private Fragment getTopFragment() {
        //TODO mFragmentManager.getBackStackEntryCount()
        int index = mFragmentManager.getFragments().size() - 1;
        return mFragmentManager.getFragments().get(index);
    }

    private void exit() {
        clearAllBackStack();
        System.exit(0);
    }

    private void popBackStack() {
        mFragmentManager.popBackStack();
    }

    public void showNotificationBar(String title, String message, Object image, boolean isError) {
        //TODO remove this shit from here
        mMainInterface.showNotificationBar(title, message, image, isError);
    }

    public void handleNavigationItem(MenuItem menuItem, DrawerLayout drawerLayout) {
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

    public void onBackPressed() {
        if (getTopFragment() instanceof LoginFragment) {
            exit();
        } else {
            popBackStack();
        }
    }
}