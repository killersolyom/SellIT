package com.sell.it.Adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sell.it.Model.ViewHolderItem.TextItem;
import com.sell.it.R;

public class TextItemViewHolder extends BaseViewHolder<TextItem> {

    private TextView mItemTextView;

    public TextItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(TextItem item) {
        setItemText(item);
        mItemTextView.setOnClickListener(v -> onItemClicked(item));
    }

    @Override
    protected void onItemClicked(TextItem item) {
        item.getListener().onClick(item.getPairItem().second);
    }

    @Override
    protected void findView(View itemView) {
        mItemTextView = itemView.findViewById(R.id.text_view);
    }

    private void setItemText(TextItem item) {
        boolean isBigCategory = item.isBigCategory();

        mItemTextView.setText(item.getPairItem().first.toString());

        float size = getContext().getResources().getDimensionPixelOffset(isBigCategory ?
                R.dimen.text_item_large_size : R.dimen.text_item_normal_size);
        mItemTextView.setTextSize(size);

        mItemTextView.setBackground(getContext().getDrawable(isBigCategory ?
                R.drawable.text_item_large_foreground : R.drawable.text_item_normal_foreground));

        if (isBigCategory) {
            mItemTextView.setScaleX(1.15f);
            mItemTextView.setScaleY(1.15f);
        }

    }

}