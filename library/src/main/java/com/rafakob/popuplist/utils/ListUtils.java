package com.rafakob.popuplist.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;

import com.rafakob.popuplist.PopupDirection;
import com.rafakob.popuplist.PopupGravity;


public class ListUtils {
    public static int measureListContentWidth(ListAdapter adapter, Context context) {
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

    public static int measureListContentHeight(ListAdapter adapter, Context context) {
        int sumHeight = 0;
        int count = adapter.getCount();
        final int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        View itemView = null;
        for (int i = 0; i < count; i++) {
            itemView = adapter.getView(i, itemView, new FrameLayout(context));
            itemView.measure(widthMeasureSpec, heightMeasureSpec);
            sumHeight += itemView.getMeasuredHeight();
        }
        return sumHeight;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getVerticalOffset(ListPopupWindow popupWindow, View anchorView, int position, int contentHeight) {
        final int shadow = calculateVerticalShadow(popupWindow);
        int offset = -shadow;

        if ((position & PopupGravity.BOTTOM) == PopupGravity.BOTTOM || (position & PopupDirection.DOWN) == PopupDirection.DOWN) {
            offset += 0;
        }

        if ((position & PopupGravity.TOP) == PopupGravity.TOP) {
            offset += -anchorView.getHeight();
        }

        if ((position & PopupGravity.CENTER_VERTICAL) == PopupGravity.CENTER_VERTICAL || (position & PopupGravity.CENTER) == PopupGravity.CENTER) {
            offset += -anchorView.getHeight() / 2;
        }

        if ((position & PopupDirection.UP) == PopupDirection.UP) {
            offset += -contentHeight - anchorView.getHeight() - (2 * shadow);
        }

        return offset;
    }

    public static int getHorizontalOffset(ListPopupWindow popupWindow, View anchorView, int position, int contentWidth) {
        final int shadow = calculateHorizontalShadow(popupWindow);
        int offset = -shadow;

        if ((position & PopupGravity.LEFT) == PopupGravity.LEFT) {
            offset += 0;
        }

        if ((position & PopupGravity.RIGHT) == PopupGravity.RIGHT) {
            offset += anchorView.getWidth();
        }

        if ((position & PopupGravity.CENTER_HORIZONTAL) == PopupGravity.CENTER_HORIZONTAL || (position & PopupGravity.CENTER) == PopupGravity.CENTER) {
            offset += anchorView.getWidth() / 2;
        }

        if ((position & PopupDirection.LEFT) == PopupDirection.LEFT) {
            offset += -contentWidth;
        }

        return offset;
    }

    private static int calculateVerticalShadow(ListPopupWindow popupWindow) {
//        Rect rect = new Rect();
//        popupWindow.getBackground().getPadding(rect);
        return 16;
    }

    private static int calculateHorizontalShadow(ListPopupWindow popupWindow) {
//        Rect rect = new Rect();
//        popupWindow.getBackground().getPadding(rect);
        return 16;
    }
}
