package com.rafakob.example.popuplist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rafakob.popuplist.OnPopupListItemListener;
import com.rafakob.popuplist.PopupDirection;
import com.rafakob.popuplist.PopupGravity;
import com.rafakob.popuplist.PopupItem;
import com.rafakob.popuplist.PopupList;

public class MainActivity extends AppCompatActivity {
    View btn;
    PopupList mPopupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (View) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupList.show();
            }
        });

        mPopupList = new PopupList.Builder().withContext(this)
                .withAnchorView(btn)
                .addItems(
                        new PopupItem().withText("W").withIcon(R.drawable.ic_vector_info),
                        new PopupItem().withText("D").withIcon(R.drawable.ic_lock)
                                .withDivider(true).withDividerColorRes(R.color.colorGreen),
                        new PopupItem().withText("M").withIcon(R.drawable.ic_vector_cloud)
                                .withTextColorRes(android.R.color.black).withIconColorRes(R.color.colorYellow),
                        new PopupItem().withText("No!").withDivider(true)
                )
                // globals:
                .withPopupGravity(PopupGravity.CENTER_HORIZONTAL | PopupGravity.TOP)
                .withPopupDirection(PopupDirection.UP | PopupDirection.LEFT)
                .withIconGoneWhenNotDefined(true)
                .withIconColorRes(R.color.colorPrimary)
                .withDividerColorRes(R.color.colorAccent)
                .withBackgroundColorRes(R.color.darkBackground)
                .withTextColorRes(android.R.color.white)
                .withOnPopupListItemListener(new OnPopupListItemListener() {
                    @Override
                    public void onPopupListItemClicked(PopupItem item) {

                    }
                })
                .build();
    }

}
