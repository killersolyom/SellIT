package com.sell.it.Adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sell.it.Model.ViewHolderItem.Advertisements.AdvertisementInfoItem;
import com.sell.it.R;

public class AdvertisementInfoViewHolder extends BaseViewHolder<AdvertisementInfoItem> {

    private TextView mInfoTextView;

    public AdvertisementInfoViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(AdvertisementInfoItem infoItem) {
        mInfoTextView.setTextSize(infoItem.getTextSize());
        mInfoTextView.setText(infoItem.getInfo());
    }

    @Override
    protected void findView(View itemView) {
        mInfoTextView = itemView.findViewById(R.id.info_text_view);
    }

    @Override
    public void unBindItem() {
        mInfoTextView.setText(null);
    }
}