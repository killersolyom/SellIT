package com.sell.it.Model.ViewHolderItem.UploadAdvertisements;

import com.sell.it.Model.Constant.Values;

public abstract class LaptopUploadItem extends BaseUploadComputeUnitItem {

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

}
