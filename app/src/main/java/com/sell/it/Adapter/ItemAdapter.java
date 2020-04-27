package com.sell.it.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ViewHolder.BaseViewHolder;
import com.sell.it.Model.ViewHolderItem.BaseDefaultItem;
import com.sell.it.Model.ViewHolderItem.BaseItem;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<BaseItem> mItemList = new ArrayList<>();
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
        return mItemList.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void setSpanCount(int spanCount) {
        mSpanCount = spanCount;
    }

    public void addItemList(ArrayList<BaseItem> itemList) {
        mItemList = itemList;
    }

    public void addItem(BaseDefaultItem item) {
        mItemList.add(item);
        notifyItemInserted(mItemList.indexOf(item));
    }

    public void clearItems() {
        mItemList.clear();
    }

    public boolean isEmpty() {
        return mItemList.isEmpty();
    }

    public ArrayList<BaseItem> getItemList() {
        return mItemList;
    }
}