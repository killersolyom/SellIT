package com.sell.it.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.sell.it.Communication.EventListener;
import com.sell.it.Model.Event;
import com.sell.it.R;
import com.sell.it.Utility.DataCacheUtil;
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
        mContext = mFragmentView.getContext();
        findView(mFragmentView);
        initListeners();
        mFragmentView.setBackgroundColor(ContextCompat.getColor(mContext, getBackgroundColorId()));
        initComponents();
        getArgumentsFromBundle(getArguments());
        return mFragmentView;
    }

    protected abstract int getLayoutId();

    protected abstract void findView(View view);

    protected void initComponents() {
    }

    protected void initListeners() {
    }

    protected int getBackgroundColorId() {
        return R.color.fragmentBackground;
    }

    protected void getArgumentsFromBundle(Bundle bundle) {
    }

    protected void loadImages() {
    }

    protected void removeCallbacks() {
    }

    protected Bundle saveItems() {
        return null;
    }

    protected void restoreItems(Bundle bundle) {
    }

    protected Bundle getSavedItems() {
        return DataCacheUtil.getItem(TAG);
    }

    @Override
    public boolean onEvent(Event event) {
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        EventDispatcher.unSubscribe(this);
        DataCacheUtil.addItem(TAG, saveItems());
        removeCallbacks();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventDispatcher.subscribe(this);
        EventDispatcher.offerEvent(new Event(Event.TYPE_CONTROL, Event.ACTION_UNLOCK_ORIENTATION));
        restoreItems(getSavedItems());
        loadImages();
        EventDispatcher.sendUnconsumedEvents();
    }

    public boolean compare(BaseFragment fragment) {
        return fragment != null && this.getClass().equals(fragment.getClass());
    }

}
