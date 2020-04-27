package com.sell.it.Communication;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.Target;

public abstract class RequestListener implements com.bumptech.glide.request.RequestListener {

    public RequestListener() {
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
        this.onLoadFailed();
        return false;
    }

    @Override
    public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
        onLoadSuccess();
        return false;
    }

    public void onLoadFailed() {
    }

    public void onLoadSuccess() {
    }
}
