package com.rafakob.popuplist.holder;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

public class ColorHolder {
    private int mColorInt = 0;
    private int mColorRes = -1;

    @ColorInt
    public int getColorInt() {
        return mColorInt;
    }

    public void setColorInt(@ColorInt int colorInt) {
        mColorInt = colorInt;
    }

    @ColorRes
    public int getColorRes() {
        return mColorRes;
    }

    public void setColorRes(@ColorRes int colorRes) {
        mColorRes = colorRes;
    }

    public static ColorHolder fromColorRes(@ColorRes int colorRes) {
        ColorHolder colorHolder = new ColorHolder();
        colorHolder.mColorRes = colorRes;
        return colorHolder;
    }

    public static ColorHolder fromColor(@ColorInt int colorInt) {
        ColorHolder colorHolder = new ColorHolder();
        colorHolder.mColorInt = colorInt;
        return colorHolder;
    }
}
