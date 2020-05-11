package com.sell.it.Adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sell.it.Model.Event;
import com.sell.it.Model.ViewHolderItem.TextItem;
import com.sell.it.R;
import com.sell.it.Utility.EventDispatcher;

public class TextItemViewHolder extends BaseViewHolder<TextItem> {

    private TextView mItemTextView;

    public TextItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(TextItem item) {
        mItemTextView.setText(item.getPairItem().first.toString());
        mItemTextView.setOnClickListener(v -> onItemClicked(item));
    }

    @Override
    protected void onItemClicked(TextItem item) {
        item.getListener().onClick(item.getPairItem().second.toString());
    }

    @Override
    protected void findView(View itemView) {
        mItemTextView = itemView.findViewById(R.id.text_view);
    }

    private void sendRestartAppRequest() {
        EventDispatcher.offerEvent(new Event(Event.TYPE_CONTROL, Event.ACTION_LANGUAGE_CHANGE));
    }

}