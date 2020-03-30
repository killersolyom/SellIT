package com.sell.it.Model;

import com.sell.it.Adapter.ItemType;

public class FakeItem extends BaseItem {

    private String mTitle;

    public FakeItem() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public int getItemType() {
        return 512;
    }
}
