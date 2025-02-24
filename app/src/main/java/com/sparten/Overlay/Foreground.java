package com.sparten.Overlay;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.sparten.spartens_productivity.R;

public class Foreground extends Service {
    public static final String TAG = "     Foreground   ";

    int value = 0;
    WindowX window;
    public Foreground() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: " + "EXECUTED");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startMyOwnForeground();

            Log.e(TAG, "onCreate: " + "if block");


        } else {
            startForeground(1, new Notification());


        }

        Log.e(TAG, "Window block: " + " as now Executed");
        window = new WindowX(this);
        window.open();
        //new Notification_function(getApplicationContext());-from notification new to stop these


        }



    @Override
    public void onDestroy() {
        super.onDestroy();

        if(window == null){
            window = new WindowX(this);
        }
        window.close(new Foreground());//this will be called when the service stop has been clicked from notification or other places
    }

    public void  clearwindow() {
          if(window == null){
              window = new WindowX(this);
          }
            switch (value) {
                case 1:
                   // window.close(Foreground.this);
                    break;
                case 2:
                   // window.close(Foreground.this);
                    break;


            }
        }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    private void startMyOwnForeground() {
    Log.e(TAG, "startMyOwnForeground: "+ "EXECUTED" );

        String Notifiacation_channel_id  =  "example.permanence";
        String channel_name  = "BackGround Service";

        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(Notifiacation_channel_id,channel_name, NotificationManager.IMPORTANCE_MIN);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    assert notificationManager != null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(channel);
        }


        //fitter on                                             notification reciver usjing it as the on reiver class
        Intent activityIntent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 3, activityIntent, 0);

        Intent broadcastIntent = new Intent(getApplicationContext(), NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", "Restriction OFF");
        PendingIntent actionIntent = PendingIntent.getBroadcast(getApplicationContext(), 3, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder  notificationBuilder = new NotificationCompat.Builder(this,Notifiacation_channel_id);
    Notification notification = notificationBuilder.setOngoing(true)
            .setContentTitle("Service running")
            .setContentText("Displaying over other apps")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(contentIntent)
            .addAction(R.mipmap.ic_launcher,"Remove Phone Use Restriction",actionIntent)
            .setPriority(NotificationManager.IMPORTANCE_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build();
    startForeground(2, notification);
    Log.e(TAG, "NotiFiction: "+ "EXECUTED" );

    }



}

