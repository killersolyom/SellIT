package com.sell.it.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;

import com.sell.it.CustomView.CustomRecyclerView;
import com.sell.it.Model.Event;
import com.sell.it.Model.ViewHolderItem.BaseAdvertisementItem;
import com.sell.it.R;

import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;

public class AdvertisementFragment extends BaseFragment {

    private CustomRecyclerView mAdvertisementRecyclerView;
    private GridLayoutManager mLayoutManager;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_advertisement, container, false);
    }

    @Override
    protected void findView(View view) {
        mAdvertisementRecyclerView = view.findViewById(R.id.advertisement_recycler_view);
    }

    @Override
    protected void initComponents() {
        mLayoutManager = new GridLayoutManager(getContext(), getColumnNumberByOrientation(getOrientation()));
        mAdvertisementRecyclerView.initParams(mLayoutManager, 8);

        //TODO Dummy data generator, remove it
        for (int i = 0; i < 100; i++) {
            BaseAdvertisementItem advertisementItem = new BaseAdvertisementItem();
            advertisementItem.setTitle("Title for advertisement " + i);
            mAdvertisementRecyclerView.addItem(advertisementItem);
        }
    }

    private void handleRotationEvent(String orientation) {
        mLayoutManager.setSpanCount(getColumnNumberByOrientation(orientation));
    }

    private int getColumnNumberByOrientation(String orientation) {
        return orientation.equals(PORTRAIT) ? 2 : 3;
    }

    @Override
    public void onEvent(Event event) {
        switch (event.getEventType()) {
            case Event.SCREEN_ORIENTATION:
                handleRotationEvent(event.getEvent());
            default:
                break;

        }
    }

}
