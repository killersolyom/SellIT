package com.sell.it.Fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.sell.it.CustomView.CustomRecyclerView;
import com.sell.it.Model.ViewHolderItem.BaseAdvertisementItem;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;

public class AdvertisementFragment extends BaseFragment {

    private static final String SAVE_ARRAY_ITEMS_KEY = "SaveArrayItems";

    private CustomRecyclerView mAdvertisementRecyclerView;
    private GridLayoutManager mLayoutManager;

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
        int spanCount = getOrientation() == PORTRAIT ? 2 : 3;
        mLayoutManager = new GridLayoutManager(getContext(), spanCount);
        mAdvertisementRecyclerView.initParams(mLayoutManager, 8);

        //TODO Dummy data generator, remove it
        if (mAdvertisementRecyclerView.isEmpty()) {
            for (int i = 0; i < 100; i++) {
                BaseAdvertisementItem advertisementItem = new BaseAdvertisementItem();
                advertisementItem.setTitle("Title for advertisement " + ThreadLocalRandom.current().nextInt(100));
                mAdvertisementRecyclerView.addItem(advertisementItem);
            }
        }
    }

    @Override
    protected void restoreItems(Bundle bundle) {
        if (BundleUtil.canCast(bundle, SAVE_ARRAY_ITEMS_KEY, ArrayList.class)) {
            mAdvertisementRecyclerView.addItemList(
                    BundleUtil.castItem(bundle, SAVE_ARRAY_ITEMS_KEY, ArrayList.class));
        }
    }

    @Override
    protected void saveItems(Bundle bundle) {
        bundle.putSerializable(SAVE_ARRAY_ITEMS_KEY, mAdvertisementRecyclerView.getItemList());
    }
}
