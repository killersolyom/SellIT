package com.sell.it.Adapter.ViewHolder;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<GenericItem> extends RecyclerView.ViewHolder {

    protected View mItemView;

    BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        findView(itemView);
        mItemView = itemView;
    }

    protected Context getContext() {
        return mItemView.getContext();
    }

    protected void findView(View itemView) {
    }

    public void bindItem(GenericItem genericItem) {
    }

    public void unBindItem() {
    }

    protected void onItemClicked(GenericItem genericItem) {
    }
}
