package com.rafakob.popuplist;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;

import com.rafakob.popuplist.holder.ColorHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PopupList {
    public static final int START_TOP = 0;
    public static final int START_CENTER = 1;
    public static final int START_BOTTOM = 2;
    public static final int END_TOP = 3;
    public static final int END_CENTER = 4;
    public static final int END_BOTTOM = 5;
    public static final int MIDDLE_TOP = 6;
    public static final int MIDDLE = 7;
    public static final int MIDDLE_BOTTOM = 8;
    public static final int OUTSIDE_TOP = 9;
    public static final int OUTSIDE_CENTER = 10;
    public static final int OUTSIDE_BOTTOM = 11;
    public static final int OUTSIDE_MIDDLE_TOP = 12;
    public static final int OUTSIDE_MIDDLE_CENTER = 13;
    public static final int OUTSIDE_MIDDLE_BOTTOM = 14;

    private ListPopupWindow mPopup;
    private PopupAdapter mAdapter;
    private Context mContext;
    private View mAnchorView;
    private int mContentWidth;
    private OnPopupListItemListener mOnPopupListItemListener;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private int mRelativePosition;
    private int mTextColor;
    private int mTextColorRes;
    private int mIconColor;
    private int mIconColorRes;
    private int mDividerColor;
    private int mDividerColorRes;
    private int mBackgroundColor;
    private int mBackgroundColorRes;
    private int mAnimationStyle;
    private int mTextAppearance;
    private boolean mIsModal;
    private boolean mIsIconGoneWhenNotDefined;

    private PopupList(Builder b) {
        mContext = b.context;
        mAnchorView = b.anchorView;
        mOnPopupListItemListener = b.onPopupListItemListener;
        mOnDismissListener = b.onDismissListener;
        mRelativePosition = b.relativePosition;
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

    private void setupAdapter(List<PopupItem> items) {
        PopupItem item;
        for (int i = 0; i < items.size(); i++) {
            item = items.get(i);
            item.setId(i);
            item.setIconGoneWhenNotDefined(mIsIconGoneWhenNotDefined);

            if (item.getTextAppearance() == -1 && mTextAppearance != -1) {
                item.setTextAppearance(mTextAppearance);
                continue;
            }

            if (item.getBackgroundColorHolder() == null && (mBackgroundColor != 0 || mBackgroundColorRes != -1)) {
                item.setBackgroundColorHolder(mBackgroundColor != 0 ? ColorHolder.fromColor(mBackgroundColor) : ColorHolder.fromColorRes(mBackgroundColorRes));
            }

            if (item.getTextColorHolder() == null && (mTextColor != 0 || mTextColorRes != -1)) {
                item.setTextColorHolder(mTextColor != 0 ? ColorHolder.fromColor(mTextColor) : ColorHolder.fromColorRes(mTextColorRes));
            }

            if (item.getIconColorHolder() == null && (mIconColor != 0 || mIconColorRes != -1)) {
                item.setIconColorHolder(mIconColor != 0 ? ColorHolder.fromColor(mIconColor) : ColorHolder.fromColorRes(mIconColorRes));
            }

            if (item.getDividerColorHolder() == null && (mDividerColor != 0 || mDividerColorRes != -1)) {
                item.setDividerColorHolder(mDividerColor != 0 ? ColorHolder.fromColor(mDividerColor) : ColorHolder.fromColorRes(mDividerColorRes));
            }

        }
        mAdapter = new PopupAdapter(mContext, items);
    }

    private void setupList() {
        mPopup = new ListPopupWindow(mContext);
        mPopup.setAdapter(mAdapter);
        mPopup.setAnchorView(mAnchorView);
        mContentWidth = measureContentWidth(mAdapter, mContext);
        mPopup.setContentWidth(mContentWidth);
        mPopup.setModal(mIsModal);
        mPopup.setOnDismissListener(mOnDismissListener);
        mPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnPopupListItemListener != null)
                    mOnPopupListItemListener.onPopupListItemClicked(mAdapter.getItem(position));
                mPopup.dismiss();
            }
        });

        if (mAnimationStyle != -1) {
            mPopup.setAnimationStyle(mAnimationStyle);
        }
    }

    public void show() {
        if (mRelativePosition != -1) {
            mPopup.setVerticalOffset(calculateVerticalOffset(mRelativePosition));
            mPopup.setHorizontalOffset(calculateHorizontalOffset(mRelativePosition));
        }
        mPopup.show();
    }

    private int measureContentWidth(ListAdapter adapter, Context context) {
        int maxWidth = 0;
        int count = adapter.getCount();
        final int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        View itemView = null;
        for (int i = 0; i < count; i++) {
            itemView = adapter.getView(i, itemView, new FrameLayout(context));
            itemView.measure(widthMeasureSpec, heightMeasureSpec);
            maxWidth = Math.max(maxWidth, itemView.getMeasuredWidth());
        }
        return maxWidth;
    }

    private int calculateVerticalOffset(@RelativePosition int relativePosition) {
        final int shadow = calculateVerticalShadow();
        switch (relativePosition) {
            case OUTSIDE_TOP:
            case OUTSIDE_MIDDLE_TOP:
            case START_TOP:
            case MIDDLE_TOP:
            case END_TOP:
                return -mAnchorView.getHeight() - shadow;

            case OUTSIDE_CENTER:
            case OUTSIDE_MIDDLE_CENTER:
            case START_CENTER:
            case MIDDLE:
            case END_CENTER:
                return -mAnchorView.getHeight() / 2 - shadow;

            case OUTSIDE_BOTTOM:
            case OUTSIDE_MIDDLE_BOTTOM:
            case START_BOTTOM:
            case MIDDLE_BOTTOM:
            case END_BOTTOM:
            default:
                return -shadow;
        }
    }

    private int calculateHorizontalOffset(@RelativePosition int relativePosition) {
        final int shadow = calculateHorizontalShadow();
        switch (relativePosition) {
            case OUTSIDE_TOP:
            case OUTSIDE_CENTER:
            case OUTSIDE_BOTTOM:
                return -16 - mContentWidth;

            case OUTSIDE_MIDDLE_TOP:
            case OUTSIDE_MIDDLE_CENTER:
            case OUTSIDE_MIDDLE_BOTTOM:
                return -16 - mContentWidth + mAnchorView.getWidth() / 2;

            case START_TOP:
            case START_CENTER:
            case START_BOTTOM:
            default:
                return -16;

            case MIDDLE_TOP:
            case MIDDLE:
            case MIDDLE_BOTTOM:
                return mAnchorView.getWidth() / 2 - shadow;

            case END_TOP:
            case END_CENTER:
            case END_BOTTOM:
                return mAnchorView.getWidth() - shadow;
        }
    }

    private int calculateVerticalShadow() {
        Rect rect = new Rect();
        mPopup.getBackground().getPadding(rect);
        return rect.top;
    }

    private int calculateHorizontalShadow() {
        Rect rect = new Rect();
        mPopup.getBackground().getPadding(rect);
        return rect.left;
    }

    /**
     * Popup position relative to anchor view.
     */
    @IntDef({START_TOP, START_CENTER, START_BOTTOM, END_TOP, END_CENTER, END_BOTTOM, MIDDLE_TOP, MIDDLE, MIDDLE_BOTTOM,
            OUTSIDE_TOP, OUTSIDE_CENTER, OUTSIDE_BOTTOM, OUTSIDE_MIDDLE_TOP, OUTSIDE_MIDDLE_CENTER, OUTSIDE_MIDDLE_BOTTOM})
    public @interface RelativePosition {
    }

    /**
     * PopupList builder class.
     */
    public static final class Builder {
        private Context context;
        private List<PopupItem> items = new ArrayList<>();
        private View anchorView;
        private OnPopupListItemListener onPopupListItemListener;
        private PopupWindow.OnDismissListener onDismissListener;
        private int backgroundColor = 0;
        private int backgroundColorRes = -1;
        private int animationStyle = -1;
        private boolean isModal = true;
        private boolean isIconGoneWhenNotDefined = true;
        private int relativePosition = -1;
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

        public Builder withRelativePosition(@RelativePosition int position) {
            relativePosition = position;
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


        public Builder addItems(@NonNull PopupItem... popupListItems) {
            items = Arrays.asList(popupListItems);
            return this;
        }

        public PopupList build() {
            return new PopupList(this);
        }
    }
}

