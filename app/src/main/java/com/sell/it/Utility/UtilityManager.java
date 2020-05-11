package com.sell.it.Utility;

import com.sell.it.Activity.MainActivity;
import com.sell.it.Communication.MainInterface;

public class UtilityManager {

    public static void initUtilities(MainActivity activity, MainInterface mainInterface) {
        FragmentNavigation.initComponents(activity, mainInterface);
        DisplayUtils.initialize(activity);
        DataManager.initialize(activity.getApplicationContext());
        DatabaseManager.initialize();
        SnackBarUtility.initialize(mainInterface);
    }

    public static void onPause() {
        FragmentNavigation.onPause();
    }
}
