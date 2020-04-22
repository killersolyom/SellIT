package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Model.Constant.Values;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class BaseAdvertisementItem extends BaseDefaultItem {

    private String mTitle;
    private ArrayList<String> mImageList;

    public BaseAdvertisementItem() {
        mImageList = new ArrayList<>();
        int random = (int) (100 * ThreadLocalRandom.current().nextDouble(2, 10));
        mImageList.add("https://picsum.photos/" + random);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getFirstImage() {
        return mImageList.isEmpty() ? null : mImageList.get(0);
    }

    public ArrayList<String> getImageList() {
        return mImageList;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public int getItemType() {
        return Values.ItemType.BASE_ADVERTISEMENT_TYPE;
    }
}
