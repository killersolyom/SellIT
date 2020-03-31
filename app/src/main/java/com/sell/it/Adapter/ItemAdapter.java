package com.sell.it.Adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ViewHolder.BaseViewHolder;
import com.sell.it.Model.BaseItem;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<BaseItem> mItemList = new ArrayList<>();
    private Context mContext;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return LayoutSelector.getLayoutForItem(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindItem(mItemList.get(position),getOrientation());
    }

    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    private int getOrientation() {
        return mContext.getResources().getConfiguration().orientation;
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





