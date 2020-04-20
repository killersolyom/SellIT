package com.sell.it.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import com.sell.it.R;

public class ConfirmDialog {

    private static Dialog mDialog;

    public static void showDialog(Context context, String confirmText, Runnable yesOption, Runnable noOption) {
        initDialog(context, confirmText, yesOption, noOption);
        mDialog.show();
    }

    public static void dismissDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    private static void initDialog(Context context, String confirmText, Runnable yesOption, Runnable noOption) {
        mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCancelable(false);
        mDialog.setContentView(R.layout.confirm_dialog_layout);
        TextView confirmTextView = mDialog.findViewById(R.id.confirm_text);
        TextView yesText = mDialog.findViewById(R.id.yes_text);
        TextView noText = mDialog.findViewById(R.id.no_text);

        confirmTextView.setText(confirmText);
        yesText.setOnClickListener(v -> yesOption.run());
        noText.setOnClickListener(v -> noOption.run());
    }


}
