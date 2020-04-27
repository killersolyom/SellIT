package com.sell.it.Adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.sell.it.Model.ViewHolderItem.ImageItem;
import com.sell.it.R;

public class ImageViewHolder extends BaseViewHolder<ImageItem> {

    private ImageView mImage;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(ImageItem imageItem) {
        Glide.with(getContext()).load(imageItem.getImagePath()).into(mImage);
    }

    @Override
    public void unBindItem() {
        Glide.with(getContext()).clear(mImage);
    }

    @Override
    protected void findView(View itemView) {
        mImage = itemView.findViewById(R.id.image_component);
    }

}