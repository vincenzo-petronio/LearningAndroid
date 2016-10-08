package it.localhost.app.mobile.learningandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import it.localhost.app.mobile.learningandroid.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private static final String TAG = RecyclerViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

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
    }
}
