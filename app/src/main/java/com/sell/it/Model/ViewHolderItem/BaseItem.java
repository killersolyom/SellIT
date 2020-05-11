package com.sell.it.Model.ViewHolderItem;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public abstract class BaseItem implements Serializable {
    @Exclude
    public abstract int getViewType();

}
