package it.localhost.app.mobile.learningandroid.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Set;

import it.localhost.app.mobile.learningandroid.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private static final String TAG = RecyclerViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        handleAppLinking(getIntent());

        // VIEW
        setContentView(R.layout.activity_recycler_view);
        getSupportActionBar().setTitle(R.string.recyclerview_actionbar_title);

        Button btnRecyclerLinear = (Button) findViewById(R.id.btnRecyclerLinear);
        btnRecyclerLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecyclerViewActivity.this, RecyclerActivity.class));
            }
        });
        Button btnRecyclerGrid = (Button) findViewById(R.id.btnRecyclerGrid);
        btnRecyclerGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecyclerViewActivity.this, RecyclerPhotoActivity.class));
            }
        });
        Button btnRecyclerCard = (Button) findViewById(R.id.btnRecyclerCard);
        btnRecyclerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecyclerViewActivity.this, RecyclerCardActivity.class));
            }
        });
        Button btnRecyclerDrag = (Button) findViewById(R.id.btnRecyclerDrag);
        btnRecyclerDrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecyclerViewActivity.this, RecyclerDragActivity.class));
            }
        });
        Button btnRecyclerExpandable = (Button) findViewById(R.id.btnRecyclerExpandable);
        btnRecyclerExpandable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecyclerViewActivity.this, RecyclerExpandActivity.class));
            }
        });
        Button btnRecyclerSticky = (Button) findViewById(R.id.btnRecyclerSticky);
        btnRecyclerSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecyclerViewActivity.this, RecyclerStickyActivity.class));
            }
        });
    }

    /**
     * @param appLinkIntent Intent
     * @see <a href="https://developer.android.com/training/app-links/deep-linking.html">Links to app content</a>
     */
    private void handleAppLinking(Intent appLinkIntent) {
        if (appLinkIntent == null) {
            return;
        }

        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
        Log.i(TAG, "appLinkAction: " + appLinkAction);
        Log.i(TAG, "appLinkData: " + (appLinkData != null ? appLinkData.toString() : ""));

        try {
            Set<String> queryParams = appLinkData.getQueryParameterNames();
            for (String queryParam : queryParams) {
                Log.i(TAG, queryParam + "=" + appLinkData.getQueryParameter(queryParam));
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }
    }
}
