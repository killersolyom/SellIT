package com.sell.it.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Model.ViewHolderItem.BaseAdvertisementItem;
import com.sell.it.Model.ViewHolderItem.BaseItem;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.DisplayUtils;
import com.sell.it.Utility.FragmentNavigation;

import java.util.ArrayList;
import java.util.Random;

import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;

public class AdvertisementFragment extends BaseFragment {

    private RecyclerView mAdvertisementRecyclerView;
    private ImageView mAddAdvertisementView;
    private GridLayoutManager mLayoutManager;
    private ItemAdapter mItemAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_advertisement;
    }

    @Override
    protected int getBackgroundColorId() {
        return R.color.colorTransparentWhite;
    }

    @Override
    protected void findView(View view) {
        mAdvertisementRecyclerView = view.findViewById(R.id.advertisement_recycler_view);
        mAddAdvertisementView = view.findViewById(R.id.add_advertisement);
    }

    @Override
    protected void initComponents() {
        mLayoutManager = new GridLayoutManager(getContext(), getSpanCount());
        mItemAdapter = new ItemAdapter(getSpanCount());
        mAdvertisementRecyclerView.setAdapter(mItemAdapter);
        mAdvertisementRecyclerView.setLayoutManager(mLayoutManager);
        mAdvertisementRecyclerView.setItemViewCacheSize(4 * getSpanCount());
    }

    @Override
    protected void initListeners() {
        mAddAdvertisementView.setOnClickListener(v -> FragmentNavigation.showAddAdvertisementFragment());
    }

    private void loadItems(ArrayList<BaseItem> itemList) {
        mItemAdapter.setSpanCount(getSpanCount());
        mItemAdapter.addItemList(itemList);
        saveItems();
    }

    private int getSpanCount() {
        return DisplayUtils.getOrientation() == PORTRAIT ?
                DataManager.getPortraitColumnNumber() : DataManager.getLandscapeColumnNumber();
    }

    private void addItems() {
        for (int i = 0; i < 100; i++) {
            BaseAdvertisementItem advertisementItem = new BaseAdvertisementItem();
            advertisementItem.setTitle("Title for advertisement " + new Random().nextInt(100));
            mItemAdapter.addItem(advertisementItem);
        }
        saveItems();
    }

    @Override
    protected void restoreItems(Bundle bundle) {
        if (BundleUtil.canCast(bundle, TAG, ArrayList.class)) {
            loadItems(BundleUtil.castItem(bundle, TAG, ArrayList.class));
        } else {
            addItems();
        }
    }

    @Override
    protected Bundle saveItems() {
        return BundleUtil.createBundle(TAG, mItemAdapter.getItemList());
    }

}
