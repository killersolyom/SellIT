package com.sell.it.Utility;

import com.sell.it.Activity.MainActivity;

public class UtilityManager {

    public static void initUtilities(MainActivity activity) {
        FragmentNavigation.initComponents(activity);
        GlideUtils.initialize(activity.getApplicationContext());
        DataManager.initialize(activity.getApplicationContext());
    }
}
