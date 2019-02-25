package com.knockknock.dragonra.smartdoor.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.knockknock.dragonra.smartdoor.controller.Services.NotificationService;
import com.knockknock.dragonra.smartdoor.controller.Services.SmartDoorFirebaseMessagingService;

public class MainActivity extends AppCompatActivity {

    private void setupWritePermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(this)) {
                return;
            }

            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + MainActivity.this.getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ACTIVITY_START", "onCreate MainActivity");
        super.onCreate(savedInstanceState);

        setupWritePermission();
        NotificationService.createNotificationChannel(this);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (userSignedIn(account)) {
            Log.d("MAIN_ACTIVITY", "Opening dashboard");

            SmartDoorFirebaseMessagingService.logFirebaseToken();

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
