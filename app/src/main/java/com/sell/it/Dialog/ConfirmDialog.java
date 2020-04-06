package com.sell.it.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import com.sell.it.R;

public class ConfirmDialog {

    private static Dialog dialog;

    public static void showDialog(Context context, String confirmText, Runnable yesOption, Runnable noOption) {
        initDialog(context, confirmText, yesOption, noOption);
        dialog.show();
    }

    public static void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    private static void initDialog(Context context, String confirmText, Runnable yesOption, Runnable noOption) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.confirm_dialog_layout);
        TextView confirmTextView = dialog.findViewById(R.id.confirm_text);
        TextView yesText = dialog.findViewById(R.id.yes_text);
        TextView noText = dialog.findViewById(R.id.no_text);

        confirmTextView.setText(confirmText);
        yesText.setOnClickListener(v -> yesOption.run());
        noText.setOnClickListener(v -> noOption.run());
    }


}
