package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.Map;

public class LaptopItem extends BaseComputeUnitItem {

    public final static String USB_PORT_KEY = "USB_PORT_KEY";
    public final static String DVD_KEY = "DVD_KEY";

    private int mNumberOfUsbPorts;
    private boolean mHasDvdRom;

    public LaptopItem() {
    }

    public LaptopItem(Map<String, Object> mItemData) {
        initItems(mItemData);
    }

    public int getNumberOfUsbPorts() {
        return mNumberOfUsbPorts;
    }

    public void setNumberOfUsbPorts(int mNumberOfUsbPorts) {
        this.mNumberOfUsbPorts = mNumberOfUsbPorts;
    }

    public boolean hasDvdRom() {
        return mHasDvdRom;
    }

    public void setDvdRom(boolean mHasDvdRom) {
        this.mHasDvdRom = mHasDvdRom;
    }

    @Override
    protected void initItems(Map<String, Object> items) {
        super.initItems(items);
        mNumberOfUsbPorts = getIntValue(items.get(USB_PORT_KEY));
        mHasDvdRom = getBooleanValue(items.get(DVD_KEY));
    }

    @Override
    public String getItemType() {
        return Values.ItemType.LAPTOP_TYPE;
    }

    @Override
    public String getCategoryType() {
        return Values.CategoryType.ELECTRONIC_TYPE;
    }

    @Exclude
    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_usb_number, intValueToDescriptionString(mNumberOfUsbPorts));
        if (mHasDvdRom) {
            descriptionList.add(new Pair<>(R.string.advertisement_storage, " "));
        }
        return descriptionList;
    }

}
