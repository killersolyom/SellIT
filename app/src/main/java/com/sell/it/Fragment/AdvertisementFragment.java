package com.sell.it.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.Event;
import com.sell.it.Model.ViewHolderItem.Advertisements.CameraItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.CarItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.LaptopItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.MobilePhoneItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.OtherItem;
import com.sell.it.Model.ViewHolderItem.BaseItem;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.DisplayUtils;
import com.sell.it.Utility.FragmentNavigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;
import static com.sell.it.Model.ViewHolderItem.Advertisements.DefaultAdvertisementItem.ITEM_KEY;
import static com.sell.it.Utility.DatabaseManager.ALL_ADVERTISEMENT_KEY;

public class AdvertisementFragment extends BaseFragment {

    private RecyclerView mAdvertisementRecyclerView;
    private ImageView mAddAdvertisementView;
    private ImageView mCategorySelectorView;
    private GridLayoutManager mLayoutManager;
    private ItemAdapter mItemAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_advertisement;
    }

    @Override
    protected void findView(View view) {
        mAdvertisementRecyclerView = view.findViewById(R.id.advertisement_recycler_view);
        mAddAdvertisementView = view.findViewById(R.id.add_advertisement);
        mCategorySelectorView = view.findViewById(R.id.select_category);
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
        mAddAdvertisementView.setOnClickListener(v -> FragmentNavigation.verifyUser());
        mCategorySelectorView.setOnClickListener(v -> FragmentNavigation.showCategorySelectorDialog());
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


    @Override
    protected void restoreItems(Bundle bundle) {
        if (BundleUtil.canCast(bundle, TAG, ArrayList.class)) {
            loadItems(BundleUtil.castItem(bundle, TAG, ArrayList.class));
        }
    }

    @Override
    protected Bundle saveItems() {
        return BundleUtil.createBundle(TAG, mItemAdapter.getItemList());
    }

    @Override
    public boolean onEvent(Event event) {
        switch (event.getEventType()) {
            case Event.TYPE_FIREBASE:
                switch (event.getAction()) {
                    case Event.ACTION_GET_ALL_ADVERTISEMENT:
                        if (BundleUtil.canCast(event.getExtras(), ALL_ADVERTISEMENT_KEY, HashMap.class)) {
                            mItemAdapter.clearItems();
                            loadAllAdvertisements(BundleUtil.castItem(event.getExtras(), ALL_ADVERTISEMENT_KEY, HashMap.class));
                        }
                        return true;
                }
        }
        return false;
    }

    private void loadAllAdvertisements(HashMap<?, ?> allItem) {
        allItem.entrySet().forEach(it -> {
            if (it.getValue() instanceof HashMap) {
                loadCategoryAdvertisement((HashMap<?, ?>) it.getValue());
            }
        });
    }

    private void loadCategoryAdvertisement(HashMap<?, ?> allCategory) {
        allCategory.entrySet().forEach(it2 -> {
            if (it2.getValue() instanceof HashMap) {
                loadSubCategoryAdvertisement((HashMap<?, ?>) it2.getValue());
            }
        });
    }

    private void loadSubCategoryAdvertisement(HashMap<?, ?> allSubCategory) {
        allSubCategory.entrySet().forEach(it3 -> {
            if (it3.getValue() instanceof HashMap) {
                loadAdvertisementItem((HashMap<String, Object>) it3.getValue());
            }
        });
    }

    private void loadAdvertisementItem(HashMap<String, Object> advertisementItem) {
        String itemType = Objects.requireNonNull(advertisementItem.get(ITEM_KEY)).toString();
        switch (itemType) {
            case Values.ItemType.CAMERA_TYPE:
                mItemAdapter.addItem(new CameraItem(advertisementItem));
                break;
            case Values.ItemType.AUTOMOBILE_TYPE:
                mItemAdapter.addItem(new CarItem(advertisementItem));
                break;
            case Values.ItemType.LAPTOP_TYPE:
                mItemAdapter.addItem(new LaptopItem(advertisementItem));
                break;
            case Values.ItemType.MOBILE_PHONE_TYPE:
                mItemAdapter.addItem(new MobilePhoneItem(advertisementItem));
                break;
            case Values.ItemType.OTHERS_TYPE:
                mItemAdapter.addItem(new OtherItem(advertisementItem));
                break;
        }
    }

}
