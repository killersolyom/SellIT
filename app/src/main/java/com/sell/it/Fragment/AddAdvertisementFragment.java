package com.sell.it.Fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Communication.InputCallbackInterface;
import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.ViewHolderItem.ValueListenerItem;
import com.sell.it.R;
import com.sell.it.Utility.DisplayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.sell.it.CustomView.InputViewItem.NUMBER_INPUT_TYPE;
import static com.sell.it.CustomView.InputViewItem.TEXT_INPUT_TYPE;
import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;

public class AddAdvertisementFragment extends BaseFragment {

    private RecyclerView mDataInputView;
    private ItemAdapter mItemAdapter;
    private ArrayList<InputCallbackInterface> mItemCallbackList;
    private Map<String, Object> mItemData;

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
        mItemCallbackList = new ArrayList<>();
        mItemAdapter = new ItemAdapter(getSpanCount());
        mItemData = new HashMap<>();
        mDataInputView.setLayoutManager(new GridLayoutManager(getContext(), getSpanCount()));
        mDataInputView.setAdapter(mItemAdapter);

        for (int i = 0; i < 5; i++) {
            addNumberValueSetter("" + i, "Number  " + i, true);
            addTextValueSetter("" + i, "Text  " + i, true);
        }
    }

    private void addTextValueSetter(String key, String title, boolean isNecessary) {
        mItemAdapter.addItem(new ValueListenerItem(new ValueListener() {
            @Override
            public void writeValue(String value) {
                mItemData.put(key, value);
            }

            @Override
            public void registerCallback(InputCallbackInterface callback) {
                mItemCallbackList.add(callback);
            }
        }, title, TEXT_INPUT_TYPE, isNecessary));
    }

    private void addNumberValueSetter(String key, String title, boolean isNecessary) {
        mItemAdapter.addItem(new ValueListenerItem(new ValueListener() {
            @Override
            public void writeValue(Float value) {
                mItemData.put(key, value);
            }

            @Override
            public void registerCallback(InputCallbackInterface callback) {
                mItemCallbackList.add(callback);
            }
        }, title, NUMBER_INPUT_TYPE, isNecessary));
    }

    private int getSpanCount() {
        return DisplayUtils.getOrientation() == PORTRAIT ? 1 : 2;
    }
}
