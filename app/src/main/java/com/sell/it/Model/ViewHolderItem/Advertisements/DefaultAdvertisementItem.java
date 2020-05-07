package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.Model.ViewHolderItem.BaseDefaultItem;
import com.sell.it.Model.ViewHolderItem.BaseItem;
import com.sell.it.Model.ViewHolderItem.ImageItem;

import java.util.ArrayList;

public abstract class DefaultAdvertisementItem extends BaseDefaultItem {

    private String mId;
    private float mPrice;
    private String mTitle;
    private String mDescription;
    private ArrayList<BaseItem> mImageList = new ArrayList<>();

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

    public ImageItem getFirstImage() {
        return mImageList.isEmpty() ? new ImageItem() : (ImageItem) mImageList.get(0);
    }

    public ArrayList<BaseItem> getImageList() {
        return mImageList;
    }

    public abstract String getItemType();

    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        return new ArrayList<Pair<Integer, String>>();
    }

}
