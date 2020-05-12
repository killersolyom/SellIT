package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;
import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.Map;

public class CarItem extends BaseVehicleType {

    public final static String DOOR_NUMBER_KEY = "DOOR_NUMBER";

    private int mDoorNumber;

    public CarItem(Map<String, Object> mItemData) {
        initItems(mItemData);
    }

    @PropertyName(DOOR_NUMBER_KEY)
    public int getDoorNumber() {
        return mDoorNumber;
    }

    @Override
    @PropertyName(ITEM_KEY)
    public String getItemType() {
        return Values.ItemType.AUTOMOBILE_TYPE;
    }

    @Override
    protected void initItems(Map<String, Object> items) {
        super.initItems(items);
        mDoorNumber = getIntValue(items.get(DOOR_NUMBER_KEY));
    }

    @Exclude
    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_door_number, intValueToDescriptionString(mDoorNumber));
        return descriptionList;
    }

}
