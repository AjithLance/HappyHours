package com.example.sumit.happyhours;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by sumit on 12/29/2016.
 */


public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {

        String recentToken= FirebaseInstanceId.getInstance().getToken();
        Log.d("token",recentToken);
    }
}