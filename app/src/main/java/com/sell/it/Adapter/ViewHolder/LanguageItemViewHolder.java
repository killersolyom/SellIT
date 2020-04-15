package com.sell.it.Adapter.ViewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.sell.it.Dialog.ConfirmDialog;
import com.sell.it.Dialog.LanguageSelectDialog;
import com.sell.it.Model.ViewHolderItem.LanguageItem;
import com.sell.it.R;
import com.sell.it.Utility.DataManager;

import static com.sell.it.Activity.MainActivity.INTENT_FILTER_KEY;
import static com.sell.it.Activity.MainActivity.LANGUAGE_CHANGED_KEY;

public class LanguageItemViewHolder extends BaseViewHolder<LanguageItem> {

    private ImageView mLanguageImage;
    private TextView mLanguageName;
    private View mItemLayout;

    public LanguageItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(LanguageItem languageItem) {
        mLanguageName.setText(languageItem.getLanguageName());
        Glide.with(context).load(languageItem.getLanguageImage()).into(mLanguageImage);
        mItemLayout.setOnClickListener(v -> onItemClicked(languageItem));
    }

    @Override
    protected void onItemClicked(LanguageItem languageItem) {
        DataManager.saveLanguage(languageItem.getLanguageKey());
        LanguageSelectDialog.dismissDialog();
        ConfirmDialog.showDialog(
                context,
                context.getResources().getString(R.string.restart_needed),
                this::sendRestartAppRequest,
                ConfirmDialog::dismissDialog);
    }

    @Override
    protected void findView(View itemView) {
        mItemLayout = itemView.findViewById(R.id.language_item_layout);
        mLanguageImage = itemView.findViewById(R.id.language_item_image);
        mLanguageName = itemView.findViewById(R.id.language_item_name);
    }

    @Override
    public void unBindItem() {
        mLanguageName.setText(null);
        Glide.with(context).clear(mLanguageImage);
    }

    private void sendRestartAppRequest() {
        context.sendBroadcast(new Intent(INTENT_FILTER_KEY).putExtra(LANGUAGE_CHANGED_KEY, true));
    }

}