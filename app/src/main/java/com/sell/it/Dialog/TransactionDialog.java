package com.sell.it.Dialog;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import com.sell.it.Communication.EventListener;
import com.sell.it.Model.Event;
import com.sell.it.R;
import com.sell.it.Utility.EventDispatcher;

import java.util.Arrays;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class TransactionDialog extends BaseDialogFragment implements EventListener {

    private Event[] mListenEvent;

    public TransactionDialog(Event... listenEvent) {
        mListenEvent = listenEvent;
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
        if (Arrays.asList(mListenEvent).contains(event)) {
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
}
