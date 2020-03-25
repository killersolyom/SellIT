package com.sell.it.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    private View mFragmentView;

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mFragmentView == null) {
            mFragmentView = initView(inflater, container);
            findView(mFragmentView);
            initComponents();
            initListeners();
        }
        return mFragmentView;
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    protected abstract void findView(View view);

    protected abstract void initComponents();

    protected void initListeners() {
    }

}
