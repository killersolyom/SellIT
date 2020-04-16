package com.sell.it.Fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.sell.it.CustomView.CustomRecyclerView;
import com.sell.it.Model.ViewHolderItem.BaseAdvertisementItem;
import com.sell.it.R;

import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;

public class AdvertisementFragment extends BaseFragment {

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
        int spanCount = getOrientation().equals(PORTRAIT) ? 2 : 3;
        mLayoutManager = new GridLayoutManager(getContext(), spanCount);
        mAdvertisementRecyclerView.initParams(mLayoutManager, 8);

        //TODO Dummy data generator, remove it
        for (int i = 0; i < 100; i++) {
            BaseAdvertisementItem advertisementItem = new BaseAdvertisementItem();
            advertisementItem.setTitle("Title for advertisement " + i);
            mAdvertisementRecyclerView.addItem(advertisementItem);
        }
    }

}
