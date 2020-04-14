package com.example.nomoretrash;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.Objects;

public class ApplicationDemo extends Application {
    public static final String CHANNEL_ID = "channelNoMoreTrash";

    private static NotificationManager notificationManager;

    public static NotificationManager getNotificationManager() {
        return notificationManager;
    }

    private void createNotificationChannel(CharSequence name, String descriptionChannel, int importance) {
        // for API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(descriptionChannel);
            // upadate NotificationManager - cannot be changed after
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }




    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel(  "channel NoMoreTrash",
                "Channel pour l'application NoMoreTrash",
                NotificationManager.IMPORTANCE_DEFAULT );
    }
}