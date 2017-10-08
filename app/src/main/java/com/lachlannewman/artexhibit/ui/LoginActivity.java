package com.lachlannewman.artexhibit.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.lachlannewman.artexhibit.R;


public class LoginActivity extends AppCompatActivity {

    private SignInButton loginButtonGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButtonGoogle = (SignInButton) findViewById(R.id.loginButtonGoogle);
        loginButtonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMainActivity();
            }
        });

    }

    private void startMainActivity() {
        Intent intent = new Intent(this,ExibitionActivity.class);
        startActivity(intent);
    }
}
