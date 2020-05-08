package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.Model.Constant.Values;

import java.util.ArrayList;
import java.util.Map;

public class OtherItem extends DefaultAdvertisementItem {

    public OtherItem() {
    }

    public OtherItem(Map<String, Object> mItemData) {
        initItems(mItemData);
    }

    @Override
    public String getItemType() {
        return Values.ItemType.OTHERS_TYPE;
    }

    @Override
    public String getCategoryType() {
        return Values.CategoryType.OTHER_TYPE;
    }

    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        return super.getDescriptionList();
    }

}
