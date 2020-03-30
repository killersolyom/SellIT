package com.sell.it.Adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sell.it.R;

public class DefaultViewHolder extends BaseViewHolder {

    private TextView mInformationText;

    public DefaultViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void findView(View itemView) {
        mInformationText = itemView.findViewById(R.id.default_information_text);
    }

    @Override
    public void bindItem(Object o) {

    }

    @Override
    protected void onItemClicked(Object o) {

    }
}
