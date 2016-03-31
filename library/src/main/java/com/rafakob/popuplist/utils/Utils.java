package com.rafakob.popuplist.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

public class Utils {
    @ColorInt
    public static int getColorFromRes(Context context, @ColorRes int colorRes) {
        return ContextCompat.getColor(context, colorRes);
    }

    public static Drawable getDrawableFromRes(Context context, @DrawableRes int drawableRes) {
        return ContextCompat.getDrawable(context, drawableRes);
    }

    public static String getStringFromRes(Context context, @StringRes int stringRes) {
        return context.getResources().getString(stringRes);
    }
}
