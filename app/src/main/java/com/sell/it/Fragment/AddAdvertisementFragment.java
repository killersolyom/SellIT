package com.sell.it.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Communication.InputCallbackInterface;
import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.Event;
import com.sell.it.Model.ViewHolderItem.Advertisements.CameraItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.CarItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.DefaultAdvertisementItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.LaptopItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.MobilePhoneItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.OtherItem;
import com.sell.it.Model.ViewHolderItem.BooleanValueListenerItem;
import com.sell.it.Model.ViewHolderItem.ButtonInputItem;
import com.sell.it.Model.ViewHolderItem.ImageChooserInputItem;
import com.sell.it.Model.ViewHolderItem.NumberInputItem;
import com.sell.it.Model.ViewHolderItem.TextInputItem;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;
import com.sell.it.Utility.DisplayUtils;
import com.sell.it.Utility.EventDispatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;
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

    public final static String ITEM_KEY = "ITEM_CLASS_KEY";
    public final static String ITEM_CATEGORY_KEY = "ITEM_CATEGORY_KEY";

    private DefaultAdvertisementItem mUploadItem;
    private RecyclerView mDataInputView;
    private ItemAdapter mItemAdapter;
    private ArrayList<InputCallbackInterface> mItemCallbackList;
    private Map<String, Object> mItemData;
    private Class<?> mItemType;

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
    }

    @Override
    protected void getArgumentsFromBundle(Bundle bundle) {
        if (BundleUtil.hasValueAt(bundle, ITEM_KEY) && mItemAdapter.isEmpty()) {
            mItemType = Objects.requireNonNull(bundle.get(ITEM_KEY)).getClass();
            addDataInputFields(mItemType);
        }
        mItemType = MobilePhoneItem.class;
        addDataInputFields(MobilePhoneItem.class);
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
            createItem();
            for (Pair<Integer, String> it : mUploadItem.getDescriptionList()) {
                Log.d("3ss", getString(it.first) + " " + it.second);
            }
        }

    }

    private void createItem() {
        if (mItemType == OtherItem.class) {
            mUploadItem = new OtherItem(mItemData);
        } else if (mItemType == MobilePhoneItem.class) {
            mUploadItem = new MobilePhoneItem(mItemData);
        } else if (mItemType == CarItem.class) {
            mUploadItem = new CarItem(mItemData);
        } else if (mItemType == CameraItem.class) {
            mUploadItem = new CameraItem(mItemData);
        } else if (mItemType == LaptopItem.class) {
            mUploadItem = new LaptopItem(mItemData);
        }
    }

    private void addDataInputFields(Class<?> type) {
        if (type == OtherItem.class) {
            addDefaultFields();
            addImageChooserField();
            addButtonField();
        } else if (type == MobilePhoneItem.class) {
            addDefaultFields();
            addElectronicFields();
            addComputeUnitsFields();
            addMobilePhoneFields();
            addImageChooserField();
            addButtonField();
        } else if (type == CarItem.class) {
            addDefaultFields();
            addCarFields();
            addImageChooserField();
            addButtonField();
        } else if (type == CameraItem.class) {
            addDefaultFields();
            addElectronicFields();
            addCameraFields();
            addImageChooserField();
            addButtonField();
        } else if (type == LaptopItem.class) {
            addDefaultFields();
            addElectronicFields();
            addComputeUnitsFields();
            addLaptopFields();
            addImageChooserField();
            addButtonField();
        }
    }

    private void addButtonField() {
        addButtonField(getString(R.string.save_item), this::onSaveItem);
    }

    private void addDefaultFields() {
        addNumberValueSetter(PRICE_KEY, mContext.getString(R.string.advertisement_price), true);
        addTextValueSetter(TITLE_KEY, mContext.getString(R.string.advertisement_title), true);
        addTextValueSetter(DESCRIPTION_KEY, mContext.getString(R.string.advertisement_description), true);
    }

    private void addElectronicFields() {
        addNumberValueSetter(BATTERY_KEY, mContext.getString(R.string.advertisement_battery_size), false);
        addNumberValueSetter(SCREEN_KEY, mContext.getString(R.string.advertisement_screen_size), false);
    }

    private void addComputeUnitsFields() {
        addTextValueSetter(CPU_KEY, mContext.getString(R.string.advertisement_cpu), false);
        addNumberValueSetter(MEMORY_KEY, mContext.getString(R.string.advertisement_memory), false);
        addNumberValueSetter(STORAGE_KEY, mContext.getString(R.string.advertisement_storage), false);
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

    private void addCameraFields() {
        addNumberValueSetter(MEGA_PIXEL_KEY, mContext.getString(R.string.advertisement_megapixel), false);
    }

    private void addMobilePhoneFields() {
        addTextValueSetter(USB_KEY, mContext.getString(R.string.advertisement_usb_type), false);
        addTextValueSetter(MODEL_KEY, mContext.getString(R.string.advertisement_model), false);
        addNumberValueSetter(PRIMARY_CAMERA_KEY, mContext.getString(R.string.advertisement_primary_camera), false);
        addNumberValueSetter(SECONDARY_CAMERA_KEY, mContext.getString(R.string.advertisement_secondary_camera), false);
        addBooleanValueSetter(JACK_KEY, mContext.getString(R.string.advertisement_jack));
    }

    private void addLaptopFields() {
        addNumberValueSetter(USB_PORT_KEY, mContext.getString(R.string.advertisement_usb_number), false);
        addBooleanValueSetter(DVD_KEY, mContext.getString(R.string.advertisement_dvd_rom));
    }

    private void addTextValueSetter(String key, String title, boolean isNecessary) {
        mItemAdapter.addItem(new TextInputItem(new ValueListener() {
            @Override
            public void writeValue(String value) {
                mItemData.put(key, value);
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

            }

            @Override
            public void registerCallback(InputCallbackInterface callback) {
                mItemCallbackList.add(callback);
            }
        }));
    }

    private int getSpanCount() {
        return DisplayUtils.getOrientation() == PORTRAIT ? 1 : 2;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventDispatcher.offerEvent(new Event(Event.TYPE_CONTROL, Event.ACTION_LOCK_ORIENTATION));
    }
}
