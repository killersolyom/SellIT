package com.sell.it.Adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.sell.it.Dialog.LanguageSelectDialog;
import com.sell.it.Model.Event;
import com.sell.it.Model.ViewHolderItem.LanguageItem;
import com.sell.it.R;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.EventDispatcher;
import com.sell.it.Utility.FragmentNavigation;

import static com.sell.it.Model.Constant.Values.EventAction.LANGUAGE_CHANGE_ACTION;

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
        Glide.with(getContext()).load(languageItem.getLanguageImage()).into(mLanguageImage);
        mItemLayout.setOnClickListener(v -> onItemClicked(languageItem));
    }

    @Override
    protected void onItemClicked(LanguageItem languageItem) {
        DataManager.saveLanguage(languageItem.getLanguageKey());
        FragmentNavigation.dismissDialogByTAG(LanguageSelectDialog.class.getCanonicalName());
        FragmentNavigation.showConfirmDialog(R.string.restart_needed, this::sendRestartAppRequest);
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
        Glide.with(getContext()).clear(mLanguageImage);
    }

    private void sendRestartAppRequest() {
        EventDispatcher.offerEvent(new Event(Event.CONTROL, LANGUAGE_CHANGE_ACTION));
    }

}