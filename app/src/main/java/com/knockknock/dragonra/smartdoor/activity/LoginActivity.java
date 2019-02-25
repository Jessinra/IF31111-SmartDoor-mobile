package com.knockknock.dragonra.smartdoor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.knockknock.dragonra.smartdoor.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_GOOGLE_SIGN_IN = 10;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ACTIVITY_START", "onCreate LoginActivity");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpGoogleSignInClient();

        findViewById(R.id.button_sign_in).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_in:
                redirectGoogleSignIn();
                break;
            default:
                break;
        }
    }

    private void redirectGoogleSignIn() {
        Log.d("LOGIN_ACTIVITY", "redirectGoogleSignIn");

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("LOGIN_ACTIVITY", "onActivityResult");

        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        Log.d("LOGIN_ACTIVITY", "handleSignInResult");

        try {
            // Signed in successfully, show authenticated UI.
            completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (userSignedIn(acct)) {
                redirectToDashboard();
            }

        } catch (ApiException e) {
            Log.w("LOGIN_ACTIVITY", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void redirectToDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean userSignedIn(GoogleSignInAccount acct) {
        return acct != null;
    }
}
