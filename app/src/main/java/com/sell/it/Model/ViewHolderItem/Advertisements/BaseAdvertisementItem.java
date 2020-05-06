package com.sell.it.Model.ViewHolderItem.Advertisements;

import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.ViewHolderItem.BaseDefaultItem;
import com.sell.it.Model.ViewHolderItem.BaseItem;
import com.sell.it.Model.ViewHolderItem.ImageItem;

import java.util.ArrayList;

public class BaseAdvertisementItem extends BaseDefaultItem {

    private String mId;
    private String mTitle;
    private String mDescription;
    private float mPrice;
    private String mManufacturer;
    private String mOwner;
    private ArrayList<BaseItem> mImageList = new ArrayList<>();

    public BaseAdvertisementItem() {
    }

    public ImageItem getFirstImage() {
        return mImageList.isEmpty() ? new ImageItem() : (ImageItem) mImageList.get(0);
    }

    public ArrayList<BaseItem> getImageList() {
        return mImageList;
    }

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

    public String getManufacturer() {
        return mManufacturer;
    }

    public void setManufacturer(String mManufacturer) {
        this.mManufacturer = mManufacturer;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String mOwner) {
        this.mOwner = mOwner;
    }

    @Override
    public int getItemType() {
        return Values.ItemType.BASE_ADVERTISEMENT_TYPE;
    }
    
}
