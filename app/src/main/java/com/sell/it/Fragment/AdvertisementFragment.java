package com.sell.it.Fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Model.ViewHolderItem.BaseAdvertisementItem;
import com.sell.it.Model.ViewHolderItem.BaseItem;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.DisplayUtils;

import java.util.ArrayList;
import java.util.Random;

import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;

public class AdvertisementFragment extends BaseFragment {

    private RecyclerView mAdvertisementRecyclerView;
    private GridLayoutManager mLayoutManager;
    private ItemAdapter mItemAdapter;

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
        mLayoutManager = new GridLayoutManager(getContext(), getSpanCount());
    }

    private void initRecyclerView(ArrayList<BaseItem> itemList) {
        mItemAdapter = new ItemAdapter(getSpanCount(), itemList);
        mAdvertisementRecyclerView.setLayoutManager(mLayoutManager);
        mAdvertisementRecyclerView.setItemViewCacheSize(4 * getSpanCount());
        mAdvertisementRecyclerView.setAdapter(mItemAdapter);
        saveItems();
    }

    private int getSpanCount() {
        return DisplayUtils.getOrientation() == PORTRAIT ?
                DataManager.getPortraitColumnNumber() : DataManager.getLandscapeColumnNumber();
    }

    private void addItems() {
        ArrayList<BaseItem> itemArrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            BaseAdvertisementItem advertisementItem = new BaseAdvertisementItem();
            advertisementItem.setTitle("Title for advertisement " + new Random().nextInt(100));
            itemArrayList.add(advertisementItem);
        }
        initRecyclerView(itemArrayList);
    }

    @Override
    protected void restoreItems(Bundle bundle) {
        if (BundleUtil.canCast(bundle, TAG, ArrayList.class)) {
            initRecyclerView(BundleUtil.castItem(bundle, TAG, ArrayList.class));
        } else {
            addItems();
        }
    }

    @Override
    protected Bundle saveItems() {
        return BundleUtil.createBundle(TAG, mItemAdapter.getItemList());
    }

}
