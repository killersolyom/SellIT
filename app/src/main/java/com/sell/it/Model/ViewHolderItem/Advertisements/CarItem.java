package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.Model.Constant.Values;

import java.util.ArrayList;

public class CarItem extends BaseAdvertisementItem {

    private String mHorsePower;
    private String mColor;
    private int mYearOfProduction;
    private String mEngineType;
    private float mEngineSize;
    private String mTireSize;
    private int mDoorNumber;

    public String getHorsePower() {
        return mHorsePower;
    }

    public void setHorsePower(String mHorsePower) {
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
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        return null;
    }

}
