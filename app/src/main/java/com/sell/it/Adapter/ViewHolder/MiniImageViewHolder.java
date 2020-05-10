package com.sell.it.Adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.sell.it.Model.ViewHolderItem.MiniImageItem;
import com.sell.it.R;

public class MiniImageViewHolder extends BaseViewHolder<MiniImageItem> {

    private ImageView mImage;

    public MiniImageViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(MiniImageItem imageItem) {
        Glide.with(getContext()).load(imageItem.getImagePath()).fitCenter().into(mImage);
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