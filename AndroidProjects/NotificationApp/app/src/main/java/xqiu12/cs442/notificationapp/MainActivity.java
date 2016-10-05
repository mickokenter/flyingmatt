package xqiu12.cs442.notificationapp;

import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // listener handler
        View.OnClickListener handler = new View.OnClickListener(){
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.button_start:
                        showNotification();
                        break;

                    case R.id.button_stop:
                        cancelNotification(0);
                        break;
                }
            }
        };

        // we will set the listeners
        findViewById(R.id.button_start).setOnClickListener(handler);
        findViewById(R.id.button_stop).setOnClickListener(handler);

    }

    public void showNotification(){

        // define sound URI, the sound to be played when there's a notification
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // intent triggered, you can add other intent for other actions
        Intent intent = new Intent(MainActivity.this, NotificationReceiver.class);
        PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

        // this is it, we'll build the notification!
        // in the addAction method, if you don't want any icon, just set the first param to 0
        Notification mNotification = new Notification.Builder(this)

                .setSmallIcon(R.drawable.icon)
                .setContentTitle("New Post!")
                .setContentText("Here's an awesome update for you!")
                .setContentIntent(pIntent)
                .setSound(soundUri)

                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // If you want to hide the notification after it was selected, do the code below
        // myNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, mNotification);
    }

    public void cancelNotification(int notificationId){

        if (Context.NOTIFICATION_SERVICE!=null) {
            String ns = Context.NOTIFICATION_SERVICE;
            NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
            nMgr.cancel(notificationId);
        }
    }

}
