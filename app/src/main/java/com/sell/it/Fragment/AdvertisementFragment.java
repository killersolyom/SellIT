package com.sell.it.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Model.ViewHolderItem.BaseAdvertisementItem;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;
import com.sell.it.Utility.DataCacheUtil;
import com.sell.it.Utility.DisplayUtils;

import java.util.ArrayList;
import java.util.Random;

import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;

public class AdvertisementFragment extends BaseFragment {

    private RecyclerView mAdvertisementRecyclerView;
    private GridLayoutManager mLayoutManager;
    private ItemAdapter mItemAdapter = new ItemAdapter();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_advertisement;
    }

    @Override
    protected void findView(View view) {
        mAdvertisementRecyclerView = view.findViewById(R.id.advertisement_recycler_view);
    }

    @Override
    protected void initComponents() {
        int spanCount = DisplayUtils.getOrientation() == PORTRAIT ? 2 : 3;
        mItemAdapter.setSpanCount(spanCount);
        mLayoutManager = new GridLayoutManager(getContext(), spanCount);
        mAdvertisementRecyclerView.setItemViewCacheSize(4 * spanCount);
        mAdvertisementRecyclerView.setLayoutManager(mLayoutManager);
        mAdvertisementRecyclerView.setAdapter(mItemAdapter);
    }

    private void addItems() {
        //TODO Dummy data generator, remove it
        if (mItemAdapter.isEmpty()) {
            Log.d("3ss", "addItems");
            for (int i = 0; i < 100; i++) {
                BaseAdvertisementItem advertisementItem = new BaseAdvertisementItem();
                int random = new Random().nextInt(100);
                advertisementItem.setTitle("Title for advertisement " + random);
                mItemAdapter.addItem(advertisementItem);
            }
            saveItems();
        }
    }

    @Override
    protected void restoreItems() {
        Bundle bundle = DataCacheUtil.getItem(TAG);
        if (mItemAdapter.isEmpty() && BundleUtil.canCast(bundle, TAG, ArrayList.class)) {
            mItemAdapter.addItemList(BundleUtil.castItem(bundle, TAG, ArrayList.class));
        } else {
            addItems();
        }
    }

    @Override
    protected void saveItems() {
        DataCacheUtil.addItem(TAG, BundleUtil.createBundle(TAG, mItemAdapter.getItemList()));
    }
}
