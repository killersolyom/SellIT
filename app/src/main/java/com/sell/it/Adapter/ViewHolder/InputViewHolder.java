package com.sell.it.Adapter.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;

import com.sell.it.CustomView.InputViewItem;
import com.sell.it.Model.ViewHolderItem.ValueListenerItem;
import com.sell.it.R;

public class InputViewHolder extends BaseViewHolder<ValueListenerItem> {

    private InputViewItem mInputViewItem;

    public InputViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(ValueListenerItem valueListenerItem) {
        mInputViewItem.bindItem(valueListenerItem);
    }

    @Override
    public void unBindItem() {
    }

    @Override
    protected void findView(View itemView) {
        mInputViewItem = itemView.findViewById(R.id.input_view);
    }

}