package com.rafakob.popuplist.holder;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.rafakob.popuplist.utils.ResUtils;

public class IconHolder {
    private Drawable mDrawable;
    private int mDrawableRes = -1;


    public IconHolder(Drawable drawable) {
        mDrawable = drawable;
    }

    public IconHolder(@DrawableRes int drawableRes) {
        mDrawableRes = drawableRes;
    }

    public void loadInto(@NonNull ImageView imageView) {

        if (mDrawable != null) {
            imageView.setImageDrawable(mDrawable);

        } else if (mDrawableRes != 0) {
            imageView.setImageResource(mDrawableRes);
        }
    }

    public void tint(@NonNull ImageView imageView, ColorHolder colorHolder) {
        if (colorHolder != null) {
            Drawable icon = imageView.getDrawable();
            icon.mutate();

            if (colorHolder.getColorInt() != 0) {
                icon.setColorFilter(colorHolder.getColorInt(), PorterDuff.Mode.SRC_IN);
            } else {
                icon.setColorFilter(ResUtils.getColorFromRes(imageView.getContext(), colorHolder.getColorRes()), PorterDuff.Mode.SRC_IN);
            }
        }
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public int getDrawableRes() {
        return mDrawableRes;
    }
}
