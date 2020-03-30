package com.sell.it.Model;

import com.sell.it.Adapter.ItemType;

public class BaseDefaultItem extends BaseItem {

    private String mMessage;

    public BaseDefaultItem(){}

    public String getMessage(){ return mMessage;}

    public void setMessage(String mMessage){
        this.mMessage = mMessage;
    }

    @Override
    public int getItemType() {
        return ItemType.BASE_DEFAULT_TYPE;
    }
}
