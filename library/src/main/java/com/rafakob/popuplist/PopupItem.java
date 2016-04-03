package com.rafakob.popuplist;

public class PopupItem {
    private final int mId;
    private final String mTag;
    private final String mText;

    protected PopupItem(PopupListItem popupListItem) {
        mId = popupListItem.getId();
        mTag = popupListItem.getTag();
        mText = popupListItem.getText();
    }

    protected PopupItem(int id, String tag, String text) {
        mId = id;
        mTag = tag;
        mText = text;
    }

    public int getId() {
        return mId;
    }

    public String getTag() {
        return mTag;
    }

    public String getText() {
        return mText;
    }

    @Override
    public String toString() {
        return "PopupItem{" +
                "id=" + mId +
                ", tag='" + mTag + '\'' +
                ", text='" + mText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PopupItem popupItem = (PopupItem) o;

        if (mId != popupItem.mId) return false;
        if (mTag != null ? !mTag.equals(popupItem.mTag) : popupItem.mTag != null) return false;
        if (mText != null ? !mText.equals(popupItem.mText) : popupItem.mText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (mTag != null ? mTag.hashCode() : 0);
        result = 31 * result + (mText != null ? mText.hashCode() : 0);
        return result;
    }
}
