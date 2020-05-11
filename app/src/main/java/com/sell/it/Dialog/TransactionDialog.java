package com.sell.it.Dialog;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import com.sell.it.Communication.EventListener;
import com.sell.it.Model.Event;
import com.sell.it.R;
import com.sell.it.Utility.EventDispatcher;
import com.sell.it.Utility.SnackBarUtility;

import java.util.ArrayList;
import java.util.Arrays;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class TransactionDialog extends BaseDialogFragment implements EventListener {

    private static ArrayList<Event> mListenEvent;
    private final int mTimeoutTime = 7500;
    private static final Handler mTimeoutHandler = new Handler();

    public TransactionDialog() {
    }

    public TransactionDialog(Event... listenEvent) {
        mListenEvent = new ArrayList<>(Arrays.asList(listenEvent));
    }

    private void startTimeoutHandler() {
        if (!mTimeoutHandler.hasMessages(0)) {
            mTimeoutHandler.removeCallbacksAndMessages(null);
            mTimeoutHandler.postDelayed(() -> {
                dismissDialog();
                SnackBarUtility.showWithText(R.string.transaction_failed, true);
            }, mTimeoutTime);
            mTimeoutHandler.sendEmptyMessage(0);
        }
    }

    @Override
    protected int getLayoutView() {
        return R.layout.transaction_dialog_layout;
    }

    @Override
    protected void loadDialogSettings(Window dialogWindow) {
        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogWindow.setLayout(MATCH_PARENT, MATCH_PARENT);
    }

    @Override
    protected void initView(View view) {
        setCancelable(false);
        EventDispatcher.subscribe(this);
    }

    @Override
    public boolean onEvent(Event event) {
        if (mListenEvent.contains(event)) {
            mListenEvent.clear();
            mTimeoutHandler.removeCallbacksAndMessages(null);
            dismissDialog();
            return true;
        }
        return false;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        EventDispatcher.unSubscribe(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        startTimeoutHandler();
    }
}
