package com.rafakob.popuplist;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;

import com.rafakob.popuplist.holder.ColorHolder;
import com.rafakob.popuplist.utils.ListUtils;
import com.rafakob.popuplist.utils.ResUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PopupList {
    private final Context mContext;
    private final OnPopupListItemListener mOnPopupListItemListener;
    private final PopupWindow.OnDismissListener mOnDismissListener;
    private final int mPopupGravity;
    private final int mPopupDirection;
    private final int mTextColor;
    private final int mTextColorRes;
    private final int mIconColor;
    private final int mIconColorRes;
    private final int mDividerColor;
    private final int mDividerColorRes;
    private final int mBackgroundColor;
    private final int mBackgroundColorRes;
    private final int mAnimationStyle;
    private final int mTextAppearance;
    private final boolean mIsModal;
    private final boolean mIsIconGoneWhenNotDefined;
    private ListPopupWindow mPopup;
    private PopupAdapter mAdapter;
    private View mAnchorView;
    private int mContentWidth;
    private int mContentHeight;

    private PopupList(Builder b) {
        mContext = b.context;
        mAnchorView = b.anchorView;
        mOnPopupListItemListener = b.onPopupListItemListener;
        mOnDismissListener = b.onDismissListener;
        mPopupGravity = b.popupGravity;
        mPopupDirection = b.popupDirection;
        mBackgroundColor = b.backgroundColor;
        mBackgroundColorRes = b.backgroundColorRes;
        mAnimationStyle = b.animationStyle;
        mIsModal = b.isModal;
        mIsIconGoneWhenNotDefined = b.isIconGoneWhenNotDefined;
        mTextColor = b.textColor;
        mTextColorRes = b.textColorRes;
        mIconColor = b.iconColor;
        mIconColorRes = b.iconColorRes;
        mDividerColor = b.dividerColor;
        mDividerColorRes = b.dividerColorRes;
        mTextAppearance = b.textAppearance;

        setupAdapter(b.items);
        setupList();
    }

    /**
     * Iterate over
     */
    private void setupAdapter(List<PopupListItem> items) {
        PopupListItem item;
        for (int i = 0; i < items.size(); i++) {
            item = items.get(i);
            item.setId(i);
            item.setIconGoneWhenNotDefined(mIsIconGoneWhenNotDefined);
        }
        mAdapter = new PopupAdapter(mContext, items);
    }

    private void setupList() {
        mPopup = new ListPopupWindow(mContext);
        mPopup.setAdapter(mAdapter);
        mPopup.setAnchorView(mAnchorView);
        mContentWidth = ListUtils.measureListContentWidth(mAdapter, mContext);
        mContentHeight = ListUtils.measureListContentHeight(mAdapter, mContext);
        mPopup.setContentWidth(mContentWidth);
        mPopup.setModal(mIsModal);
        mPopup.setOnDismissListener(mOnDismissListener);
        mPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnPopupListItemListener != null)
                    mOnPopupListItemListener.onPopupListItemClicked(new PopupItem(mAdapter.getItem(position)));
                mPopup.dismiss();
            }
        });

        if (mAnimationStyle != -1) {
            mPopup.setAnimationStyle(mAnimationStyle);
        }
    }

    private void updateTheme() {
        PopupListItem item;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            item = mAdapter.getItem(i);

            if (item.getTextAppearance() == -1 && mTextAppearance != -1 && item.getTextAppearance() != mTextAppearance) {
                item.setTextAppearance(mTextAppearance);
            }

            item.setBackgroundColorHolder(getColorHolder(item.getBackgroundColorHolder(), mBackgroundColor, mBackgroundColorRes, R.attr.popuplist_backgroundColor));
            item.setTextColorHolder(getColorHolder(item.getTextColorHolder(), mTextColor, mTextColorRes, R.attr.popuplist_textColor));
            item.setIconColorHolder(getColorHolder(item.getIconColorHolder(), mIconColor, mIconColorRes, R.attr.popuplist_iconColor));
            item.setDividerColorHolder(getColorHolder(item.getDividerColorHolder(), mDividerColor, mDividerColorRes, R.attr.popuplist_dividerColor));
        }
    }

    public void show() {
        show(mAnchorView);
    }

    public void show(View anchorView) {
        if (anchorView == null) {
            throw new NullPointerException("PopupList - Anchor view can not be null");
        } else {
            mAnchorView = anchorView;
        }

        mPopup.setAnchorView(mAnchorView);

        if (mPopupGravity != -1) {
            mPopup.setVerticalOffset(ListUtils.getVerticalOffset(mPopup, mAnchorView, mPopupGravity | mPopupDirection, mContentHeight));
            mPopup.setHorizontalOffset(ListUtils.getHorizontalOffset(mPopup, mAnchorView, mPopupGravity | mPopupDirection, mContentWidth));
        }
        updateTheme();
        mPopup.show();
    }

    private ColorHolder getColorHolder(ColorHolder colorHolder, int colorInt, int colorRes, @AttrRes int colorAttr) {
        if (colorHolder == null && (colorInt != 0 || colorRes != -1)) {
            // color provided in PopupList Builder
            return colorInt != 0 ? ColorHolder.fromColor(colorInt) : ColorHolder.fromColorRes(colorRes);
        } else if (colorHolder == null) {
            // color provided in theme
            int color = ResUtils.getColorFromAttr(mContext, colorAttr);
            if (color != 0) {
                return ColorHolder.fromColor(color);
            } else {
                return null;
            }
        } else {
            // color provided in PopupListItem Builder
            return colorHolder;
        }
    }

    /**
     * PopupList builder class.
     */
    public static final class Builder {
        private Context context;
        private List<PopupListItem> items = new ArrayList<>();
        private View anchorView;
        private OnPopupListItemListener onPopupListItemListener;
        private PopupWindow.OnDismissListener onDismissListener;
        private int backgroundColor = 0;
        private int backgroundColorRes = -1;
        private int animationStyle = -1;
        private boolean isModal = true;
        private boolean isIconGoneWhenNotDefined = true;
        private int popupGravity = -1;
        private int popupDirection = -1;
        private int textColor = 0;
        private int textColorRes = -1;
        private int iconColor = 0;
        private int iconColorRes = -1;
        private int dividerColor = 0;
        private int dividerColorRes = -1;
        private int textAppearance = -1;

        public Builder() {
        }

        public Builder withContext(Context val) {
            context = val;
            return this;
        }

        public Builder withAnchorView(View val) {
            anchorView = val;
            return this;
        }

        public Builder withOnPopupListItemListener(OnPopupListItemListener val) {
            onPopupListItemListener = val;
            return this;
        }

        public Builder withOnDismissListener(PopupWindow.OnDismissListener val) {
            onDismissListener = val;
            return this;
        }

        public Builder withPopupGravity(int gravity) {
            popupGravity = gravity;
            return this;
        }

        public Builder withPopupDirection(int direction) {
            popupDirection = direction;
            return this;
        }

        public Builder withBackgroundColor(@ColorInt int val) {
            backgroundColor = val;
            return this;
        }

        public Builder withBackgroundColorRes(@ColorRes int val) {
            backgroundColorRes = val;
            return this;
        }

        public Builder withAnimationStyle(@StyleRes int val) {
            animationStyle = val;
            return this;
        }

        public Builder withIsModal(boolean val) {
            isModal = val;
            return this;
        }

        public Builder withIconGoneWhenNotDefined(boolean val) {
            isIconGoneWhenNotDefined = val;
            return this;
        }

        public Builder withTextColor(@ColorInt int val) {
            textColor = val;
            return this;
        }

        public Builder withTextColorRes(@ColorRes int val) {
            textColorRes = val;
            return this;
        }

        public Builder withIconColor(@ColorInt int val) {
            iconColor = val;
            return this;
        }

        public Builder withIconColorRes(@ColorRes int val) {
            iconColorRes = val;
            return this;
        }

        public Builder withDividerColor(@ColorInt int val) {
            dividerColor = val;
            return this;
        }

        public Builder withDividerColorRes(@ColorRes int val) {
            dividerColorRes = val;
            return this;
        }

        public Builder withTextAppearance(@StyleRes int val) {
            textAppearance = val;
            return this;
        }


        public Builder addItems(@NonNull PopupListItem... popupListItems) {
            items = Arrays.asList(popupListItems);
            return this;
        }

        public PopupList build() {
            return new PopupList(this);
        }
    }
}

