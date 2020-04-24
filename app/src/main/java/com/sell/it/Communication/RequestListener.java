package com.sell.it.Communication;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.Target;

public class RequestListener implements com.bumptech.glide.request.RequestListener {

    private ImageLoaderCallback mImageLoaderCallback;
    private String mImagePath;

    public RequestListener(ImageLoaderCallback imageLoaderCallback, String imagePath) {
        mImageLoaderCallback = imageLoaderCallback;
        mImagePath = imagePath;
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
        if (mImageLoaderCallback != null && mImagePath != null) {
            mImageLoaderCallback.onLoadFailed(mImagePath);
        }
        return false;
    }

    @Override
    public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
        if (mImageLoaderCallback != null) {
            mImageLoaderCallback.onLoadSuccess();
        }
        return false;
    }
}
