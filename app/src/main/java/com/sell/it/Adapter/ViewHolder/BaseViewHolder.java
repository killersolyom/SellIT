package com.sell.it.Adapter.ViewHolder;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<GenericItem> extends RecyclerView.ViewHolder {

    protected Context context;

    BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        findView(itemView);
        context = itemView.getContext();
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
