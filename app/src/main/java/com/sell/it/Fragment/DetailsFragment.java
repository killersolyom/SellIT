package com.sell.it.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Model.ViewHolderItem.Advertisements.BaseAdvertisementItem;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;
import com.sell.it.Utility.DisplayUtils;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;
import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;
import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;

public class DetailsFragment extends BaseVerificationFragment {

    private final static String ADVERTISEMENT_ITEM_KEY = "Advertisement";
    private RecyclerView mImageRecyclerView;
    private RecyclerView mInfoRecyclerView;
    private TextView mTitleView;
    private ItemAdapter mImageAdapter;
    private ItemAdapter mInfoAdapter;

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
        mImageRecyclerView = view.findViewById(R.id.images_recycler_view);
        mInfoRecyclerView = view.findViewById(R.id.info_recycler_view);
        mTitleView = view.findViewById(R.id.advertisement_title);
    }

    @Override
    protected void initComponents() {
        mImageAdapter = new ItemAdapter();
        mInfoAdapter = new ItemAdapter();

        mImageRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                DisplayUtils.getOrientation() == PORTRAIT ? HORIZONTAL : VERTICAL, false));

        mInfoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                DisplayUtils.getOrientation() == PORTRAIT ? HORIZONTAL : VERTICAL, false));

        mImageRecyclerView.setAdapter(mImageAdapter);
        mInfoRecyclerView.setAdapter(mInfoAdapter);
    }

    @Override
    protected void getArgumentsFromBundle(Bundle bundle) {
        if (BundleUtil.hasValueAt(bundle, ADVERTISEMENT_ITEM_KEY)) {
            BaseAdvertisementItem advertisement =
                    BundleUtil.castItem(bundle, ADVERTISEMENT_ITEM_KEY, BaseAdvertisementItem.class);
            mTitleView.setText(advertisement.getTitle());

            mImageAdapter.addItemList(advertisement.getImageList());
            //mInfoAdapter.addItemList();

        }
    }
}
