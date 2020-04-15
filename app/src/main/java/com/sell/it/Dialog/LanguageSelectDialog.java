package com.sell.it.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.sell.it.CustomView.CustomRecyclerView;
import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.ViewHolderItem.LanguageItem;
import com.sell.it.R;

public class LanguageSelectDialog {

    private static Dialog dialog;

    public static void showDialog(Context context) {
        initDialog(context);
        dialog.show();
    }

    public static void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    private static void initDialog(Context context) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.language_selector_dialog_layout);
        CustomRecyclerView languageRecyclerView = dialog.findViewById(R.id.language_recycler);
        languageRecyclerView.initParams(new LinearLayoutManager(context));
        fillLanguageAdapter(languageRecyclerView, context);
    }

    private static void fillLanguageAdapter(CustomRecyclerView recyclerView, Context context) {
        recyclerView.addItem(
                new LanguageItem(Values.Language.LANGUAGE_KEY_ENGLISH,
                        context.getString(R.string.english_language),
                        R.drawable.eng_image));

        recyclerView.addItem(
                new LanguageItem(Values.Language.LANGUAGE_KEY_HUNGARY,
                        context.getString(R.string.hungarian_language),
                        R.drawable.hun_image));
    }

}
