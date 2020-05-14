package com.sell.it.Dialog;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.ViewHolderItem.LanguageItem;
import com.sell.it.R;

public class LanguageSelectDialog extends BaseDialogFragment {

    private RecyclerView mLanguageRecyclerView;

    @Override
    protected int getLayoutView() {
        return R.layout.language_selector_dialog_layout;
    }

    @Override
    protected void initView(View view) {
        mLanguageRecyclerView = view.findViewById(R.id.language_recycler);
    }

    @Override
    protected void initComponents(Context context) {
        mLanguageRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        ItemAdapter<LanguageItem> adapter = new ItemAdapter();
        mLanguageRecyclerView.setAdapter(adapter);
        addLanguageItems(adapter, context);
    }

    private void addLanguageItems(ItemAdapter adapter, Context context) {
        adapter.addItem(
                new LanguageItem(Values.Language.LANGUAGE_KEY_ENGLISH,
                        context.getString(R.string.english_language),
                        R.drawable.eng_image));

        adapter.addItem(
                new LanguageItem(Values.Language.LANGUAGE_KEY_HUNGARY,
                        context.getString(R.string.hungarian_language),
                        R.drawable.hun_image));
    }

}
