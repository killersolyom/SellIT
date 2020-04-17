package com.sell.it.Fragment;

import android.content.Context;
import android.content.res.Configuration;
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

import static com.sell.it.Model.Constant.Values.Orientation.LANDSCAPE;
import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;

public abstract class BaseFragment extends Fragment implements EventListener {

    public final String TAG = this.getClass().getCanonicalName();
    Context mContext;

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(getLayoutId(), container, false);
        mContext = fragmentView.getContext();
        findView(fragmentView);
        initComponents();
        initListeners();
        fragmentView.setBackgroundColor(ContextCompat.getColor(container.getContext(), R.color.fragmentBackground));
        return fragmentView;
    }

    protected abstract int getLayoutId();

    protected abstract void findView(View view);

    protected void initComponents() {
    }

    protected void initListeners() {
    }

    protected void loadImages() {
    }

    protected void clearImages() {
    }

    protected void saveItems(Bundle bundle) {
    }

    protected void restoreItems(Bundle bundle) {
    }

    int getOrientation() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE ? LANDSCAPE : PORTRAIT;
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
        if (bundle != null && !bundle.isEmpty()) {
            restoreItems(bundle);
        }
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
