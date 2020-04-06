package com.sell.it.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sell.it.Dialog.LanguageSelectDialog;
import com.sell.it.R;

public class SettingsFragment extends BaseFragment {

    private Button languageSelectorButton;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    protected void findView(View view) {
        languageSelectorButton = view.findViewById(R.id.language_selector_button);
    }

    @Override
    protected void initComponents() {
        languageSelectorButton.setOnClickListener(v -> new LanguageSelectDialog().showDialog(mFragmentView.getContext()));
    }

}
