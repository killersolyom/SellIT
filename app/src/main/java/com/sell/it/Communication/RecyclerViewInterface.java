package com.sell.it.Communication;

import com.sell.it.Model.ViewHolderItem.BaseItem;

public interface RecyclerViewInterface {
    BaseItem getItem(int position);

    int getViewType(int position);

    int getItemCount();
}
