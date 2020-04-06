package com.sell.it.Adapter.ViewHolder;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<GenericItem> extends RecyclerView.ViewHolder {

    ViewGroup mItemLayout;

    BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        findView(itemView);
    }

    protected void findView(View itemView) {
    }

    public void bindItem(GenericItem genericItem) {
    }

    protected void onItemClicked(GenericItem genericItem) {
    }

    public void unBindItem() {
    }
}
