package com.sell.it.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.sell.it.R;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.FragmentNavigation;
import com.sell.it.Utility.UtilityManager;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
        UtilityManager.initUtilities(this);
        initView();
        initReceiver();
        handleIntentEvents(getIntent());
        loadPreferredLanguage();
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
            finish();
        } else if (!intent.getBooleanExtra(FIRST_START_KEY, false)) {
            FragmentNavigation.showLoginFragment();
            intent.putExtra(FIRST_START_KEY, true);
        }
    }

    private void loadPreferredLanguage() {
        Resources res = getResources();
        Locale locale = new Locale(DataManager.getLanguage());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentNavigation.handleNavigationItem(menuItem, mDrawerLayout);
        return false;
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
        Glide.with(getApplicationContext()).load(R.drawable.warning_image).into(mDrawerHeaderImage);
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

}
