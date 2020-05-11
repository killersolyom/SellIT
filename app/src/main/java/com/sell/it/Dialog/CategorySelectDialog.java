package com.sell.it.Dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.CustomPairItem;
import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.ViewHolderItem.TextItem;
import com.sell.it.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class CategorySelectDialog extends BaseDialogFragment {

    private RecyclerView mCategoryView;

    @Override
    protected int getLayoutView() {
        return R.layout.category_selector_dialog_layout;
    }

    @Override
    protected void loadDialogSettings(Window dialogWindow) {
        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogWindow.setLayout(MATCH_PARENT, MATCH_PARENT);
    }

    @Override
    protected void initView(View view) {
        mCategoryView = view.findViewById(R.id.category_recycler);
    }

    @Override
    protected void initComponents(Context context) {
        mCategoryView.setLayoutManager(new LinearLayoutManager(context));
        ItemAdapter adapter = new ItemAdapter();
        mCategoryView.setAdapter(adapter);
        addLanguageItems(adapter);
    }

    private void addLanguageItems(ItemAdapter adapter) {
        adapter.addItem(createTextItem((R.string.advertisement_other_type), Values.ItemType.OTHERS_TYPE));
        adapter.addItem(createTextItem((R.string.advertisement_camera_type), Values.ItemType.CAMERA_TYPE));
        adapter.addItem(createTextItem((R.string.advertisement_car_type), Values.ItemType.AUTOMOBILE_TYPE));
        adapter.addItem(createTextItem((R.string.advertisement_laptop_type), Values.ItemType.LAPTOP_TYPE));
        adapter.addItem(createTextItem((R.string.advertisement_mobile_type), Values.ItemType.MOBILE_PHONE_TYPE));
    }

    private TextItem createTextItem(int id, String value) {
        return new TextItem(new CustomPairItem<>(getString(id), value), this::onItemClicked);
    }

    private void onItemClicked(String value) {
        dismissDialog();
    }

}
