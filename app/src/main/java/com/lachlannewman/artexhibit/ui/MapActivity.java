package com.lachlannewman.artexhibit.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lachlannewman.artexhibit.R;

public class MapActivity extends AppCompatActivity {

    private TextView roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        roomId = (TextView) findViewById(R.id.roomIdMap);
        Intent intent = getIntent();
        roomId.setText(intent.getStringExtra(ArtworkActivity.ROOM_ID));
    }
}
