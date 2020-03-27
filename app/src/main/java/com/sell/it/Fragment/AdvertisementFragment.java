package com.sell.it.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.R;

public class AdvertisementFragment extends BaseFragment {

    private RecyclerView recycle;
    private GridLayoutManager layoutManager;
    private ItemAdapter advertisementAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_advertisement, container, false);
    }

    @Override
    protected void findView(View view) {
        recycle = view.findViewById(R.id.advertisement_recycler_view);
    }

    @Override
    protected void initComponents() {
        layoutManager = new GridLayoutManager(this.getContext(), 2);
        recycle.setLayoutManager(layoutManager);
        advertisementAdapter = new ItemAdapter();
        recycle.setAdapter(advertisementAdapter);
    }

}
