package com.sell.it.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.fragment.app.DialogFragment;

import com.sell.it.Utility.FragmentNavigation;

public abstract class BaseDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutView(), container, false);
        initView(view);
        initComponents(view.getContext());
        if (getDialog() != null && getDialog().getWindow() != null) {
            loadDialogSettings(getDialog().getWindow());
        }
        return view;
    }

    protected abstract int getLayoutView();

    protected abstract void initView(View view);

    protected void initComponents(Context context) {
    }

    protected void loadDialogSettings(Window dialogWindow) {
    }

    protected void dismissDialog() {
        FragmentNavigation.dismissDialogByTAG(getClass().getCanonicalName());
    }

}
