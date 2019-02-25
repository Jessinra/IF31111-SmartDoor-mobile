package com.knockknock.dragonra.smartdoor.controller.Services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class SmartDoorFirebaseMessagingService extends FirebaseMessagingService {

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d("FIREBASE_TOKEN", "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO : send token to database, link to user account
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("MESSAGING_SERVICE", "onMessageReceived");
        Log.d("MESSAGING_SERVICE", "From: " + remoteMessage.getFrom());

        if (containPayloads(remoteMessage)) {
            Log.d("MESSAGING_SERVICE", "Message data payload: " + remoteMessage.getData());

            Map<String, String> payload = remoteMessage.getData();
            // TODO: do something with the data received
        }

        if (containNotification(remoteMessage)) {
            Log.d("MESSAGING_SERVICE", "Message Notification body: " + remoteMessage.getNotification().getTitle());
            Log.d("MESSAGING_SERVICE", "Message Notification body: " + remoteMessage.getNotification().getBody());

            RemoteMessage.Notification firebaseNotification = remoteMessage.getNotification();
            NotificationService.showFirebaseNotification(this, firebaseNotification);
        }
    }

    private boolean containPayloads(RemoteMessage remoteMessage) {
        return remoteMessage.getData().size() > 0;
    }

    private boolean containNotification(RemoteMessage remoteMessage) {
        return remoteMessage.getNotification() != null;
    }


    public static void logFirebaseToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("MESSAGING_SERVICE", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        Log.d("FIREBASE_TOKEN", "Firebase Messaging token : " + token);
                    }
                });
    }

}
