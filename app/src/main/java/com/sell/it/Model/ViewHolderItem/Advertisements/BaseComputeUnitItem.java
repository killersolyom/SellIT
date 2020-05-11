package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.Map;

public abstract class BaseComputeUnitItem extends BaseElectronicUtilitiesItem {

    public final static String CPU_KEY = "CPU";
    public final static String MEMORY_KEY = "MEMORY_SIZE";
    public final static String STORAGE_KEY = "STORAGE_SIZE";

    private String mCpu;
    private int mMemory;
    private int mStorage;

    @PropertyName(CPU_KEY)
    public String getCpu() {
        return mCpu;
    }

    @PropertyName(CPU_KEY)
    public void setCpu(String mCpu) {
        this.mCpu = mCpu;
    }

    @PropertyName(MEMORY_KEY)
    public int getMemory() {
        return mMemory;
    }

    @PropertyName(MEMORY_KEY)
    public void setMemory(int mMemory) {
        this.mMemory = mMemory;
    }

    @PropertyName(STORAGE_KEY)
    public int getStorage() {
        return mStorage;
    }

    @PropertyName(STORAGE_KEY)
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
