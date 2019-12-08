package dev.threepebbles.datalabeler.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import dev.threepebbles.datalabeler.utils.SharedPreferencesHandler;

public class DataLabelerFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "DataLabelerFirebaseMess";


    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        SharedPreferencesHandler.saveFirebaseToken(getApplicationContext(), token);

        //sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "From: " + remoteMessage.getFrom());
    }

}
