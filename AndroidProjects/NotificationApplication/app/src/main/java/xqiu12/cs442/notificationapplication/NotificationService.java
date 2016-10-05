package xqiu12.cs442.notificationapplication;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationService extends Service {
    Intent intent;
    int countingNumber;
    Context context = this;
    Timer timer;

    //get the intent for the service and set it STICKY(run or stop as i wish)
    @Override
    public int onStartCommand(Intent itt, int flags, int startId){
        intent = itt;
        countingNumber = intent.getIntExtra("countingNumber", 1);
        return START_STICKY;
    }

    //create a timer to count number and run the notification each 10s
    @Override
    public void onCreate(){
        super.onCreate();
        timer = new Timer();
        MyTimerTask myTimerTask = new MyTimerTask();
        timer.schedule(myTimerTask, 0, 10000);
    }

    public void setNotification(){
        // define sound URI, the sound to be played when there's a notification
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] vibrate = {1000, 1000, 1000};

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        //notification builder
        Notification notification = new Notification.Builder(context)

                .setSmallIcon(R.drawable.icon)
                .setContentTitle("Come on! Check it!")
                .setContentText("The number has been counted to " + countingNumber)
                .setContentIntent(pendingIntent)
                .setVibrate(vibrate)
                .setSound(soundUri)

                .build();

        //start the notification in the foreground to ensure it work
        startForeground(1200, notification);
        //+10s each setting
        countingNumber += 10;
    }

    //real killer for service stopping
    @Override
    public void onDestroy(){
        timer.cancel();
        stopForeground(true);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //helper class for timer
    class MyTimerTask extends TimerTask{
        @Override
        public void run(){
            setNotification();
        }
    }
}
