package com.example.sumit.happyhours;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by sumit on 12/17/2016.
 */

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder> {

    private Context context;
    private ArrayList<Upcomings> upcomings;

    public UpcomingAdapter(Context context, ArrayList<Upcomings> upcomings) {
        this.context = context;
        this.upcomings = upcomings;
    }

    @Override
    public UpcomingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext())
                .inflate(R.layout.layouthappyhours, parent, false);

        UpcomingViewHolder upcomingViewHolder = new UpcomingViewHolder(view);
        return upcomingViewHolder;
    }

    @Override
    public void onBindViewHolder(final UpcomingViewHolder holder, int position) {
        final Upcomings upcoming = upcomings.get(position);
        String fullUrl = "http://vga.ramstertech.com/happyhour/images/" + upcoming.image + ".jpg";
        Glide
                .with(context)
                .load(fullUrl)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(holder.upcomingimage);

        holder.shopphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(callIntent.setData(Uri.parse("tel:" +upcoming.phone.toString())));

            }
        });

        holder.shopname.setText(upcoming.name.toString());
        holder.shopdesc.setText(upcoming.description.toString());
        holder.shoplocation.setText(upcoming.location.toString());
        holder.shopvalid.setText(upcoming.valid.toString());

       holder.shopmap.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", upcoming.latitude, upcoming.longitude, "Routing to "+upcoming.name);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        context.startActivity(intent);
    }
});


    }

    @Override
    public int getItemCount() {
        if(upcomings != null){
            return upcomings.size();
        }
        return 0;
    }


    //ViewHolder class
    public static class UpcomingViewHolder extends RecyclerView.ViewHolder{

        public CardView cvUpcoming;

        public TextView shopname;

        public TextView shopdesc;

        public TextView shoplocation;

        public TextView shopvalid;

        public ImageView upcomingimage;

        public ImageView shopmap;

        public ImageView shopphone;


        public UpcomingViewHolder(View itemView) {
            super(itemView);

            cvUpcoming = (CardView)itemView.findViewById(R.id.cvUpcoming);


            shopname = (TextView)itemView.findViewById(R.id.shopname);

            shopdesc = (TextView)itemView.findViewById(R.id.shopdesc);

            shoplocation = (TextView)itemView.findViewById(R.id.shoplocation);

            shopvalid = (TextView)itemView.findViewById(R.id.shopvalid);

            upcomingimage=(ImageView)itemView.findViewById(R.id.shopimage);

            shopmap=(ImageView)itemView.findViewById(R.id.shopmap);

            shopphone = (ImageView)itemView.findViewById(R.id.phonecall);



        }
    }
}



