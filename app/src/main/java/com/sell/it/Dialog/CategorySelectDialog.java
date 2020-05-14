package com.sell.it.Dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Pair;
import android.view.View;
import android.view.Window;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.CustomPairItem;
import com.sell.it.Model.ViewHolderItem.TextItem;
import com.sell.it.R;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.DatabaseManager;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class CategorySelectDialog extends BaseDialogFragment {

    public static final String ALL_CATEGORY = "ALL";
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
        ItemAdapter<TextItem> adapter = new ItemAdapter();
        mCategoryView.setAdapter(adapter);
        addLanguageItems(adapter);
    }

    private void addLanguageItems(ItemAdapter adapter) {

        //All category
        adapter.addItem(createTextItem(R.string.advertisement_all, ALL_CATEGORY, true));

        //Whole electronic category
        adapter.addItem(createTextItem(R.string.advertisement_electronics, Values.CategoryType.ELECTRONIC_TYPE, true));

        //Camera Item
        adapter.addItem(createTextItem((R.string.advertisement_camera_type),
                new Pair<>(Values.ItemType.CAMERA_TYPE, Values.CategoryType.ELECTRONIC_TYPE), false));
        //Laptop Item
        adapter.addItem(createTextItem((R.string.advertisement_laptop_type),
                new Pair<>(Values.ItemType.LAPTOP_TYPE, Values.CategoryType.ELECTRONIC_TYPE), false));

        //Mobile Item
        adapter.addItem(createTextItem((R.string.advertisement_mobile_type),
                new Pair<>(Values.ItemType.MOBILE_PHONE_TYPE, Values.CategoryType.ELECTRONIC_TYPE), false));

        //Whole vehicle category
        adapter.addItem(createTextItem(R.string.advertisement_vehicles, Values.CategoryType.VEHICLE_TYPE, true));
        adapter.addItem(createTextItem((R.string.advertisement_car_type),
                new Pair<>(Values.ItemType.AUTOMOBILE_TYPE, Values.CategoryType.VEHICLE_TYPE), false));

        //Whole others category
        adapter.addItem(createTextItem(R.string.advertisement_other_type,
                new Pair<>(Values.ItemType.OTHERS_TYPE, Values.CategoryType.OTHERS_TYPE), true));
    }

    private TextItem createTextItem(int id, Object item, boolean isCategory) {
        return new TextItem(new CustomPairItem<>(getString(id), item), this::onItemClicked, isCategory);
    }

    private void onItemClicked(Object item) {
        if (item instanceof String) {
            String selectedCategory = item.toString();
            if (selectedCategory.equals(ALL_CATEGORY)) {
                DatabaseManager.getAllAdvertisements();
            } else {
                //fokategoria
            }
            DataManager.saveLastSelectedCategory(selectedCategory, selectedCategory);
        } else if (item instanceof Pair) {
            Pair selectedItem = (Pair) item;
            //alkategoria
            DataManager.saveLastSelectedCategory(selectedItem.first.toString(), selectedItem.second.toString());
        }
        dismissDialog();
    }

}
