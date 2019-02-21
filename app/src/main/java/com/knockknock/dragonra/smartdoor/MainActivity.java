package com.knockknock.dragonra.smartdoor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Activity", "onCreate MainActivity");

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        // SmartDoorUser logged-in
        if (userLoggedIn(account)) {
            // TODO: create intent to dashboard
            Log.d("INTENT", "Opening dashboard");

        } else {
            // TODO: open google sign in page
            Log.d("INTENT", "Opening google sign in");
        }
    }

    private boolean userLoggedIn(GoogleSignInAccount account) {
        return account != null;
    }
}
