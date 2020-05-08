package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.Model.ViewHolderItem.BaseDefaultItem;
import com.sell.it.Model.ViewHolderItem.BaseItem;
import com.sell.it.Model.ViewHolderItem.ImageItem;
import com.sell.it.R;
import com.sell.it.Utility.TextUtils;

import java.util.ArrayList;
import java.util.Map;

public abstract class DefaultAdvertisementItem extends BaseDefaultItem {

    public static final String PRICE_KEY = "PRICE_KEY";
    public static final String TITLE_KEY = "TITLE_KEY";
    public static final String DESCRIPTION_KEY = "DESCRIPTION_KEY";

    private String mId;
    private float mPrice;
    private String mTitle;
    private String mDescription;
    private ArrayList<BaseItem> mImageList = new ArrayList<>();

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    public ImageItem getFirstImage() {
        return mImageList.isEmpty() ? new ImageItem() : (ImageItem) mImageList.get(0);
    }

    public ArrayList<BaseItem> getImageList() {
        return mImageList;
    }

    public abstract String getItemType();

    public abstract String getCategoryType();

    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = new ArrayList<>();
        addToListIfExist(descriptionList, R.string.advertisement_title, mTitle);
        addToListIfExist(descriptionList, R.string.advertisement_description, mDescription);
        addToListIfExist(descriptionList, R.string.advertisement_price, floatValueToDescriptionString(mPrice));
        return descriptionList;
    }

    protected void addToListIfExist(ArrayList<Pair<Integer, String>> list, int textId, String item) {
        if (!TextUtils.isEmpty(item)) {
            list.add(new Pair<>(textId, item));
        }
    }

    protected void initItems(Map<String, Object> items) {
        mPrice = getFloatValue(items.get(PRICE_KEY));
        mTitle = getStringValue(items.get(TITLE_KEY));
        mDescription = getStringValue(items.get(DESCRIPTION_KEY));
    }

    protected boolean getBooleanValue(Object item) {
        return (item instanceof Boolean) ? (Boolean) item : false;
    }

    protected String getStringValue(Object item) {
        return (item instanceof String) ? (String) item : "";
    }

    protected float getFloatValue(Object item) {
        return (item instanceof Float) ? (Float) item : 0;
    }

    protected int getIntValue(Object item) {
        return (item instanceof Float) ? ((Float) item).intValue() : 0;
    }

    protected String floatValueToDescriptionString(float value) {
        return value == 0 ? "" : String.valueOf(value);
    }

    protected String intValueToDescriptionString(int value) {
        return value == 0 ? "" : String.valueOf(value);
    }

}
