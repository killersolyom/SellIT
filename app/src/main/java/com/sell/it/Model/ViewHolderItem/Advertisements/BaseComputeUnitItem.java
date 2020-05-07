package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.R;

import java.util.ArrayList;

public abstract class BaseComputeUnitItem extends BaseElectronicUtilitiesItem {

    private String mCpu;
    private int mMemory;
    private int mStorage;

    public String getCpu() {
        return mCpu;
    }

    public void setCpu(String mCpu) {
        this.mCpu = mCpu;
    }

    public int getMemory() {
        return mMemory;
    }

    public void setMemory(int mMemory) {
        this.mMemory = mMemory;
    }

    public int getStorage() {
        return mStorage;
    }

    public void setStorage(int mStorage) {
        this.mStorage = mStorage;
    }

    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_cpu, getCpu());
        addToListIfExist(descriptionList, R.string.advertisement_memory, intValueToDescriptionString(mMemory));
        addToListIfExist(descriptionList, R.string.advertisement_storage, intValueToDescriptionString(mStorage));
        return descriptionList;
    }

}
