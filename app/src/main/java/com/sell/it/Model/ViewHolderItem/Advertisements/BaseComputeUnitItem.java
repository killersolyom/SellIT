package com.sell.it.Model.ViewHolderItem.Advertisements;

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
}
