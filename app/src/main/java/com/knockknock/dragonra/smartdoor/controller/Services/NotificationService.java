package com.knockknock.dragonra.smartdoor.controller.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.activity.MainActivity;

public class NotificationService {

    private static final String CHANNEL_ID = "SmartDoor";
    private static int notificationId = 0;

    public static void createNotificationChannel(Context currentActivity) {

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = currentActivity.getString(R.string.notification_channel_name);
            String description = currentActivity.getString(R.string.notification_channel_description);

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = currentActivity.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    static void showFirebaseNotification(Context context, RemoteMessage.Notification firebaseNotification) {

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        Notification notification = createNotification(context, firebaseNotification);
        notificationId += 1;
        notificationManager.notify(notificationId, notification);

    }

    private static Notification createNotification(Context context, RemoteMessage.Notification firebaseNotification) {

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)

                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle(firebaseNotification.getTitle())
                .setContentText(firebaseNotification.getBody())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        return builder.build();
    }
}
