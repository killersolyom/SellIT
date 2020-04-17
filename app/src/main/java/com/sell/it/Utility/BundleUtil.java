package com.sell.it.Utility;

import android.os.Bundle;

public class BundleUtil {

    public static boolean isExist(Bundle bundle) {
        return bundle != null && !bundle.isEmpty();
    }

    public static boolean hasValueAt(Bundle bundle, String key) {
        return isExist(bundle) && bundle.get(key) != null;
    }

    public static boolean canCast(Bundle bundle, String key, Class<?> type) {
        if (hasValueAt(bundle, key) && type != null) {
            Object item = bundle.get(key);
            return item != null && type.equals(item.getClass());
        }
        return false;
    }

    public static <Type> Type castItem(Bundle bundle, String key, Class<Type> type) {
        return type.cast(bundle.get(key));
    }

    public static <Type> Type castItem(Object item, Class<Type> type) {
        return type.cast(item);
    }

}
