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

public class PopupListItem {
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

    public PopupListItem withTag(String tag) {
        mTag = tag;
        return this;
    }

    public PopupListItem withIcon(Drawable icon) {
        mIcon = new IconHolder(icon);
        return this;
    }

    public PopupListItem withIcon(@DrawableRes int iconRes) {
        mIcon = new IconHolder(iconRes);
        return this;
    }

    public PopupListItem withText(String text) {
        mText = new TextHolder(text);
        return this;
    }

    public PopupListItem withText(@StringRes int textRes) {
        mText = new TextHolder(textRes);
        return this;
    }

    public PopupListItem withColor(@ColorInt int colorInt) {
        mIconColor = ColorHolder.fromColor(colorInt);
        mTextColor = ColorHolder.fromColor(colorInt);
        return this;
    }

    public PopupListItem withColorRes(@ColorRes int colorRes) {
        mIconColor = ColorHolder.fromColorRes(colorRes);
        mTextColor = ColorHolder.fromColorRes(colorRes);
        return this;
    }

    public PopupListItem withTextColor(@ColorInt int colorInt) {
        mTextColor = ColorHolder.fromColor(colorInt);
        return this;
    }

    public PopupListItem withTextColorRes(@ColorRes int colorRes) {
        mTextColor = ColorHolder.fromColorRes(colorRes);
        return this;
    }

    public PopupListItem withIconColor(@ColorInt int colorInt) {
        mIconColor = ColorHolder.fromColor(colorInt);
        return this;
    }

    public PopupListItem withIconColorRes(@ColorRes int colorRes) {
        mIconColor = ColorHolder.fromColorRes(colorRes);
        return this;
    }

    public PopupListItem withDividerColor(@ColorInt int colorInt) {
        mDividerColor = ColorHolder.fromColor(colorInt);
        return this;
    }

    public PopupListItem withDividerColorRes(@ColorRes int colorRes) {
        mDividerColor = ColorHolder.fromColorRes(colorRes);
        return this;
    }

    public PopupListItem withDivider(boolean divider) {
        mDivider = divider;
        return this;
    }

    public PopupListItem withTextAppearance(@StyleRes int style) {
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

    @StyleRes
    protected int getTextAppearance() {
        return mTextAppearance;
    }

    protected void setTextAppearance(int textAppearance) {
        mTextAppearance = textAppearance;
    }

    protected String getText() {
        return mText.getText();
    }

    protected int getId() {
        return mId;
    }

    protected void setId(int id) {
        mId = id;
    }

    protected String getTag() {
        return mTag;
    }
}

