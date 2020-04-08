package com.sell.it.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.sell.it.Communication.ActivityCallbackInterface;
import com.sell.it.R;
import com.sell.it.Utility.FragmentNavigation;
import com.sell.it.Utility.LanguageManager;
import com.sell.it.Utility.UtilityManager;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.sell.it.Model.Constant.Values.DrawerControlAction.CLOSE_ACTION;
import static com.sell.it.Model.Constant.Values.DrawerControlAction.DISABLE_ACTION;
import static com.sell.it.Model.Constant.Values.DrawerControlAction.ENABLE_ACTION;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActivityCallbackInterface {

    private DrawerLayout mDrawerLayout;
    private BroadcastReceiver mReceiver;
    private ImageView mDrawerHeaderImage;
    private NavigationView mNavigationView;
    private static final String FIRST_START_KEY = "FirstStart";
    public static final String LANGUAGE_CHANGED_KEY = "languageChangeEvent";
    public static final String INTENT_FILTER_KEY = "applicationIntentFilter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UtilityManager.initUtilities(this, this);
        initView();
        initReceiver();
        handleIntentEvents(getIntent());
    }

    private void initView() {
        mNavigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerHeaderImage = (mNavigationView.getHeaderView(0)).findViewById(R.id.drawer_menu_image);
    }

    private void initReceiver() {
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                handleIntentEvents(intent);
            }
        };
    }

    private void handleIntentEvents(Intent intent) {
        if (intent.getBooleanExtra(LANGUAGE_CHANGED_KEY, false)) {
            restart();
        } else if (!intent.getBooleanExtra(FIRST_START_KEY, false)) {
            FragmentNavigation.showLoginFragment();
            intent.putExtra(FIRST_START_KEY, true);
        }
    }

    private void restart() {
        startActivity(new Intent(this, MainActivity.class)
                .addFlags(FLAG_ACTIVITY_NEW_TASK).putExtra(FIRST_START_KEY, false));
        finish();
        Runtime.getRuntime().exit(0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return FragmentNavigation.onDrawerItemSelected(menuItem.getItemId());
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LanguageManager.loadPreferredLanguage(context));
    }

    @Override
    public Context createConfigurationContext(Configuration overrideConfiguration) {
        return super.createConfigurationContext(
                LanguageManager.loadLanguageIntoConfig(overrideConfiguration));
    }

    @Override
    public void onBackPressed() {
        FragmentNavigation.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNavigationView.setNavigationItemSelectedListener(this);
        registerReceiver(mReceiver, new IntentFilter(INTENT_FILTER_KEY));
        Glide.with(getApplicationContext()).load(R.drawable.app_logo_png).into(mDrawerHeaderImage);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Glide.with(getApplicationContext()).clear(mDrawerHeaderImage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onDrawerLayoutEvent(String event) {
        switch (event) {
            case CLOSE_ACTION:
                mDrawerLayout.closeDrawers();
                break;
            case ENABLE_ACTION:
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
            case DISABLE_ACTION:
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
        }
    }

    @Override
    public boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

}
