package com.sell.it.Adapter.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;

import com.sell.it.CustomView.ImageChooserViewItem;
import com.sell.it.Model.ViewHolderItem.ImageChooserInputItem;
import com.sell.it.R;

public class ImageChooserViewHolder extends BaseViewHolder<ImageChooserInputItem> {

    private ImageChooserViewItem mInputViewItem;

    public ImageChooserViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(ImageChooserInputItem inputItem) {
        mInputViewItem.bindItem(inputItem);
    }

    @Override
    public void unBindItem() {
        mInputViewItem.unbind();
    }

    @Override
    protected void findView(View itemView) {
        mInputViewItem = itemView.findViewById(R.id.chooser_input_view);
    }


}