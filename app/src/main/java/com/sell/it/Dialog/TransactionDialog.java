package com.sell.it.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.sell.it.Communication.EventListener;
import com.sell.it.R;
import com.sell.it.Utility.EventDispatcher;

import static com.sell.it.Model.Event.TRANSACTION;

public class TransactionDialog {

    private static Dialog mDialog = null;

    private static EventListener eventListener = event -> {
        switch (event.getEventType()) {
            case TRANSACTION:
                return true;
        }
        return false;
    };

    public static void showDialog(Context context) {
        initDialog(context);
        mDialog.show();
    }

    public static void dismissDialog() {
        if (mDialog != null) {
            mDialog.setOnKeyListener(null);
            mDialog.dismiss();
            mDialog = null;
            EventDispatcher.unSubscribe(eventListener);
        }
    }

    private static void initDialog(Context context) {
        mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCancelable(false);
        mDialog.setContentView(R.layout.transaction_dialog_layout);
        if (mDialog.getWindow() != null) {
            mDialog.getWindow().setBackgroundDrawableResource(R.color.colorTransparentOverlay);
            mDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT);
        }
        EventDispatcher.subscribe(eventListener);

        mDialog.setOnKeyListener((arg0, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dismissDialog();
            }
            return true;
        });
    }

}
