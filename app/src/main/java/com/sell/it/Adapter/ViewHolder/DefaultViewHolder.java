package com.sell.it.Adapter.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;

import com.sell.it.Model.ViewHolderItem.BaseDefaultItem;

public class DefaultViewHolder extends BaseViewHolder<BaseDefaultItem> {


    public DefaultViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(BaseDefaultItem baseDefaultItem) {
        //Nothing to do here, if we are here that means something went wrong :(
    }

    @Override
    protected void onItemClicked(BaseDefaultItem baseDefaultItem) {
    }

    @Override
    protected void findView(View itemView) {
    }

}
