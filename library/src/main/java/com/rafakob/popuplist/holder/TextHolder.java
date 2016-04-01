package com.rafakob.popuplist.holder;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.TextView;

import com.rafakob.popuplist.utils.ResUtils;

public class TextHolder {
    private String mText;
    private int mTextRes = -1;

    public TextHolder(String text) {
        mText = text;
    }

    public TextHolder(@StringRes int textRes) {
        mTextRes = textRes;
    }

    public void loadInto(@NonNull TextView textView) {
        if (mTextRes != -1) {
            mText = ResUtils.getStringFromRes(textView.getContext(), mTextRes);
        }

        textView.setText(mText);
    }

    public String getText() {
        return mText;
    }

    public void color(@NonNull TextView textView, ColorHolder colorHolder) {
        if (colorHolder != null) {
            if (colorHolder.getColorInt() != 0) {
                textView.setTextColor(colorHolder.getColorInt());
            } else {
                textView.setTextColor(ResUtils.getColorFromRes(textView.getContext(), colorHolder.getColorRes()));
            }
        }
    }
}

