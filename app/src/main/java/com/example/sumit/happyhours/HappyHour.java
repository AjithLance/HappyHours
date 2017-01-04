package com.example.sumit.happyhours;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.kosalgeek.android.json.JsonConverter;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class HappyHour extends AppCompatActivity {
    final String TAG = "HappyHour";
    RecyclerView rvUpcoming;

    SweetAlertDialog sweetAlertDialog;
    SweetAlertDialog sweetAlertDialogp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_hour);
        rvUpcoming = (RecyclerView)findViewById(R.id.rvUpcoming);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rvUpcoming.setHasFixedSize(true);
        rvUpcoming.setItemViewCacheSize(20);
        rvUpcoming.setDrawingCacheEnabled(true);
        rvUpcoming.setLayoutManager(manager);

        sweetAlertDialog = new SweetAlertDialog(this,SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        sweetAlertDialogp = new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);


        if(isNetworkAvailable()){

       // progressBar.show();
        sweetAlertDialogp.setTitleText("").setCustomImage(R.drawable.hhlogo).show();
        String url = "http://vga.ramstertech.com/happyhour/upcoming.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        ArrayList<Upcomings> productList = new JsonConverter<Upcomings>()
                                .toArrayList(response, Upcomings.class);

                        UpcomingAdapter adapter = new UpcomingAdapter(getApplicationContext(), productList);

                        rvUpcoming.setAdapter(adapter);

                        sweetAlertDialogp.hide();

                        sweetAlertDialog.setTitleText(" Welcome Pune ")
                                .setContentText("Offer only valids as per the details and timing mentioned in the App")
                                .setCustomImage(R.drawable.hhlogo)
                                .show();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){
                            Log.d(TAG, error.toString());
                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                            sweetAlertDialogp.hide();
                        }
                    }
                }

        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);}

        else{
              Toast.makeText(getApplicationContext(),"No Intrnet Available",Toast.LENGTH_SHORT).show();

            }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
