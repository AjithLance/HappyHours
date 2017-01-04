package com.example.sumit.happyhours;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {
    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefManager = new PrefManager(getApplicationContext());
        Thread background = new Thread() {
            public void run() {

                try {

                    sleep(2000);



                if(prefManager.isFirstTimeLaunch()){
                     prefManager.setFirstTimeLaunch(true);
                     startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                     finish();
                }
                    else{

                         startActivity(new Intent(MainActivity.this, HappyHour.class));
                         finish();
                    }




                } catch (Exception e) {

                }
            }
        };


        background.start();

    }
}
