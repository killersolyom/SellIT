package com.sell.it.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.sell.it.CustomView.NotificationBar;
import com.sell.it.R;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.FragmentNavigation;
import com.sell.it.Utility.GlideUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ImageView drawerHeaderImage;
    private NavigationView navigationView;
    private NotificationBar notificationBar;
    private final String FIRST_START_KEY = "FirstStart";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initComponents();
        handleIntentEvents(getIntent());
    }

    private void initView() {
        notificationBar = findViewById(R.id.notification_bar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerHeaderImage = (navigationView.getHeaderView(0)).findViewById(R.id.drawer_menu_image);
    }

    private void initComponents() {
        FragmentNavigation.initComponents(this, notificationBar);
        GlideUtils.initialize(getApplicationContext());
        DataManager.initialize(getApplicationContext());
    }

    private void handleIntentEvents(Intent intent) {
        if (!intent.getBooleanExtra(FIRST_START_KEY, false)) {
            FragmentNavigation.showLoginFragment();
        }
        intent.putExtra(FIRST_START_KEY, true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentNavigation.handleNavigationItem(menuItem, drawerLayout);
        return false;
    }

    @Override
    public void onBackPressed() {
        FragmentNavigation.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        notificationBar.clearAllTask();
        GlideUtils.clearImage(drawerHeaderImage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setNavigationItemSelectedListener(this);
        GlideUtils.loadBackgroundImage(R.drawable.warning_image, drawerHeaderImage);
    }

}
