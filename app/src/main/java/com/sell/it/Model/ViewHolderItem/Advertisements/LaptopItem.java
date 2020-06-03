package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;
import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.HashMap;

public class LaptopItem extends BaseComputeUnitItem {

    public final static String USB_PORT_KEY = "USB_PORT_NUMBER";
    public final static String DVD_KEY = "DVD_ROM";

    private int mNumberOfUsbPorts;
    private boolean dvdRom;

    public LaptopItem(HashMap<String, Object> mItemData) {
        initItems(mItemData);
    }

    @PropertyName(USB_PORT_KEY)
    public int getNumberOfUsbPorts() {
        return mNumberOfUsbPorts;
    }

    @PropertyName(DVD_KEY)
    public boolean isDvdRom() {
        return dvdRom;
    }

    @Override
    protected void initItems(HashMap<String, Object> items) {
        super.initItems(items);
        mNumberOfUsbPorts = getIntValue(items.get(USB_PORT_KEY));
        dvdRom = getBooleanValue(items.get(DVD_KEY));
    }

    @Override
    @PropertyName(ITEM_KEY)
    public String getItemType() {
        return Values.ItemType.LAPTOP_TYPE;
    }

    @Exclude
    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_usb_number, intValueToDescriptionString(mNumberOfUsbPorts));
        if (dvdRom) {
            descriptionList.add(new Pair<>(R.string.advertisement_dvd_rom, ""));
        }
        return descriptionList;
    }

}
