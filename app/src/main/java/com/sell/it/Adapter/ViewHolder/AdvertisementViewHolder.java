package com.sell.it.Adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sell.it.Model.BaseAdvertisementItem;
import com.sell.it.R;

public class AdvertisementViewHolder extends BaseViewHolder<BaseAdvertisementItem> {

    private TextView mTitleText;

    public AdvertisementViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(BaseAdvertisementItem advertisementItem) {
        mItemLayout.setOnClickListener(v -> onItemClicked(advertisementItem));
        mTitleText.setText(advertisementItem.getTitle());
    }

    @Override
    protected void onItemClicked(BaseAdvertisementItem item) {

    }

    @Override
    protected void findView(View itemView) {
        mItemLayout = itemView.findViewById(R.id.recycler_view_item);
        mTitleText = itemView.findViewById(R.id.test_text);
    }

}