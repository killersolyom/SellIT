package com.sell.it.Adapter.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;

import com.sell.it.CustomView.ButtonInputViewItem;
import com.sell.it.Model.ViewHolderItem.ButtonInputItem;
import com.sell.it.R;

public class ButtonInputViewHolder extends BaseViewHolder<ButtonInputItem> {

    private ButtonInputViewItem mInputViewItem;

    public ButtonInputViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(ButtonInputItem inputItem) {
        mInputViewItem.bindItem(inputItem);
    }

    @Override
    protected void findView(View itemView) {
        mInputViewItem = itemView.findViewById(R.id.button_input_view);
    }


}