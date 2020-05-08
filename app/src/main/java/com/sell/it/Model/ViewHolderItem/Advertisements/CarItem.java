package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.Map;

public class CarItem extends BaseAdvertisementItem {

    public final static String COLOR_KEY = "COLOR_KEY";
    public final static String ENGINE_TYPE_KEY = "ENGINE_KEY";
    public final static String TIRE_KEY = "TIRE_KEY";
    public final static String ENGINE_SIZE_KEY = "ENGINE_SIZE_KEY";
    public final static String HORSE_POWER_KEY = "HORSE_POWER_KEY";
    public final static String PRODUCTION_YEAR_KEY = "PRODUCTION_YEAR_KEY";
    public final static String DOOR_NUMBER_KEY = "DOOR_NUMBER_KEY";

    private String mColor;
    private String mEngineType;
    private String mTireSize;
    private float mEngineSize;
    private float mHorsePower;
    private int mYearOfProduction;
    private int mDoorNumber;

    public CarItem() {
    }

    public CarItem(Map<String, Object> mItemData) {
        initItems(mItemData);
    }

    public float getHorsePower() {
        return mHorsePower;
    }

    public void setHorsePower(float mHorsePower) {
        this.mHorsePower = mHorsePower;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String mColor) {
        this.mColor = mColor;
    }

    public int getYearOfProduction() {
        return mYearOfProduction;
    }

    public void setYearOfProduction(int mYearOfProduction) {
        this.mYearOfProduction = mYearOfProduction;
    }

    public String getEngineType() {
        return mEngineType;
    }

    public void setEngineType(String mEngineType) {
        this.mEngineType = mEngineType;
    }

    public float getEngineSize() {
        return mEngineSize;
    }

    public void setEngineSize(float mEngineSize) {
        this.mEngineSize = mEngineSize;
    }

    public String getTireSize() {
        return mTireSize;
    }

    public void setTireSize(String mTireSize) {
        this.mTireSize = mTireSize;
    }

    public int getDoorNumber() {
        return mDoorNumber;
    }

    public void setDoorNumber(int mDoorNumber) {
        this.mDoorNumber = mDoorNumber;
    }

    @Override
    public String getItemType() {
        return Values.ItemType.AUTOMOBILE_TYPE;
    }

    @Override
    public String getCategoryType() {
        return Values.CategoryType.VEHICLE_TYPE;
    }

    @Override
    protected void initItems(Map<String, Object> items) {
        super.initItems(items);
        mColor = getStringValue(items.get(COLOR_KEY));
        mEngineType = getStringValue(items.get(ENGINE_TYPE_KEY));
        mTireSize = getStringValue(items.get(TIRE_KEY));
        mEngineSize = getFloatValue(items.get(ENGINE_SIZE_KEY));
        mHorsePower = getFloatValue(items.get(HORSE_POWER_KEY));
        mYearOfProduction = getIntValue(items.get(PRODUCTION_YEAR_KEY));
        mDoorNumber = getIntValue(items.get(DOOR_NUMBER_KEY));
    }

    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_horse_power, String.valueOf(mHorsePower));
        addToListIfExist(descriptionList, R.string.advertisement_color, mColor);
        addToListIfExist(descriptionList, R.string.advertisement_production_year, intValueToDescriptionString(mYearOfProduction));
        addToListIfExist(descriptionList, R.string.advertisement_engine_type, mEngineType);
        addToListIfExist(descriptionList, R.string.advertisement_engine_size, floatValueToDescriptionString(mEngineSize));
        addToListIfExist(descriptionList, R.string.advertisement_tire_size, mTireSize);
        addToListIfExist(descriptionList, R.string.advertisement_door_number, intValueToDescriptionString(mDoorNumber));
        return descriptionList;
    }

}
