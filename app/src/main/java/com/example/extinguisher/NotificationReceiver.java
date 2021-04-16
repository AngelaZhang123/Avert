package com.example.extinguisher;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {

    private final String CHANNEL_ID = "kit notification";

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent notifIntent = new Intent(context, CheckListActivity.class);
        notifIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Check Your Emergency Kit!")
                .setContentText("Hello, this is a reminder to check the contents of your emergency kit.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        PreferenceManager manager = PreferenceManager.getInstance();
        manager.setNotified(true);

        NotificationManagerCompat notifMgr = NotificationManagerCompat.from(context);
        notifMgr.notify(100, builder.build());
    }
}
