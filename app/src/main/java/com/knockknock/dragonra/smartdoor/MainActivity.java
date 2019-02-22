package com.knockknock.dragonra.smartdoor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ACTIVITY_START", "onCreate MainActivity");
        super.onCreate(savedInstanceState);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (userSignedIn(account)) {
            Log.d("MAIN_ACTIVITY", "Opening dashboard");

            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            finish();

        } else {
            Log.d("MAIN_ACTIVITY", "Opening google sign in");

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean userSignedIn(GoogleSignInAccount account) {
        return account != null;
    }
}
