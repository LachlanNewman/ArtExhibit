package com.lachlannewman.artexhibit.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.lachlannewman.artexhibit.R;
import com.lachlannewman.artexhibit.models.User;

import java.util.UUID;


public class LoginActivity extends AppCompatActivity {

    public static final String USER = "User";
    private static final String POOL_ID = "us-east-2_FVKIHt2MH";
    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText userName;
    private EditText password;
    private EditText email;
    private EditText verificaton;
    private Button signUpButton;

    private EditText authUserName;
    private EditText authPassword;
    private Button loginButton;
    private ImageView socialLogin;


    private CognitoUser user;
    private CognitoUserPool userPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.userPassword);
        email = (EditText) findViewById(R.id.userEmail);
        socialLogin = (ImageView) findViewById(R.id.socialLogins);



        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExhibitActivity();
            }
        });

        userPool = new CognitoUserPool(this, POOL_ID, getString(R.string.cognito_client_id), getString(R.string.cognito_client_secret));
        user = userPool.getCurrentUser();

        socialLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExhibitActivitySocial();
            }
        });

    }

    private void startExhibitActivitySocial() {
        User user = new User("Socail Username","Social Email address");
        Intent intent = new Intent(this,ExibitionActivity.class);
        intent.putExtra(USER,user);
        startActivity(intent);
    }

    private void cognitoUser() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();

        CognitoUserPool userPool = new CognitoUserPool(this, POOL_ID, getString(R.string.cognito_client_id), getString(R.string.cognito_client_secret));

        CognitoUserAttributes userAttributes = new CognitoUserAttributes();
        userAttributes.addAttribute("email", email.getText().toString());


// Add the user attributes. Attributes are added as key-value pairs
// Adding user's given name.
// Note that the key is "given_name" which is the OIDC claim for given name



        SignUpHandler signUpHandler = new SignUpHandler() {
            @Override
            public void onSuccess(CognitoUser user, boolean signUpConfirmationState, CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
                signUpButton.setText("Submit Code");
                signUpButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startExhibitActivity();
                    }
                });
            }

            @Override
            public void onFailure(Exception exception) {
                Log.d(TAG,exception.toString());
            }
        };



        userPool.signUpInBackground(userName.getText().toString(),password.getText().toString(),userAttributes, null, signUpHandler);

    }

    public void verifyUser() {

        GenericHandler handler = new GenericHandler() {

            @Override
            public void onSuccess() {
                // User was successfully confirmed!
            }

            @Override
            public void onFailure(Exception exception) {
                // Confirmation failed, probe exception for details
            }
        };
        String code = verificaton.getText().toString();
        user.confirmSignUp(code,false,handler);
    }

    private void startExhibitActivity() {
        User user = new User(userName.getText().toString(),email.getText().toString());
        Intent intent = new Intent(this,ExibitionActivity.class);
        intent.putExtra(USER,user);
        startActivity(intent);
    }

}
