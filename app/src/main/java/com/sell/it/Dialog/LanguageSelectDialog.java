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

public class LanguageSelectDialog {

    private static Dialog mDialog;

    public static void showDialog(Context context) {
        initDialog(context);
        mDialog.show();
    }

    public static void dismissDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    private static void initDialog(Context context) {
        mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCancelable(true);
        mDialog.setContentView(R.layout.language_selector_dialog_layout);
        RecyclerView languageRecyclerView = mDialog.findViewById(R.id.language_recycler);
        languageRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        languageRecyclerView.setAdapter(getLanguageAdapter(context));
    }

    private static ItemAdapter getLanguageAdapter(Context context) {
        ItemAdapter adapter = new ItemAdapter();
        adapter.addItem(
                new LanguageItem(Values.Language.LANGUAGE_KEY_ENGLISH,
                        context.getString(R.string.english_language),
                        R.drawable.eng_image));

        adapter.addItem(
                new LanguageItem(Values.Language.LANGUAGE_KEY_HUNGARY,
                        context.getString(R.string.hungarian_language),
                        R.drawable.hun_image));
        return adapter;
    }

}
