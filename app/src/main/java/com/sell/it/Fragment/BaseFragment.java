package com.sell.it.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.sell.it.Communication.EventListener;
import com.sell.it.Model.Event;
import com.sell.it.R;
import com.sell.it.Utility.EventDispatcher;

public abstract class BaseFragment extends Fragment implements EventListener {

    public final String TAG = this.getClass().getCanonicalName();
    Context mContext;
    private View mFragmentView;

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(getLayoutId(), container, false);
        }
        mFragmentView.setBackgroundColor(ContextCompat.getColor(container.getContext(), R.color.fragmentBackground));
        mContext = mFragmentView.getContext();
        findView(mFragmentView);
        initComponents();
        initListeners();
        getArgumentsFromBundle(getArguments());

        return mFragmentView;
    }

    protected abstract int getLayoutId();

    protected abstract void findView(View view);

    protected void initComponents() {
    }

    protected void initListeners() {
    }

    protected void getArgumentsFromBundle(Bundle bundle) {
    }

    protected void loadImages() {
    }

    protected void clearImages() {
    }

    protected void saveItems(Bundle bundle) {
    }

    protected void restoreItems(Bundle bundle) {
    }

    @Override
    public boolean onEvent(Event event) {
        return false;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        saveItems(bundle);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
        restoreItems(bundle);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventDispatcher.unSubscribe(this);
        clearImages();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventDispatcher.subscribe(this);
        loadImages();
    }

}
