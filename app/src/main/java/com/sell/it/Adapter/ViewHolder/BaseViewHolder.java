package com.sell.it.Adapter.ViewHolder;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Model.BaseItem;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    ViewGroup mItemLayout;

    protected abstract void findView(View itemView);

    BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        findView(itemView);
    }

    public void setClickListener(View.OnClickListener clickListener) {
        mItemLayout.setOnClickListener(clickListener);
    }

    public abstract void bindItem(BaseItem item);
}
