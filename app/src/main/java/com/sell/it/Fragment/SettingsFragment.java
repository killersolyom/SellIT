package com.sell.it.Fragment;

import android.view.View;
import android.widget.Button;

import com.sell.it.Dialog.LanguageSelectDialog;
import com.sell.it.R;
import com.sell.it.Utility.FragmentNavigation;

public class SettingsFragment extends BaseVerificationFragment {

    private Button languageSelectorButton;
    private Button columnSelectorButton;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void findView(View view) {
        languageSelectorButton = view.findViewById(R.id.language_selector_button);
        columnSelectorButton = view.findViewById(R.id.column_selecting_button);
    }

    @Override
    protected void initComponents() {
        new LanguageSelectDialog();
        languageSelectorButton.setOnClickListener(v -> FragmentNavigation.showLanguageSelectorDialog());
        columnSelectorButton.setOnClickListener(v -> FragmentNavigation.showColumnNumberSelectorDialog());
    }

}
