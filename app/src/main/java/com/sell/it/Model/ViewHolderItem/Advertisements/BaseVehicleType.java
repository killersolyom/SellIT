package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;
import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BaseVehicleType extends BaseAdvertisementItem {

    public final static String COLOR_KEY = "COLOR";
    public final static String ENGINE_TYPE_KEY = "ENGINE_TYPE";
    public final static String TIRE_KEY = "TIRE_SIZE";
    public final static String ENGINE_SIZE_KEY = "ENGINE_SIZE";
    public final static String HORSE_POWER_KEY = "HORSE_POWER";
    public final static String PRODUCTION_YEAR_KEY = "PRODUCTION_YEAR";

    private String mColor;
    private String mEngineType;
    private String mTireSize;
    private float mEngineSize;
    private float mHorsePower;
    private int mYearOfProduction;

    public BaseVehicleType() {
    }

    public BaseVehicleType(HashMap<String, Object> mItemData) {
        initItems(mItemData);
    }

    @PropertyName(HORSE_POWER_KEY)
    public float getHorsePower() {
        return mHorsePower;
    }

    @PropertyName(COLOR_KEY)
    public String getColor() {
        return mColor;
    }

    @PropertyName(PRODUCTION_YEAR_KEY)
    public int getYearOfProduction() {
        return mYearOfProduction;
    }

    @PropertyName(ENGINE_TYPE_KEY)
    public String getEngineType() {
        return mEngineType;
    }

    @PropertyName(ENGINE_SIZE_KEY)
    public float getEngineSize() {
        return mEngineSize;
    }

    @PropertyName(TIRE_KEY)
    public String getTireSize() {
        return mTireSize;
    }

    @Exclude
    @Override
    public String getCategoryType() {
        return Values.CategoryType.VEHICLE_TYPE;
    }

    @Override
    protected void initItems(HashMap<String, Object> items) {
        super.initItems(items);
        mColor = getStringValue(items.get(COLOR_KEY));
        mEngineType = getStringValue(items.get(ENGINE_TYPE_KEY));
        mTireSize = getStringValue(items.get(TIRE_KEY));
        mEngineSize = getFloatValue(items.get(ENGINE_SIZE_KEY));
        mHorsePower = getFloatValue(items.get(HORSE_POWER_KEY));
        mYearOfProduction = getIntValue(items.get(PRODUCTION_YEAR_KEY));
    }

    @Exclude
    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_horse_power, String.valueOf(mHorsePower));
        addToListIfExist(descriptionList, R.string.advertisement_color, mColor);
        addToListIfExist(descriptionList, R.string.advertisement_production_year, intValueToDescriptionString(mYearOfProduction));
        addToListIfExist(descriptionList, R.string.advertisement_engine_type, mEngineType);
        addToListIfExist(descriptionList, R.string.advertisement_engine_size, floatValueToDescriptionString(mEngineSize));
        addToListIfExist(descriptionList, R.string.advertisement_tire_size, mTireSize);
        return descriptionList;
    }

}
