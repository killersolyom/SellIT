package com.sell.it.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.sell.it.R;
import com.sell.it.Utility.FragmentNavigation;
import com.sell.it.Utility.UtilityManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ImageView mDrawerHeaderImage;
    private NavigationView mNavigationView;
    private final String FIRST_START_KEY = "FirstStart";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UtilityManager.initUtilities(this);
        initView();
        handleIntentEvents(getIntent());
    }

    private void initView() {
        mNavigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerHeaderImage = (mNavigationView.getHeaderView(0)).findViewById(R.id.drawer_menu_image);
    }

    private void handleIntentEvents(Intent intent) {
        if (!intent.getBooleanExtra(FIRST_START_KEY, false)) {
            FragmentNavigation.showLoginFragment();
        }
        intent.putExtra(FIRST_START_KEY, true);
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
    protected void onPause() {
        super.onPause();
        Glide.with(getApplicationContext()).clear(mDrawerHeaderImage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNavigationView.setNavigationItemSelectedListener(this);
        Glide.with(getApplicationContext()).load(R.drawable.warning_image).into(mDrawerHeaderImage);
    }

}
