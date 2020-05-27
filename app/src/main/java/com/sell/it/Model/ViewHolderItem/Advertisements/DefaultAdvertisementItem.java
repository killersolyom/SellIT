package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;
import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.ViewHolderItem.BaseDefaultItem;
import com.sell.it.Model.ViewHolderItem.ImageItem;
import com.sell.it.R;
import com.sell.it.Utility.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class DefaultAdvertisementItem extends BaseDefaultItem {

    public static final String PRICE_KEY = "PRICE";
    public static final String TITLE_KEY = "TITLE";
    public static final String DESCRIPTION_KEY = "DESCRIPTION";
    public static final String OWNER_KEY = "OWNER";
    public static final String ID_KEY = "ID";
    public static final String ITEM_KEY = "ITEM_TYPE";
    public static final String PHONE_NUMBER_KEY = "PHONE_NUMBER";
    public static final String IMAGE_LIST_KEY = "IMAGE_LIST_KEY";

    private String mId;
    private float mPrice;
    private String mTitle;
    private String mOwner;
    private String mDescription;
    private String mPhoneNumber;

    private ArrayList<ImageItem> mImageList = new ArrayList<>();

    @PropertyName(TITLE_KEY)
    public String getTitle() {
        return mTitle;
    }

    @PropertyName(ID_KEY)
    public String getId() {
        return mId;
    }

    @PropertyName(ID_KEY)
    public void setId(String mId) {
        this.mId = mId;
    }

    @PropertyName(DESCRIPTION_KEY)
    public String getDescription() {
        return mDescription;
    }

    @PropertyName(PRICE_KEY)
    public float getPrice() {
        return mPrice;
    }

    public String getPriceString() {
        return String.valueOf(mPrice).replace(".0", "");
    }

    @PropertyName(PHONE_NUMBER_KEY)
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    @PropertyName(OWNER_KEY)
    public String getOwner() {
        return mOwner;
    }


    @Exclude
    public ImageItem getFirstImage() {
        return mImageList.isEmpty() ? new ImageItem() : mImageList.get(0);
    }


    @PropertyName(IMAGE_LIST_KEY)
    public ArrayList<ImageItem> getImageList() {
        return mImageList;
    }

    @PropertyName(ITEM_KEY)
    public abstract String getItemType();

    @Exclude
    public abstract String getCategoryType();

    @Exclude
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = new ArrayList<>();
        addToListIfExist(descriptionList, R.string.advertisement_title, mTitle);
        addToListIfExist(descriptionList, R.string.advertisement_description, mDescription);
        addToListIfExist(descriptionList, R.string.advertisement_price, floatValueToDescriptionString(mPrice));
        addToListIfExist(descriptionList, R.string.advertisement_owner, mOwner);
        addToListIfExist(descriptionList, R.string.phone_number, mPhoneNumber);
        return descriptionList;
    }

    @Exclude
    @Override
    public int getViewType() {
        return Values.ViewType.ADVERTISEMENT_TYPE;
    }

    protected void addToListIfExist(ArrayList<Pair<Integer, String>> list, int textId, String item) {
        if (!TextUtils.isEmpty(item)) {
            list.add(new Pair<>(textId, item));
        }
    }

    protected void initItems(HashMap<String, Object> items) {
        mPrice = getFloatValue(items.get(PRICE_KEY));
        mTitle = getStringValue(items.get(TITLE_KEY));
        mOwner = getStringValue(items.get(OWNER_KEY));
        mDescription = getStringValue(items.get(DESCRIPTION_KEY));
        getArrayListValue(items.get(IMAGE_LIST_KEY));
    }

    protected boolean getBooleanValue(Object item) {
        return (item instanceof Boolean) ? (Boolean) item : false;
    }

    protected String getStringValue(Object item) {
        return (item instanceof String) ? (String) item : "";
    }

    protected float getFloatValue(Object item) {
        return (item instanceof Float) ? (Float) item :
                item instanceof Long ? ((Long) item).floatValue() : 0;
    }

    protected int getIntValue(Object item) {
        return item instanceof Float ? ((Float) item).intValue() :
                item instanceof Long ? ((Long) item).intValue() : 0;
    }

    protected void getArrayListValue(Object item) {
        if(item instanceof ArrayList){
            ((ArrayList)item).forEach(it -> {
                mImageList.add(new ImageItem(it.toString()+".jpg"));
            });
        }
    }

    protected String floatValueToDescriptionString(float value) {
        return value == 0 ? "" : String.valueOf(value).replace(".0", "");
    }

    protected String intValueToDescriptionString(int value) {
        return value == 0 ? "" : String.valueOf(value);
    }

    public void addImageList(ArrayList<String> images) {
        mImageList.clear();
        images.forEach(it -> mImageList.add(new ImageItem(it)));
    }

}
