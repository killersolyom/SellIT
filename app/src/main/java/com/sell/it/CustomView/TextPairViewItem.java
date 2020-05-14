package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.sell.it.Model.ViewHolderItem.TextInfoPairItem;
import com.sell.it.R;

public class TextPairViewItem extends BaseCustomView<TextInfoPairItem> {

    private TextView mItemTitle;
    private TextView mItemData;

    public TextPairViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateView(Context context) {
        itemView = LayoutInflater.from(context).inflate(R.layout.text_pair_component_layout, this, true);
    }

    @Override
    protected void initView() {
        mItemTitle = findViewById(R.id.item_id);
        mItemData = findViewById(R.id.item_property);
    }

    @Override
    public void bindItem(TextInfoPairItem item) {
        mItemTitle.setText(getContext().getText(item.getTextPairItem().first));
        mItemData.setText(item.getTextPairItem().second);
    }

}