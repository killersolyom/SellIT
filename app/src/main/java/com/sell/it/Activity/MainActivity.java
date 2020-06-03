package com.sell.it.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.sell.it.Communication.EventListener;
import com.sell.it.Communication.MainInterface;
import com.sell.it.Model.CustomUri;
import com.sell.it.Model.Event;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;
import com.sell.it.Utility.DataCacheUtil;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.EventDispatcher;
import com.sell.it.Utility.FragmentNavigation;
import com.sell.it.Utility.LanguageManager;
import com.sell.it.Utility.UtilityManager;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.sell.it.Model.Constant.Values.SELECT_PICTURE;

public class MainActivity extends AppCompatActivity implements EventListener, MainInterface,
        NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ImageView mDrawerHeaderImage;
    private NavigationView mNavigationView;
    private static final String FIRST_START_KEY = "FirstStart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventDispatcher.subscribe(this);
        UtilityManager.initUtilities(this, this);
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
            DataCacheUtil.clearItems();
            DataManager.clearLastSelectedItems();
            intent.putExtra(FIRST_START_KEY, true);
        }
    }

    private void restart() {
        startActivity(new Intent(this, MainActivity.class)
                .addFlags(FLAG_ACTIVITY_NEW_TASK)
                .putExtra(FIRST_START_KEY, false));
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
        return super.createConfigurationContext(LanguageManager.loadLanguage(overrideConfiguration));
    }

    @Override
    public void onBackPressed() {
        FragmentNavigation.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventDispatcher.subscribe(this);
        mNavigationView.setNavigationItemSelectedListener(this);
        Glide.with(getApplicationContext()).load(R.drawable.app_logo_png).into(mDrawerHeaderImage);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventDispatcher.unSubscribe(this);
        mNavigationView.setNavigationItemSelectedListener(null);
        Glide.with(getApplicationContext()).clear(mDrawerHeaderImage);
    }

    @Override
    public boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public boolean onEvent(Event event) {
        switch (event.getEventType()) {
            case Event.TYPE_DRAWER_MENU:
                switch (event.getAction()) {
                    case Event.ACTION_CLOSE_DRAWER:
                        mDrawerLayout.closeDrawers();
                        return true;
                    case Event.ACTION_ENABLE_DRAWER:
                        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                        return true;
                    case Event.ACTION_DISABLE_DRAWER:
                        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        return true;
                }
            case Event.TYPE_CONTROL:
                switch (event.getAction()) {
                    case Event.ACTION_LANGUAGE_CHANGE:
                        restart();
                        return true;
                    case Event.ACTION_LOCK_ORIENTATION:
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                        return true;
                    case Event.ACTION_UNLOCK_ORIENTATION:
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                        return true;
                    case Event.ACTION_PICK_IMAGE:
                        Intent intent = new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, SELECT_PICTURE), 1);
                        return true;
                }
        }
        return false;
    }

    @Override
    public View getView() {
        return mDrawerLayout;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null && data.getData() != null) {
            Event event = new Event(Event.TYPE_IMAGE_PICKER, Event.ACTION_ADD_IMAGE,
                    BundleUtil.createBundle(SELECT_PICTURE, new CustomUri(data.getData())));
            EventDispatcher.offerEvent(event);
        }
    }
    
}
