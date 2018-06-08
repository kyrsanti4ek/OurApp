package com.example.user.ourapp;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.os.Handler;
import android.widget.Toast;

import static com.example.user.ourapp.loginIn.LoginActivity.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    Handler hunDler = new Handler();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

    final String title=remoteMessage.getNotification().getTitle();
    final String body=remoteMessage.getNotification().getBody();

     hunDler.post(new Runnable() {
         @Override
         public void run() {
             Toast.makeText(getApplicationContext(),title + " , " + body,Toast.LENGTH_SHORT).show();
         }
     });

        sendNotification(remoteMessage.getNotification().getBody());
    }


    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_announcement_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_announcement_black_24dp))
                .setContentTitle(this.getString(R.string.app_name))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }


}


