package com.example.user.ourapp;

import android.app.Service;
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
    }


    //public
//
//        Log.d(TAG, "Refreshed token: " + refreshedToken);
//
//        // If you want to send messages to this application instance or
//        // manage this apps subscriptions on the server side, send the
//        // Instance ID token to your app server.
//        sendRegistrationToServer(refreshedToken);
//




}


