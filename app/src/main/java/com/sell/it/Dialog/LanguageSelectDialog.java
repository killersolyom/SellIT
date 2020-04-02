package com.sell.it.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.ViewHolderItem.LanguageItem;
import com.sell.it.R;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

public class LanguageSelectDialog {

    public LanguageSelectDialog(Context context) {
        showDialog(context);
    }

    private void showDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.language_selector_dialog_layout);
        RecyclerView languageView = dialog.findViewById(R.id.language_recycler);
        languageView.setLayoutManager(new LinearLayoutManager(context, VERTICAL, false));
        ItemAdapter languageAdapter = new ItemAdapter();
        fillLanguageAdapter(languageAdapter, context);
        languageView.setAdapter(languageAdapter);
        dialog.show();
    }

    private void fillLanguageAdapter(ItemAdapter languageAdapter, Context context) {
        languageAdapter.addItem(
                new LanguageItem(Values.Language.LANGUAGE_KEY_ENGLISH,
                        context.getString(R.string.english_language),
                        R.drawable.eng_image));

        languageAdapter.addItem(
                new LanguageItem(Values.Language.LANGUAGE_KEY_HUNGARY,
                        context.getString(R.string.hungarian_language),
                        R.drawable.hun_image));
    }

}
