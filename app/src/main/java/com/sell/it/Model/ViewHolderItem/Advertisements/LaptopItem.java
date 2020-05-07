package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.Model.Constant.Values;

import java.util.ArrayList;

public class LaptopItem extends BaseComputeUnitItem {

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
        return null;
    }

}
