package com.lachlannewman.artexhibit.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lachlannewman.artexhibit.R;
import com.lachlannewman.artexhibit.models.User;

public class UserActivity extends AppCompatActivity {

    private TextView userName;
    private TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        User user = intent.getParcelableExtra(LoginActivity.USER);

        userName = (TextView) findViewById(R.id.userDetailsName);
        userEmail = (TextView) findViewById(R.id.userDetailsEmail);

        userName.setText(user.getUserName());
        userEmail.setText(user.getEmail());
    }
}
