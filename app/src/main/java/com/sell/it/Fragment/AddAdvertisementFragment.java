package com.sell.it.Fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.CustomPairItem;
import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Communication.InputCallbackInterface;
import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.ClassPackaging;
import com.sell.it.Model.ViewHolderItem.Advertisements.CameraItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.CarItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.LaptopItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.MobilePhoneItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.OtherItem;
import com.sell.it.Model.ViewHolderItem.BooleanValueListenerItem;
import com.sell.it.Model.ViewHolderItem.ButtonInputItem;
import com.sell.it.Model.ViewHolderItem.DropDownItem;
import com.sell.it.Model.ViewHolderItem.ImageChooserInputItem;
import com.sell.it.Model.ViewHolderItem.NumberInputItem;
import com.sell.it.Model.ViewHolderItem.TextInputItem;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.DatabaseManager;
import com.sell.it.Utility.DisplayUtils;
import com.sell.it.Utility.FragmentNavigation;

import java.util.ArrayList;
import java.util.HashMap;

import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;
import static com.sell.it.Model.ViewHolderItem.Advertisements.BaseAdvertisementItem.MANUFACTURER_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.BaseComputeUnitItem.CPU_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.BaseComputeUnitItem.MEMORY_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.BaseComputeUnitItem.STORAGE_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.BaseElectronicUtilitiesItem.BATTERY_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.BaseElectronicUtilitiesItem.SCREEN_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.CameraItem.MEGA_PIXEL_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.CarItem.COLOR_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.CarItem.DOOR_NUMBER_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.CarItem.ENGINE_SIZE_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.CarItem.ENGINE_TYPE_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.CarItem.HORSE_POWER_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.CarItem.PRODUCTION_YEAR_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.CarItem.TIRE_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.DefaultAdvertisementItem.DESCRIPTION_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.DefaultAdvertisementItem.OWNER_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.DefaultAdvertisementItem.PRICE_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.DefaultAdvertisementItem.TITLE_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.LaptopItem.DVD_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.LaptopItem.USB_PORT_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.MobilePhoneItem.JACK_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.MobilePhoneItem.MODEL_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.MobilePhoneItem.PRIMARY_CAMERA_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.MobilePhoneItem.SECONDARY_CAMERA_KEY;
import static com.sell.it.Model.ViewHolderItem.Advertisements.MobilePhoneItem.USB_KEY;

public class AddAdvertisementFragment extends BaseFragment {

    private final static String ITEM_DATA = "ITEM_DATA";
    private final static String IMAGE_LIST = "IMAGE_LIST";
    private final static String SELECTED_TYPE = "SELECTED_TYPE";

    private ArrayList<CustomPairItem<String, Class<?>>> mSelectableItems;
    private RecyclerView mDataInputView;
    private ItemAdapter mItemAdapter;
    private ArrayList<InputCallbackInterface> mItemCallbackList;
    private ArrayList<String> mImageList = new ArrayList<>();
    private HashMap<String, Object> mItemData = new HashMap<>();
    private Class<?> mItemType = null;

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
        mDataInputView.setLayoutManager(new GridLayoutManager(getContext(), getSpanCount()));
        mDataInputView.setAdapter(mItemAdapter);
        iniSelectableItems();
    }

    private void iniSelectableItems() {
        mSelectableItems = new ArrayList<>();
        mSelectableItems.add(new CustomPairItem<>(getString(R.string.advertisement_choose_item), String.class));
        mSelectableItems.add(new CustomPairItem<>(getString(R.string.advertisement_other_type), OtherItem.class));
        mSelectableItems.add(new CustomPairItem<>(getString(R.string.advertisement_mobile_type), MobilePhoneItem.class));
        mSelectableItems.add(new CustomPairItem<>(getString(R.string.advertisement_car_type), CarItem.class));
        mSelectableItems.add(new CustomPairItem<>(getString(R.string.advertisement_camera_type), CameraItem.class));
        mSelectableItems.add(new CustomPairItem<>(getString(R.string.advertisement_laptop_type), LaptopItem.class));
    }

    private void onSaveItem() {
        boolean canSaveItem = mItemType != null;

        for (InputCallbackInterface it : mItemCallbackList) {
            boolean isItemReady = it.isReady();
            it.showStatus(!isItemReady);
            if (!isItemReady) {
                canSaveItem = false;
            } else {
                it.writeValue();
            }
        }

        if (canSaveItem) {
            uploadItem();
            FragmentNavigation.onBackPressed();
        }

    }

    private void uploadItem() {
        if (mItemType == OtherItem.class) {
            DatabaseManager.uploadAdvertisement(new OtherItem(mItemData), mImageList);
        } else if (mItemType == MobilePhoneItem.class) {
            DatabaseManager.uploadAdvertisement(new MobilePhoneItem(mItemData), mImageList);
        } else if (mItemType == CarItem.class) {
            DatabaseManager.uploadAdvertisement(new CarItem(mItemData), mImageList);
        } else if (mItemType == CameraItem.class) {
            DatabaseManager.uploadAdvertisement(new CameraItem(mItemData), mImageList);
        } else if (mItemType == LaptopItem.class) {
            DatabaseManager.uploadAdvertisement(new LaptopItem(mItemData), mImageList);
        }
    }

    private void addDataInputFields(Class<?> type) {
        if (type == OtherItem.class) {
            initFields();
            addDefaultFields();
            addImageChooserField();
            addButtonField();
        } else if (type == MobilePhoneItem.class) {
            initFields();
            addDefaultFields();
            addBaseAdvertisementFields();
            addElectronicFields();
            addComputeUnitsFields();
            addMobilePhoneFields();
            addImageChooserField();
            addButtonField();
        } else if (type == CarItem.class) {
            initFields();
            addDefaultFields();
            addBaseAdvertisementFields();
            addCarFields();
            addImageChooserField();
            addButtonField();
        } else if (type == CameraItem.class) {
            initFields();
            addDefaultFields();
            addBaseAdvertisementFields();
            addElectronicFields();
            addCameraFields();
            addImageChooserField();
            addButtonField();
        } else if (type == LaptopItem.class) {
            initFields();
            addDefaultFields();
            addBaseAdvertisementFields();
            addElectronicFields();
            addComputeUnitsFields();
            addLaptopFields();
            addImageChooserField();
            addButtonField();
        }
    }

    private void initFields() {
        mItemData.put(OWNER_KEY, DataManager.getFirstName() + " " + DataManager.getLastName());
    }

    private void addButtonField() {
        addButtonField(getString(R.string.save_item), this::onSaveItem);
    }

    private void addBaseAdvertisementFields() {
        addTextValueSetter(MANUFACTURER_KEY, mContext.getString(R.string.advertisement_manufacturer), false);
    }

    private void addCameraFields() {
        addNumberValueSetter(MEGA_PIXEL_KEY, mContext.getString(R.string.advertisement_megapixel), false);
    }

    private void addElectronicFields() {
        addNumberValueSetter(BATTERY_KEY, mContext.getString(R.string.advertisement_battery_size), false);
        addNumberValueSetter(SCREEN_KEY, mContext.getString(R.string.advertisement_screen_size), false);
    }

    private void addLaptopFields() {
        addNumberValueSetter(USB_PORT_KEY, mContext.getString(R.string.advertisement_usb_number), false);
        addBooleanValueSetter(DVD_KEY, mContext.getString(R.string.advertisement_dvd_rom));
    }

    private void addComputeUnitsFields() {
        addTextValueSetter(CPU_KEY, mContext.getString(R.string.advertisement_cpu), false);
        addNumberValueSetter(MEMORY_KEY, mContext.getString(R.string.advertisement_memory), false);
        addNumberValueSetter(STORAGE_KEY, mContext.getString(R.string.advertisement_storage), false);
    }

    private void addDefaultFields() {
        addNumberValueSetter(PRICE_KEY, mContext.getString(R.string.advertisement_price), true);
        addTextValueSetter(TITLE_KEY, mContext.getString(R.string.advertisement_title), true);
        addTextValueSetter(DESCRIPTION_KEY, mContext.getString(R.string.advertisement_description), true);
        addTextValueSetter(OWNER_KEY, mContext.getString(R.string.advertisement_owner), true);
    }

    private void addMobilePhoneFields() {
        addTextValueSetter(USB_KEY, mContext.getString(R.string.advertisement_usb_type), false);
        addTextValueSetter(MODEL_KEY, mContext.getString(R.string.advertisement_model), false);
        addNumberValueSetter(PRIMARY_CAMERA_KEY, mContext.getString(R.string.advertisement_primary_camera), false);
        addNumberValueSetter(SECONDARY_CAMERA_KEY, mContext.getString(R.string.advertisement_secondary_camera), false);
        addBooleanValueSetter(JACK_KEY, mContext.getString(R.string.advertisement_jack));
    }

    private void addCarFields() {
        addTextValueSetter(COLOR_KEY, mContext.getString(R.string.advertisement_color), false);
        addTextValueSetter(ENGINE_TYPE_KEY, mContext.getString(R.string.advertisement_engine_type), false);
        addTextValueSetter(TIRE_KEY, mContext.getString(R.string.advertisement_tire_size), false);
        addNumberValueSetter(ENGINE_SIZE_KEY, mContext.getString(R.string.advertisement_engine_size), false);
        addNumberValueSetter(HORSE_POWER_KEY, mContext.getString(R.string.advertisement_horse_power), false);
        addNumberValueSetter(PRODUCTION_YEAR_KEY, mContext.getString(R.string.advertisement_production_year), false);
        addNumberValueSetter(DOOR_NUMBER_KEY, mContext.getString(R.string.advertisement_door_number), false);
    }

    private void addTextValueSetter(String key, String title, boolean isNecessary) {
        mItemAdapter.addItem(new TextInputItem(new ValueListener() {
            @Override
            public void writeValue(String value) {
                mItemData.put(key, value);
            }

            @Override
            public String getStringValue() {
                return mItemData.get(key) == null ? "" : (String) mItemData.get(key);
            }

            @Override
            public void registerCallback(InputCallbackInterface callback) {
                mItemCallbackList.add(callback);
            }
        }, title, isNecessary));
    }

    private void addNumberValueSetter(String key, String title, boolean isNecessary) {
        mItemAdapter.addItem(new NumberInputItem(new ValueListener() {
            @Override
            public void writeValue(Float value) {
                mItemData.put(key, value);
            }

            @Override
            public String getStringValue() {
                return mItemData.get(key) == null ? "" : mItemData.get(key) instanceof Float ?
                        String.valueOf(mItemData.get(key)).replace(".0", "") : "";
            }

            @Override
            public void registerCallback(InputCallbackInterface callback) {
                mItemCallbackList.add(callback);
            }
        }, title, isNecessary));
    }

    private void addBooleanValueSetter(String key, String title) {
        mItemAdapter.addItem(new BooleanValueListenerItem(new ValueListener() {
            @Override
            public void writeValue(Boolean value) {
                mItemData.put(key, value);
            }

            @Override
            public boolean getBooleanValue() {
                Object item = mItemData.get(key);
                return item instanceof Boolean && ((Boolean) item);
            }

            @Override
            public void registerCallback(InputCallbackInterface callback) {
                mItemCallbackList.add(callback);
            }
        }, title));
    }

    private void addButtonField(String title, Runnable runnable) {
        mItemAdapter.addItem(new ButtonInputItem(title, runnable));
    }

    private void addImageChooserField() {
        mItemAdapter.addItem(new ImageChooserInputItem(new ValueListener() {
            @Override
            public void writeValue(ArrayList<String> valueList) {
                mImageList = valueList;
            }

            @Override
            public ArrayList<String> getItemList() {
                return mImageList;
            }

            @Override
            public void registerCallback(InputCallbackInterface callback) {
                mItemCallbackList.add(callback);
            }
        }));
    }

    private void addCategorySelectorField() {
        mItemAdapter.addItem(new DropDownItem(new ValueListener() {
            @Override
            public void writeValue(Class<?> itemClass) {
                if (String.class != itemClass) {
                    mItemData.clear();
                    mImageList.clear();
                    mItemType = itemClass;
                    mItemAdapter.clearItems();
                    addDataInputFields(itemClass);
                }
            }
        }, getString(R.string.advertisement_select_category), mSelectableItems));
    }

    private int getSpanCount() {
        return DisplayUtils.getOrientation() == PORTRAIT ? 1 : 2;
    }

    @Override
    protected void restoreItems(Bundle bundle) {
        if (BundleUtil.canCast(bundle, SELECTED_TYPE, ClassPackaging.class)) {
            mItemType = BundleUtil.castItem(bundle, SELECTED_TYPE, ClassPackaging.class).getItemClass();
            mItemAdapter.clearItems();

            if (BundleUtil.canCast(bundle, IMAGE_LIST, ArrayList.class)) {
                mImageList = BundleUtil.castItem(bundle, IMAGE_LIST, ArrayList.class);
            }

            if (BundleUtil.canCast(bundle, ITEM_DATA, HashMap.class)) {
                mItemData = BundleUtil.castItem(bundle, ITEM_DATA, HashMap.class);
            }

            mItemAdapter.clearItems();
            addDataInputFields(mItemType);
        } else {
            addCategorySelectorField();
        }
    }

    @Override
    protected Bundle saveItems() {
        Bundle bundle = new Bundle();
        if (mItemType != null) {
            mItemCallbackList.forEach(InputCallbackInterface::writeValue);
            bundle.putSerializable(IMAGE_LIST, mImageList);
            bundle.putSerializable(ITEM_DATA, mItemData);
            bundle.putSerializable(SELECTED_TYPE, new ClassPackaging(mItemType));
        }
        return bundle;
    }

}
