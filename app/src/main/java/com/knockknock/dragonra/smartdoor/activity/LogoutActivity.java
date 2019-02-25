package com.knockknock.dragonra.smartdoor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.knockknock.dragonra.smartdoor.R;

public class LogoutActivity extends AppCompatActivity {
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ACTIVITY_START", "onCreate LogoutActivity");

        super.onCreate(savedInstanceState);

        setUpGoogleSignInClient();
        signOut();

        redirectToLoginActivity();
    }

    private void setUpGoogleSignInClient() {

        // Build GoogleSignInClientOptions
        GoogleSignInOptions.Builder gsoBuilder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
        gsoBuilder.requestEmail();
        gsoBuilder.requestIdToken(getString(R.string.FirebaseAPIKey));
        GoogleSignInOptions gso = gsoBuilder.build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void redirectToLoginActivity() {
        Log.d("LOGOUT_ACTIVITY", "redirectToLoginActivity");

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void signOut() {
        Log.d("LOGOUT_ACTIVITY", "signOut");
        mGoogleSignInClient.signOut();
        mGoogleSignInClient.revokeAccess();
    }
}
