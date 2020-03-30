package com.sell.it.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public final String TAG = this.getClass().getCanonicalName();
    protected View mFragmentView;

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

    protected void initComponents() {
    }

    protected void initListeners() {
    }

    protected void loadImages() {
    }

    protected void clearImages() {
    }

    protected void handleRotationEvent(int orientation) {
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mFragmentView != null) {
            clearImages();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        handleRotationEvent(this.getResources().getConfiguration().orientation);
        if (mFragmentView != null) {
            loadImages();
        }
    }

}
