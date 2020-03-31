package com.sell.it.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ViewHolder.BaseViewHolder;
import com.sell.it.Model.BaseItem;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<BaseItem> mItemList = new ArrayList<>();

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return LayoutSelector.getLayoutForItem(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindItem(mItemList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void addItem(BaseItem item) {
        mItemList.add(item);
        notifyItemInserted(mItemList.indexOf(item));
    }

    public void addItemList(ArrayList<BaseItem> itemList) {
        mItemList.clear();
        mItemList.addAll(itemList);
    }

}





