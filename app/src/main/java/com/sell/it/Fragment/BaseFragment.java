package com.sell.it.Fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.sell.it.Model.Event;
import com.sell.it.R;

import static com.sell.it.Model.Constant.Values.Orientation.LANDSCAPE;
import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;

public abstract class BaseFragment extends Fragment {

    public final String TAG = this.getClass().getCanonicalName();
    protected Context mContext;

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = initView(inflater, container);
        mContext = fragmentView.getContext();
        findView(fragmentView);
        initComponents();
        initListeners();
        fragmentView.setBackgroundColor(ContextCompat.getColor(container.getContext(), R.color.fragmentBackground));
        return fragmentView;
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

    public void onEvent(Event event) {
    }

    protected String getOrientation() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE ? LANDSCAPE : PORTRAIT;
    }

    @Override
    public void onPause() {
        super.onPause();
        clearImages();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadImages();
    }

}
