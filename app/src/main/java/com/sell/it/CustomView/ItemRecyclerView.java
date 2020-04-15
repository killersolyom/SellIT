package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;

public class ItemRecyclerView extends RecyclerView {

    public ItemRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void initParams(ItemAdapter adapter, LayoutManager layoutManager, int cacheSize) {
        setAdapter(adapter);
        setLayoutManager(layoutManager);
        setItemViewCacheSize(cacheSize);
    }

    public void initParams(ItemAdapter adapter, LayoutManager layoutManager) {
        setAdapter(adapter);
        setLayoutManager(layoutManager);
    }

}
