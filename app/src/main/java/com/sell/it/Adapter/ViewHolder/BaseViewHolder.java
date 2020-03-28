package com.sell.it.Adapter.ViewHolder;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<GenericItem> extends RecyclerView.ViewHolder {

    ViewGroup mItemLayout;

    protected abstract void findView(View itemView);

    BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        findView(itemView);
    }

    public abstract void bindItem(GenericItem item);

    protected abstract void onItemClicked(GenericItem item);
}
