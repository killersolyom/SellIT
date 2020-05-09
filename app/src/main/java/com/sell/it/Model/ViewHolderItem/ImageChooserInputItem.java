package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.Constant.Values;

public class ImageChooserInputItem extends BaseValueInputItem {

    public ImageChooserInputItem(ValueListener valueListener) {
        super(valueListener, "", false);
    }

    @Override
    public int getViewType() {
        return Values.ViewType.IMAGE_CHOOSER_ITEM_TYPE;
    }

}
