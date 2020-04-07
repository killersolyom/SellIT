package com.sell.it.Utility;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageManager {

    public static void loadPreferredLanguage(Context context) {
        DataManager.initialize(context);
        Resources res = context.getResources();
        Locale locale = new Locale(DataManager.getLanguage());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

}
