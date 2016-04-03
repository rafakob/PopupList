package com.rafakob.example.popuplist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.rafakob.popuplist.OnPopupListItemListener;
import com.rafakob.popuplist.PopupDirection;
import com.rafakob.popuplist.PopupGravity;
import com.rafakob.popuplist.PopupItem;
import com.rafakob.popuplist.PopupListItem;
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
                        new PopupListItem().withText("D").withIcon(R.drawable.ic_lock),
                        new PopupListItem().withText("D").withIcon(R.drawable.ic_lock),
                        new PopupListItem().withText("D").withIcon(R.drawable.ic_lock),
                        new PopupListItem().withText("M").withIcon(R.drawable.ic_vector_cloud),
                        new PopupListItem().withText("Teskt testowy!")
                )
                // globals:
                .withPopupGravity(PopupGravity.CENTER_HORIZONTAL | PopupGravity.BOTTOM)
                .withPopupDirection(PopupDirection.UP | PopupDirection.RIGHT)
                .withOnPopupListItemListener(new OnPopupListItemListener() {
                    @Override
                    public void onPopupListItemClicked(PopupItem item) {
                        Log.d("Popup", item.toString());
                    }
                })
                .build();
    }

}
