package com.sell.it.Dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.sell.it.R;

public class ConfirmDialog extends BaseDialogFragment {

    private TextView mConfirmTextView;
    private TextView mYesText;
    private TextView mNoText;
    private Runnable mYesOption;
    private int mConfirmText;

    public ConfirmDialog(int confirmTextId, Runnable yesOption) {
        mYesOption = yesOption;
        mConfirmText = confirmTextId;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.confirm_dialog_layout;
    }

    @Override
    protected void initView(View view) {
        mConfirmTextView = view.findViewById(R.id.confirm_text);
        mYesText = view.findViewById(R.id.yes_text);
        mNoText = view.findViewById(R.id.no_text);
    }

    @Override
    protected void initComponents(Context context) {
        mConfirmTextView.setText(mConfirmText);
        mYesText.setOnClickListener(v -> onConditionAccepted());
        mNoText.setOnClickListener(v -> dismissDialog());
    }

    private void onConditionAccepted() {
        mYesOption.run();
        dismissDialog();
    }

}
