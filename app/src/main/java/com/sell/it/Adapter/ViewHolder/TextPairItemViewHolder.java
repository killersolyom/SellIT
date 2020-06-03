package com.sell.it.Adapter.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;

import com.sell.it.CustomView.TextPairViewItem;
import com.sell.it.Model.ViewHolderItem.TextInfoPairItem;
import com.sell.it.R;

public class TextPairItemViewHolder extends BaseViewHolder<TextInfoPairItem> {

    private TextPairViewItem mItemTextView;

    public TextPairItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(TextInfoPairItem item) {
        mItemTextView.bindItem(item);
    }


    @Override
    protected void findView(View itemView) {
        mItemTextView = itemView.findViewById(R.id.text_pair_view);
    }

}