package com.sell.it.Dialog;

import android.content.Context;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sell.it.R;
import com.sell.it.Utility.DataManager;

public class ColumnNumberSelectDialog extends BaseDialogFragment {

    private SeekBar mPortraitSeekBar;
    private TextView mPortraitNumber;
    private SeekBar mLandscapeSeekBar;
    private TextView mLandscapeNumber;

    interface changeListener {
        void onValueChange(int value);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.column_number_selector_dialog_layout;
    }

    @Override
    protected void initView(View view) {
        mLandscapeSeekBar = view.findViewById(R.id.landscape_chooser);
        mPortraitSeekBar = view.findViewById(R.id.portrait_chooser);
        mLandscapeNumber = view.findViewById(R.id.landscape_number_text);
        mPortraitNumber = view.findViewById(R.id.portrait_number_text);
    }

    @Override
    protected void initComponents(Context context) {
        mPortraitSeekBar.setMax(2);
        mLandscapeSeekBar.setMax(3);

        int landscape = DataManager.getLandscapeColumnNumber();
        int portrait = DataManager.getPortraitColumnNumber();

        mPortraitSeekBar.setProgress(portrait - 1);
        mLandscapeSeekBar.setProgress(landscape - 1);

        mLandscapeNumber.setText(String.valueOf(landscape));
        mPortraitNumber.setText(String.valueOf(portrait));

        initListener(mPortraitSeekBar, mPortraitNumber, DataManager::savePortraitColumnNumber);
        initListener(mLandscapeSeekBar, mLandscapeNumber, DataManager::saveLandscapeColumnNumber);

    }

    private void initListener(SeekBar seekBar, TextView textView, changeListener changeListener) {
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        textView.setText(String.valueOf(progress == 0 ? 1 : progress));
                        changeListener.onValueChange(progress == 0 ? 1 : progress);
                    }
                }
        );
    }


}
