package com.sell.it.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Model.ViewHolderItem.BaseAdvertisementItem;
import com.sell.it.R;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class AdvertisementFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private ItemAdapter mAdvertisementAdapter;
    private final int mPortraitColumnNumber = 2;
    private final int mLandscapeColumnNumber = 3;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_advertisement, container, false);
    }

    @Override
    protected void findView(View view) {
        mRecyclerView = view.findViewById(R.id.advertisement_recycler_view);
    }

    @Override
    protected void initComponents() {
        mLayoutManager = new GridLayoutManager(this.getContext(), getColumnNumberByOrientation());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdvertisementAdapter = new ItemAdapter();
        mRecyclerView.setAdapter(mAdvertisementAdapter);
        //TODO Dummy data generator, remove it
        for (int i = 0 ; i < 100 ; i++){
            BaseAdvertisementItem advertisementItem = new BaseAdvertisementItem();
            advertisementItem.setTitle("Title for advertisement " + i);
            mAdvertisementAdapter.addItem(advertisementItem);
        }

    }

    @Override
    protected void handleRotationEvent() {
        mLayoutManager.setSpanCount(getColumnNumberByOrientation());
    }

    private int getColumnNumberByOrientation() {
        return getOrientation() == ORIENTATION_PORTRAIT ? mPortraitColumnNumber : mLandscapeColumnNumber;
    }
}
