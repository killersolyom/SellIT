package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Communication.EventListener;
import com.sell.it.Model.CustomUri;
import com.sell.it.Model.Event;
import com.sell.it.Model.ViewHolderItem.BaseValueInputItem;
import com.sell.it.Model.ViewHolderItem.MiniImageItem;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;
import com.sell.it.Utility.EventDispatcher;

import java.util.ArrayList;

import static com.sell.it.Model.Constant.Values.SELECT_PICTURE;
import static com.sell.it.Model.Event.ACTION_ADD_IMAGE;
import static com.sell.it.Model.Event.TYPE_IMAGE_PICKER;

public class ImageChooserViewItem extends BaseInputViewItem implements EventListener {

    private ImageView mAddImage;
    private RecyclerView mImageRecyclerView;
    private ItemAdapter<MiniImageItem> mItemAdapter;
    private ArrayList<CustomUri> mItems;

    public ImageChooserViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateView(Context context) {
        itemView = LayoutInflater.from(context).inflate(R.layout.image_chooser_component_layout, this, true);
    }

    @Override
    protected void initView() {
        mAddImage = findViewById(R.id.add_image_view);
        mImageRecyclerView = findViewById(R.id.image_chooser);
    }

    @Override
    protected void initializeComponents() {
        mItems = new ArrayList<>();
        mItemAdapter = new ItemAdapter();
        mImageRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        mImageRecyclerView.setAdapter(mItemAdapter);
        mAddImage.setOnClickListener(v -> EventDispatcher.offerEvent(new Event(Event.TYPE_CONTROL, Event.ACTION_PICK_IMAGE)));
    }

    @Override
    public void writeValue() {
        mValueListener.writeValue(mItems);
    }

    @Override
    public void bindItem(BaseValueInputItem listener) {
        super.bindItem(listener);
        EventDispatcher.subscribe(this);
        mItems = mValueListener.getItemList();
        addItemsToAdapter();
    }

    private void addItemsToAdapter() {
        mItemAdapter.clearItems();
        for (CustomUri it : mItems) {
            mItemAdapter.addItem(new MiniImageItem(it.getUri().toString()));
        }
    }

    @Override
    public void unbind() {
        EventDispatcher.unSubscribe(this);
    }

    @Override
    public boolean onEvent(Event event) {
        if (event.getEventType() == TYPE_IMAGE_PICKER && event.getAction() == ACTION_ADD_IMAGE) {
            if (BundleUtil.canCast(event.getExtras(), SELECT_PICTURE, CustomUri.class)) {
                CustomUri imageUri = BundleUtil.castItem(event.getExtras(), SELECT_PICTURE, CustomUri.class);
                mItemAdapter.addItem(new MiniImageItem(imageUri.getUri().toString()));
                mItems.add(imageUri);
                return true;
            }
        }
        return false;
    }

}