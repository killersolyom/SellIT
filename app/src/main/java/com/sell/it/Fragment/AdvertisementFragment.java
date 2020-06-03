package com.sell.it.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Dialog.CategorySelectDialog;
import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.Event;
import com.sell.it.Model.ViewHolderItem.Advertisements.CameraItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.CarItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.DefaultAdvertisementItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.LaptopItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.MobilePhoneItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.OtherItem;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.DatabaseManager;
import com.sell.it.Utility.DisplayUtils;
import com.sell.it.Utility.FragmentNavigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static com.sell.it.Model.Constant.Values.ItemType.OTHERS_TYPE;
import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;
import static com.sell.it.Model.ViewHolderItem.Advertisements.DefaultAdvertisementItem.ITEM_KEY;
import static com.sell.it.Utility.DatabaseManager.ALL_ADVERTISEMENT_KEY;
import static com.sell.it.Utility.DatabaseManager.CATEGORY_ADVERTISEMENT_KEY;
import static com.sell.it.Utility.DatabaseManager.ITEM_TYPE_ADVERTISEMENT_KEY;

public class AdvertisementFragment extends BaseFragment {

    private RecyclerView mAdvertisementRecyclerView;
    private ImageView mAddAdvertisementView;
    private ImageView mCategorySelectorView;
    private GridLayoutManager mLayoutManager;
    private SwipeRefreshLayout mFragmentLayout;
    private ItemAdapter<DefaultAdvertisementItem> mItemAdapter;
    private Runnable mRefreshRunnable = new Runnable() {
        @Override
        public void run() {
            mFragmentLayout.setRefreshing(false);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_advertisement;
    }

    @Override
    protected void findView(View view) {
        mAdvertisementRecyclerView = view.findViewById(R.id.advertisement_recycler_view);
        mAddAdvertisementView = view.findViewById(R.id.add_advertisement);
        mCategorySelectorView = view.findViewById(R.id.select_category);
        mFragmentLayout = view.findViewById(R.id.refresh_layout);
    }

    @Override
    protected void initComponents() {
        mLayoutManager = new GridLayoutManager(getContext(), getSpanCount());
        mItemAdapter = new ItemAdapter(getSpanCount());
        mAdvertisementRecyclerView.setAdapter(mItemAdapter);
        mAdvertisementRecyclerView.setLayoutManager(mLayoutManager);
        mAdvertisementRecyclerView.setItemViewCacheSize(4 * getSpanCount());
        mFragmentLayout.setOnRefreshListener(this::onRefresh);
    }

    @Override
    protected void initListeners() {
        mAddAdvertisementView.setOnClickListener(v -> FragmentNavigation.verifyUser());
        mCategorySelectorView.setOnClickListener(v -> FragmentNavigation.showCategorySelectorDialog());
    }

    private void loadItems(ArrayList<DefaultAdvertisementItem> itemList) {
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
        } else {
            DatabaseManager.getAllAdvertisements();
        }
    }

    @Override
    protected Bundle saveItems() {
        return BundleUtil.createBundle(TAG, mItemAdapter.getItemList());
    }

    @Override
    protected void removeCallbacks() {
        mFragmentLayout.removeCallbacks(mRefreshRunnable);
    }

    @Override
    public boolean onEvent(Event event) {
        switch (event.getEventType()) {
            case Event.TYPE_FIREBASE:
                switch (event.getAction()) {
                    case Event.ACTION_GET_ALL_ADVERTISEMENT:
                        if (BundleUtil.canCast(event.getExtras(), ALL_ADVERTISEMENT_KEY, HashMap.class)) {
                            mItemAdapter.clearItems();
                            mFragmentLayout.setRefreshing(false);
                            loadAllAdvertisements(BundleUtil.castItem(event.getExtras(), ALL_ADVERTISEMENT_KEY, HashMap.class));
                        }
                        return true;
                    case Event.ACTION_GET_CATEGORY_ADVERTISEMENT:
                        if (BundleUtil.canCast(event.getExtras(), CATEGORY_ADVERTISEMENT_KEY, HashMap.class)) {
                            mItemAdapter.clearItems();
                            mFragmentLayout.setRefreshing(false);
                            loadCategoryAdvertisement(BundleUtil.castItem(event.getExtras(), CATEGORY_ADVERTISEMENT_KEY, HashMap.class));
                        }
                        return true;
                    case Event.ACTION_GET_ITEM_TYPE_ADVERTISEMENT:
                        if (BundleUtil.canCast(event.getExtras(), ITEM_TYPE_ADVERTISEMENT_KEY, HashMap.class)) {
                            mItemAdapter.clearItems();
                            mFragmentLayout.setRefreshing(false);
                            loadSubCategoryAdvertisement(BundleUtil.castItem(event.getExtras(), ITEM_TYPE_ADVERTISEMENT_KEY, HashMap.class));
                        }
                        return true;
                }
        }
        return false;
    }

    private void onRefresh() {
        mItemAdapter.clearItems();
        mFragmentLayout.postDelayed(mRefreshRunnable, 2000);
        String[] lastSelected = DataManager.getLastSelectedCategory();
        if (lastSelected[0].equals(lastSelected[1])) {
            if (lastSelected[0].equals(CategorySelectDialog.ALL_CATEGORY)) {
                DatabaseManager.getAllAdvertisements();
            } else {
                DatabaseManager.getCategoryAdvertisements(lastSelected[0]);
            }
        } else {
            DatabaseManager.getSubCategoryAdvertisements(lastSelected[0], lastSelected[1]);
        }
    }

    private void loadAllAdvertisements(HashMap<?, ?> allItem) {
        getFilteredStream(allItem).forEach(it ->
                loadCategoryAdvertisement(castToHashMap(it.getValue())));
    }

    private void loadCategoryAdvertisement(HashMap<?, ?> allCategory) {
        getFilteredStream(allCategory).forEach(it ->
                loadSubCategoryAdvertisement(castToHashMap(it.getValue())));
    }

    private void loadSubCategoryAdvertisement(HashMap<?, ?> allSubCategory) {
        getFilteredStream(allSubCategory).forEach(it ->
                loadAdvertisementItem(castToHashMap(it.getValue())));
    }

    private boolean isHashMap(Object item) {
        return item instanceof HashMap;
    }

    private HashMap<String, Object> castToHashMap(Object item) {
        return (HashMap<String, Object>) item;
    }

    private Stream<? extends Map.Entry<?, ?>> getFilteredStream(HashMap<?, ?> itemList) {
        return itemList.entrySet().stream().filter(it -> isHashMap(it.getValue()));
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
            case OTHERS_TYPE:
                mItemAdapter.addItem(new OtherItem(advertisementItem));
                break;
        }
    }

}
