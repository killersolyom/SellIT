package com.sell.it.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ViewHolder.BaseViewHolder;
import com.sell.it.Communication.RecyclerViewInterface;

public class ItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private RecyclerViewInterface mRecyclerInterface;

    public ItemAdapter(RecyclerViewInterface recyclerInterface) {
        this.mRecyclerInterface = recyclerInterface;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return LayoutSelector.getLayoutForItem(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindItem(mRecyclerInterface.getItem(position));
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        holder.unBindItem();
    }

    @Override
    public int getItemViewType(int position) {
        return mRecyclerInterface.getViewType(position);
    }

    @Override
    public int getItemCount() {
        return mRecyclerInterface.getItemCount();
    }

}