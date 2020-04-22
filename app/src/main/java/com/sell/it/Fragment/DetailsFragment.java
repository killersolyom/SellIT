package com.sell.it.Fragment;

import android.os.Bundle;
import android.view.View;

import com.sell.it.Model.ViewHolderItem.BaseAdvertisementItem;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;

public class DetailsFragment extends BaseFragment {

    private final static String ADVERTISEMENT_ITEM_KEY = "Advertisement";
    private BaseAdvertisementItem mAdvertisement;

    public static DetailsFragment newInstance(BaseAdvertisementItem item) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ADVERTISEMENT_ITEM_KEY, item);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_details;
    }

    @Override
    protected void findView(View view) {

    }

    @Override
    protected void getArgumentsFromBundle(Bundle bundle) {
        if (BundleUtil.hasValueAt(bundle, ADVERTISEMENT_ITEM_KEY)) {
            mAdvertisement = BundleUtil.castItem(bundle, ADVERTISEMENT_ITEM_KEY, BaseAdvertisementItem.class);
        }
    }
}
