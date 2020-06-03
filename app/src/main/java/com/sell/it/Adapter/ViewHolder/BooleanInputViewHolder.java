package com.sell.it.Adapter.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;

import com.sell.it.CustomView.BooleanInputViewItem;
import com.sell.it.Model.ViewHolderItem.BaseValueInputItem;
import com.sell.it.R;

public class BooleanInputViewHolder extends BaseViewHolder<BaseValueInputItem> {

    private BooleanInputViewItem mInputViewItem;

    public BooleanInputViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(BaseValueInputItem baseValueInputItem) {
        mInputViewItem.bindItem(baseValueInputItem);
    }

    @Override
    protected void findView(View itemView) {
        mInputViewItem = itemView.findViewById(R.id.boolean_input_view);
    }


}