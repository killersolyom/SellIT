package com.sell.it.Model.ViewHolderItem.UploadAdvertisements;

import com.sell.it.Model.Constant.Values;

public class CarUploadItem extends BaseUploadAdvertisementItem {

    private String mColor;
    private String mEngineType;
    private String mTireSize;
    private float mEngineSize;
    private float mHorsePower;
    private int mYearOfProduction;
    private int mDoorNumber;

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

}
