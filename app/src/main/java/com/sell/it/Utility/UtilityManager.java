package com.sell.it.Utility;

import com.sell.it.Activity.MainActivity;
import com.sell.it.Communication.DrawerInterface;

public class UtilityManager {

    public static void initUtilities(MainActivity activity, DrawerInterface mainInterface) {
        FragmentNavigation.initComponents(activity,mainInterface);
        DisplayUtils.initialize(activity);
        DataManager.initialize(activity.getApplicationContext());
        DatabaseManager.initialize(activity.getApplicationContext());
    }
}
