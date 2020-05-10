package com.sell.it.Model.ViewHolderItem;

import android.util.Pair;

import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.Constant.Values;

import java.util.ArrayList;

public class DropDownItem extends BaseValueInputItem {

    ArrayList<Pair<String, Class<?>>> mItemList;

    public DropDownItem(ValueListener valueListener, String title, ArrayList<Pair<String, Class<?>>> items) {
        super(valueListener, title, false);
        mItemList = items;
    }

    public ArrayList<Pair<String, Class<?>>> getItems() {
        return mItemList;
    }

    @Override
    public int getViewType() {
        return Values.ViewType.DROP_DOWN_ITEM_TYPE;
    }

}
