package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;

public abstract class LaptopItem extends BaseComputeUnitItem {

    private int mNumberOfUsbPorts;
    private boolean mHasDvdRom;

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
    public String getItemType() {
        return Values.ItemType.LAPTOP_TYPE;
    }

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
