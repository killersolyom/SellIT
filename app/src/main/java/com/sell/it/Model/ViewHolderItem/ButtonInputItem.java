package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Model.Constant.Values;

public class ButtonInputItem extends BaseValueInputItem {

    private Runnable mClickRunnable;

    public ButtonInputItem(String title, Runnable onClickRunnable) {
        super(null, title, false);
        this.mClickRunnable = onClickRunnable;
    }

    public void runRunnable() {
        if (mClickRunnable != null) {
            mClickRunnable.run();
        }
    }

    @Override
    public int getViewType() {
        return Values.ViewType.BUTTON_ITEM_TYPE;
    }

}
