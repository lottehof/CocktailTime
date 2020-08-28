package com.example.cocktailtime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    //Setup the notification and customize it.
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyCocktail")
                .setSmallIcon(R.drawable.cocktail)
                .setContentTitle("Cocktail Added")
                .setContentText("The cocktail has been added to the app :)")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(100, builder.build());
    }

}
