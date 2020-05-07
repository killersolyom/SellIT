package com.sell.it.Fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.ViewHolderItem.ValueListenerItem;
import com.sell.it.R;

public class AddAdvertisementFragment extends BaseFragment {

    private RecyclerView mDataInputView;
    private ItemAdapter mItemAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_advertisement;
    }

    @Override
    protected void findView(View view) {
        mDataInputView = view.findViewById(R.id.upload_recycler_view);
    }

    @Override
    protected void initComponents() {
        mItemAdapter = new ItemAdapter();
        mDataInputView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataInputView.setAdapter(mItemAdapter);

        for (int i = 0; i < 5; i++) {
            mItemAdapter.addItem(new ValueListenerItem(new ValueListener() {
                @Override
                public void writeValue(String value) {

                }
            }, "Valami cím...", ValueListenerItem.InputTypes.STRING));
        }


    }
}
