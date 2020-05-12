package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;
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
    @PropertyName(ITEM_KEY)
    public String getItemType() {
        return Values.ItemType.OTHERS_TYPE;
    }

    @Override
    @Exclude
    public String getCategoryType() {
        return Values.CategoryType.OTHERS_TYPE;
    }

    @Exclude
    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        return super.getDescriptionList();
    }

}
