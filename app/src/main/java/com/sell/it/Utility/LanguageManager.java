package com.sell.it.Utility;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageManager {

    public static Context loadPreferredLanguage(Context context) {
        DataManager.initialize(context);
        DataManager.initialize(context);
        Resources res = context.getResources();
        Locale locale = new Locale(DataManager.getLanguage());
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        res.updateConfiguration(configuration, res.getDisplayMetrics());
        return context.createConfigurationContext(configuration);
    }

    public static Configuration loadLanguageIntoConfig(Configuration overrideConfiguration){
        overrideConfiguration.setLocale(new Locale(DataManager.getLanguage()));
        return overrideConfiguration;
    }

}
