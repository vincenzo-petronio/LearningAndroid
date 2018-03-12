package it.localhost.app.mobile.learningandroid.service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.util.Log;

import java.util.Map;

/**
 * @author vincenzo.petronio on 06/03/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.v(TAG, "onMessageReceived");
        super.onMessageReceived(remoteMessage);

        Log.i(TAG, "onMessageReceived from: " + remoteMessage.getFrom());


        // DATA PAYLOAD
        // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
        Map<String, String> dataMap = remoteMessage.getData();
        if (dataMap.size() > 0) {
            Log.i(TAG, "onMessageReceived with data: " + dataMap);
        } else {
            Log.i(TAG, "onMessageReceived with no data.");
        }

        // NOTIFICATION PAYLOAD
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        if (notification != null) {
            Log.i(TAG, "onMessageReceived with notification: " + notification.getBody());
        } else {
            Log.i(TAG, "onMessageReceived with no notification.");
        }
    }

    @Override
    public void onDeletedMessages() {
        Log.v(TAG, "onDeletedMessages");
        super.onDeletedMessages();
    }
}
