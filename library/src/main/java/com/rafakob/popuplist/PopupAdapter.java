package com.rafakob.popuplist;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rafakob.popuplist.utils.ResUtils;

import java.util.List;

public class PopupAdapter extends ArrayAdapter<PopupItem> {
    public PopupAdapter(Context context, List<PopupItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PopupItem item = getItem(position);
        ViewHolder vh;

        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.popuplist_item, parent, false);
            vh.content = (LinearLayout) convertView.findViewById(R.id.content);
            vh.icon = (ImageView) convertView.findViewById(R.id.icon);
            vh.text = (TextView) convertView.findViewById(R.id.text);
            vh.divider = convertView.findViewById(R.id.divider);

            /* Background color - common for all items */
            if (item.getBackgroundColorHolder() != null) {
                if (item.getBackgroundColorHolder().getColorInt() != 0) {
                    vh.content.setBackgroundColor(item.getBackgroundColorHolder().getColorInt());
                } else {
                    vh.content.setBackgroundColor(ResUtils.getColorFromRes(vh.content.getContext(), item.getBackgroundColorHolder().getColorRes()));
                }
            }
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        /* Icon color and visibility */
        if (item.getIconHolder() != null) {
            item.getIconHolder().loadInto(vh.icon);
            item.getIconHolder().tint(vh.icon, item.getIconColorHolder());
            vh.icon.setVisibility(View.VISIBLE);
        } else {
            if (item.isIconGoneWhenNotDefined()) {
                vh.icon.setVisibility(View.GONE);
            } else {
                vh.icon.setVisibility(View.VISIBLE);
            }
        }

        /* Text appearance */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            vh.text.setTextAppearance(item.getTextAppearance() != -1 ? item.getTextAppearance() : android.R.style.TextAppearance);
        } else {
            vh.text.setTextAppearance(vh.text.getContext(), item.getTextAppearance() != -1 ? item.getTextAppearance() : android.R.style.TextAppearance);
        }

         /* Text color */
        if (item.getTextHolder() != null) {
            item.getTextHolder().loadInto(vh.text);
            item.getTextHolder().color(vh.text, item.getTextColorHolder());
        }

        /* Divider color and visibility */
        if (item.isDivider()) {
            vh.divider.setVisibility(View.VISIBLE);
            if (item.getDividerColorHolder() != null) {
                if (item.getDividerColorHolder().getColorInt() != 0) {
                    vh.divider.setBackgroundColor(item.getDividerColorHolder().getColorInt());
                } else {
                    vh.divider.setBackgroundColor(ResUtils.getColorFromRes(vh.divider.getContext(), item.getDividerColorHolder().getColorRes()));
                }
            }
        } else {
            vh.divider.setVisibility(View.GONE);
        }

        return convertView;
    }


    private static class ViewHolder {
        LinearLayout content;
        ImageView icon;
        TextView text;
        View divider;
    }
}