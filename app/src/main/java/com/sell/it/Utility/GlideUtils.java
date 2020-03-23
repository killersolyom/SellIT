package com.sell.it.Utility;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class GlideUtils {

    private static Context mContext;

    public static void initialize(Context context) {
        mContext = context;
    }

    public static void loadImage(int id, ImageView imageView) {
        clearImage(imageView);
        Glide.with(mContext).load(id).fitCenter().into(imageView);
    }

    public static void loadBackgroundImage(int id, ImageView imageView) {
        clearImage(imageView);
        Glide.with(mContext).load(id).centerCrop().into(imageView);
    }

    public static void clearImage(ImageView imageView) {
        Glide.with(mContext).clear(imageView);
    }

    public void changeBackgroundWithFadeOut(int id, int delayTime, ImageView imageView) {
        Glide.with(mContext).load(id).centerCrop().transition(withCrossFade(delayTime)).into(imageView);
    }
}
