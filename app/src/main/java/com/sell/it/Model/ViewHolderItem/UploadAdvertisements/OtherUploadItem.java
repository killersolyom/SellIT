package com.sell.it.Model.ViewHolderItem.UploadAdvertisements;

import com.sell.it.Model.Constant.Values;

public class OtherUploadItem extends DefaultUploadAdvertisementItem {

    @Override
    public String getItemType() {
        return Values.ItemType.OTHERS_TYPE;
    }

    @Override
    public String getCategoryType() {
        return Values.CategoryType.OTHER_TYPE;
    }

}
