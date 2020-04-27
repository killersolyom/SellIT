package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Model.Constant.Values;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class BaseAdvertisementItem extends BaseDefaultItem {

    private String mTitle;
    private ArrayList<BaseItem> mImageList;

    public BaseAdvertisementItem() {
        mImageList = new ArrayList<>();
        mImageList.add(new ImageItem("https://picsum.photos/" + getRandom()));
        mImageList.add(new ImageItem("https://picsum.photos/" + getRandom()));
        mImageList.add(new ImageItem("https://picsum.photos/" + getRandom()));
    }

    private int getRandom() {
        //TODO remove this method
        return (int) (100 * ThreadLocalRandom.current().nextDouble(2, 10));
    }

    public String getTitle() {
        return mTitle;
    }

    public ImageItem getFirstImage() {
        return mImageList.isEmpty() ? null : (ImageItem) mImageList.get(0);
    }

    public ArrayList<BaseItem> getImageList() {
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
