package com.sell.it.Fragment;

import android.view.View;
import android.widget.Button;

import com.sell.it.Dialog.LanguageSelectDialog;
import com.sell.it.R;

public class SettingsFragment extends BaseFragment {

    private Button languageSelectorButton;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void findView(View view) {
        languageSelectorButton = view.findViewById(R.id.language_selector_button);
    }

    @Override
    protected void initComponents() {
        new LanguageSelectDialog();
        languageSelectorButton.setOnClickListener(v -> LanguageSelectDialog.showDialog(mContext));
    }

}
