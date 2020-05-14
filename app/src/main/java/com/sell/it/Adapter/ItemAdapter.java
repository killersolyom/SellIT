package com.sell.it.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ViewHolder.BaseViewHolder;
import com.sell.it.Model.ViewHolderItem.BaseItem;

import java.util.ArrayList;

public class ItemAdapter<Item extends BaseItem> extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<Item> mItemList = new ArrayList<>();
    private int mSpanCount;

    public ItemAdapter() {
        mSpanCount = 1;
    }

    public ItemAdapter(int spanCount) {
        mSpanCount = spanCount;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return LayoutSelector.getLayoutForItem(parent, viewType, mSpanCount);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindItem(mItemList.get(position));
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        holder.unBindItem();
    }

    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void addItemList(ArrayList<Item> itemList) {
        mItemList = itemList;
    }

    public void addItem(Item item) {
        mItemList.add(item);
        notifyItemInserted(mItemList.indexOf(item));
    }

    public void clearItems() {
        mItemList.clear();
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return mItemList.isEmpty();
    }

    public ArrayList<Item> getItemList() {
        return mItemList;
    }
    
}