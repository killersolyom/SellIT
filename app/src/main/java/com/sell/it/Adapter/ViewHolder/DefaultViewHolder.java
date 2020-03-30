package com.sell.it.Adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sell.it.Model.BaseDefaultItem;
import com.sell.it.R;

public class DefaultViewHolder extends BaseViewHolder<BaseDefaultItem> {

    private TextView mMessageText;

    public DefaultViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(BaseDefaultItem baseDefaultItem) {
        mMessageText.setText(baseDefaultItem.getMessage());
    }

    @Override
    protected void onItemClicked(BaseDefaultItem baseDefaultItem) {

    }

    @Override
    protected void findView(View itemView) {
        mMessageText = itemView.findViewById(R.id.default_information_text);
    }

}
