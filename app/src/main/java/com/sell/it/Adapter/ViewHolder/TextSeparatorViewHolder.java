package com.sell.it.Adapter.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;

import com.sell.it.Model.ViewHolderItem.TextSeparatorItem;

public class TextSeparatorViewHolder extends BaseViewHolder<TextSeparatorItem> {

    public TextSeparatorViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(TextSeparatorItem textSeparatorItem) {
        itemView.getLayoutParams().height = textSeparatorItem.getSeparatorHeight();
    }
}