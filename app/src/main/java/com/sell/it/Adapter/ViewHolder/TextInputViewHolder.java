package com.sell.it.Adapter.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;

import com.sell.it.CustomView.TextInputViewItem;
import com.sell.it.Model.ViewHolderItem.BaseValueInputItem;
import com.sell.it.R;

public class TextInputViewHolder extends BaseViewHolder<BaseValueInputItem> {

    private TextInputViewItem mInputView;

    public TextInputViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(BaseValueInputItem baseValueInputItem) {
        mInputView.bindItem(baseValueInputItem);
    }

    @Override
    public void unBindItem() {
        mInputView.unbind();
    }

    @Override
    protected void findView(View itemView) {
        mInputView = itemView.findViewById(R.id.input_view);
    }

}