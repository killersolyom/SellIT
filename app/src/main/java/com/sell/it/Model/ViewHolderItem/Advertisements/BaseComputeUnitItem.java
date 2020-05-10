package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.Map;

public abstract class BaseComputeUnitItem extends BaseElectronicUtilitiesItem {

    public final static String CPU_KEY = "CPU_KEY";
    public final static String MEMORY_KEY = "MEMORY_KEY";
    public final static String STORAGE_KEY = "STORAGE_KEY";

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
    protected void initItems(Map<String, Object> items) {
        super.initItems(items);
        mCpu = getStringValue(items.get(CPU_KEY));
        mMemory = getIntValue(items.get(MEMORY_KEY));
        mStorage = getIntValue(items.get(STORAGE_KEY));
    }

    @Exclude
    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_cpu, getCpu());
        addToListIfExist(descriptionList, R.string.advertisement_memory, intValueToDescriptionString(mMemory));
        addToListIfExist(descriptionList, R.string.advertisement_storage, intValueToDescriptionString(mStorage));
        return descriptionList;
    }

}
