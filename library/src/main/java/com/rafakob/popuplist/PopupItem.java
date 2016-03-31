package com.rafakob.popuplist;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;

import com.rafakob.popuplist.holder.ColorHolder;
import com.rafakob.popuplist.holder.IconHolder;
import com.rafakob.popuplist.holder.TextHolder;

public class PopupItem {
    private int mId;
    private String mTag;
    private IconHolder mIcon;
    private TextHolder mText;
    private ColorHolder mBackgroundColor;
    private ColorHolder mIconColor;
    private ColorHolder mTextColor;
    private ColorHolder mDividerColor;
    private int mTextAppearance = -1;
    private boolean mDivider;
    private boolean mIconGoneWhenNotDefined;

    public PopupItem withTag(String tag) {
        mTag = tag;
        return this;
    }

    public PopupItem withIcon(Drawable icon) {
        mIcon = new IconHolder(icon);
        return this;
    }

    public PopupItem withIcon(@DrawableRes int iconRes) {
        mIcon = new IconHolder(iconRes);
        return this;
    }

    public PopupItem withText(String text) {
        mText = new TextHolder(text);
        return this;
    }

    public PopupItem withText(@StringRes int textRes) {
        mText = new TextHolder(textRes);
        return this;
    }

    public PopupItem withColor(@ColorInt int colorInt) {
        mIconColor = ColorHolder.fromColor(colorInt);
        mTextColor = ColorHolder.fromColor(colorInt);
        return this;
    }

    public PopupItem withColorRes(@ColorRes int colorRes) {
        mIconColor = ColorHolder.fromColorRes(colorRes);
        mTextColor = ColorHolder.fromColorRes(colorRes);
        return this;
    }

    public PopupItem withTextColor(@ColorInt int colorInt) {
        mTextColor = ColorHolder.fromColor(colorInt);
        return this;
    }

    public PopupItem withTextColorRes(@ColorRes int colorRes) {
        mTextColor = ColorHolder.fromColorRes(colorRes);
        return this;
    }

    public PopupItem withIconColor(@ColorInt int colorInt) {
        mIconColor = ColorHolder.fromColor(colorInt);
        return this;
    }

    public PopupItem withIconColorRes(@ColorRes int colorRes) {
        mIconColor = ColorHolder.fromColorRes(colorRes);
        return this;
    }

    public PopupItem withDividerColor(@ColorInt int colorInt) {
        mDividerColor = ColorHolder.fromColor(colorInt);
        return this;
    }

    public PopupItem withDividerColorRes(@ColorRes int colorRes) {
        mDividerColor = ColorHolder.fromColorRes(colorRes);
        return this;
    }

    public PopupItem withDivider(boolean divider) {
        mDivider = divider;
        return this;
    }

    public PopupItem withTextAppearance(@StyleRes int style) {
        mTextAppearance = style;
        return this;
    }

    protected IconHolder getIconHolder() {
        return mIcon;
    }

    protected TextHolder getTextHolder() {
        return mText;
    }


    protected ColorHolder getIconColorHolder() {
        return mIconColor;
    }

    protected void setIconColorHolder(ColorHolder iconColor) {
        mIconColor = iconColor;
    }

    protected ColorHolder getBackgroundColorHolder() {
        return mBackgroundColor;
    }

    protected void setBackgroundColorHolder(ColorHolder backgroundColor) {
        mBackgroundColor = backgroundColor;
    }

    protected ColorHolder getTextColorHolder() {
        return mTextColor;
    }

    protected void setTextColorHolder(ColorHolder textColor) {
        mTextColor = textColor;
    }

    protected ColorHolder getDividerColorHolder() {
        return mDividerColor;
    }

    protected void setDividerColorHolder(ColorHolder dividerColor) {
        mDividerColor = dividerColor;
    }

    protected boolean isDivider() {
        return mDivider;
    }

    protected boolean isIconGoneWhenNotDefined() {
        return mIconGoneWhenNotDefined;
    }

    protected void setIconGoneWhenNotDefined(boolean iconVisible) {
        mIconGoneWhenNotDefined = iconVisible;
    }

    protected int getTextAppearance() {
        return mTextAppearance;
    }

    protected void setTextAppearance(int textAppearance) {
        mTextAppearance = textAppearance;
    }

    public String getText() {
        return mText.getText();
    }

    public int getId() {
        return mId;
    }

    protected void setId(int id) {
        mId = id;
    }

    public String getTag() {
        return mTag;
    }
}

