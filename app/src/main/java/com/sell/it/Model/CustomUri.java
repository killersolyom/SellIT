package com.sell.it.Model;

import android.net.Uri;

import java.io.Serializable;

public class CustomUri implements Serializable {
    private Uri mUri;

    public CustomUri(Uri item) {
        mUri = item;
    }

    public Uri getUri() {
        return mUri;
    }
}
