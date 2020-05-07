package com.sell.it.Model.ViewHolderItem.UploadAdvertisements;

import com.sell.it.Model.ViewHolderItem.BaseDefaultItem;

public abstract class DefaultUploadAdvertisementItem extends BaseDefaultItem {

    private String mId;
    private float mPrice;
    private String mTitle;
    private String mDescription;
    private String mImage;

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    public String getImage() { return mImage; }

    public abstract String getItemType();

    public abstract String getCategoryType();

}
