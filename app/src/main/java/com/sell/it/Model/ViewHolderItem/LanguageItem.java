package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Model.Constant.Values;

public class LanguageItem extends BaseDefaultItem {

    private String mKey;
    private String mLanguageName;
    private int mLanguageImage;

    public LanguageItem(String key, String languageName, int imageId) {
        mKey = key;
        mLanguageName = languageName;
        mLanguageImage = imageId;
    }

    public String getLanguageKey() {
        return mKey;
    }

    public String getLanguageName() {
        return mLanguageName;
    }

    public int getLanguageImage() {
        return mLanguageImage;
    }

    @Override
    public int getViewType() {
        return Values.ViewType.LANGUAGE_ITEM_TYPE;
    }
}
