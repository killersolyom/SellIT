package com.sell.it.Utility;

import com.sell.it.Activity.MainActivity;
import com.sell.it.Communication.ActivityCallbackInterface;

public class UtilityManager {

    public static void initUtilities(MainActivity activity, ActivityCallbackInterface mainInterface) {
        FragmentNavigation.initComponents(activity,mainInterface);
        DataManager.initialize(activity.getApplicationContext());
    }
}
