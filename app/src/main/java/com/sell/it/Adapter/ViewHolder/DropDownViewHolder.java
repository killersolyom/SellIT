package com.sell.it.Adapter.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;

import com.sell.it.CustomView.DropDownViewItem;
import com.sell.it.Model.ViewHolderItem.DropDownItem;
import com.sell.it.R;

public class DropDownViewHolder extends BaseViewHolder<DropDownItem> {

    private DropDownViewItem mInputView;

    public DropDownViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void findView(View itemView) {
        mInputView = itemView.findViewById(R.id.drop_down_item);
    }

    @Override
    public void bindItem(DropDownItem item) {
        mInputView.bindItem(item);
    }

}