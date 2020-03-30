package com.sell.it.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Model.BaseAdvertisementItem;
import com.sell.it.Model.BaseItem;
import com.sell.it.Model.FakeItem;
import com.sell.it.R;

public class AdvertisementFragment extends BaseFragment {

    private RecyclerView mRecycle;
    private GridLayoutManager mLayoutManager;
    private ItemAdapter mAdvertisementAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_advertisement, container, false);
    }

    @Override
    protected void findView(View view) {
        mRecycle = view.findViewById(R.id.advertisement_recycler_view);
    }

    @Override
    protected void initComponents() {
        mLayoutManager = new GridLayoutManager(this.getContext(), 2);
        mRecycle.setLayoutManager(mLayoutManager);
        mAdvertisementAdapter = new ItemAdapter();
        mRecycle.setAdapter(mAdvertisementAdapter);
        BaseAdvertisementItem advItem = new BaseAdvertisementItem();
        advItem.setTitle("Ez egy adv");
        FakeItem fakeItem = new FakeItem();
        fakeItem.setTitle("Ez nem egy adv, ez hiba.");
        mAdvertisementAdapter.addItem(advItem);
        mAdvertisementAdapter.addItem(fakeItem);

    }

}
