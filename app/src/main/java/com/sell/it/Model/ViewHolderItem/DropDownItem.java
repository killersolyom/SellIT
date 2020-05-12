package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.CustomPairItem;

import java.util.ArrayList;

public class DropDownItem extends BaseValueInputItem {

    ArrayList<CustomPairItem<String, Class<?>>> mItemList;

    public DropDownItem(ValueListener valueListener, String title, ArrayList<CustomPairItem<String, Class<?>>> items) {
        super(valueListener, title, false);
        mItemList = items;
    }

    public ArrayList<CustomPairItem<String, Class<?>>> getItems() {
        return mItemList;
    }

    @Override
    public int getViewType() {
        return Values.ViewType.DROP_DOWN_ITEM_TYPE;
    }

}
