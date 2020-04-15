package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Communication.RecyclerViewInterface;
import com.sell.it.Model.ViewHolderItem.BaseItem;

import java.util.ArrayList;

public class CustomRecyclerView extends RecyclerView implements RecyclerViewInterface {

    private ItemAdapter mItemAdapter;
    private ArrayList<BaseItem> mItemList = new ArrayList<>();

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mItemAdapter = new ItemAdapter(this);
    }

    @Override
    public BaseItem getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public int getViewType(int position) {
        return mItemList.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void initParams(LayoutManager layoutManager, int cacheSize) {
        initParams(layoutManager);
        setItemViewCacheSize(cacheSize);
    }

    public void initParams(LayoutManager layoutManager) {
        setAdapter(mItemAdapter);
        setLayoutManager(layoutManager);
    }

    public void addItemList(ArrayList<BaseItem> itemList) {
        mItemList.clear();
        mItemList.addAll(itemList);
    }

    public void addItem(BaseItem item) {
        mItemList.add(item);
        mItemAdapter.notifyItemInserted(mItemList.indexOf(item));
    }

    public void clearItems() {
        mItemList.clear();
    }

}
