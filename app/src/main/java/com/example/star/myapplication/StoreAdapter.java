package com.example.star.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class StoreAdapter extends ArrayAdapter<Store> {
    public StoreAdapter(Context context, ArrayList<Store> stores) {
        super(context, 0, stores);
    }
    private Drawable d;
    private Bitmap bitmap;
    URL url;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Store store = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adapter_store, parent, false);
        }

        // Lookup views within item layout
        TextView sttitle = (TextView) convertView.findViewById(R.id.sttitle);
        TextView stkind = (TextView) convertView.findViewById(R.id.stkind);
        TextView staddress = (TextView) convertView.findViewById(R.id.staddress);
        TextView stopentime = (TextView) convertView.findViewById(R.id.stopentime);
        TextView stclosetime = (TextView) convertView.findViewById(R.id.stclosetime);
        TextView stphonenumber = (TextView) convertView.findViewById(R.id.stphonenumber);
        TextView stdescription = (TextView) convertView.findViewById(R.id.stdescription);
        final TextView stimages = (TextView) convertView.findViewById(R.id.stimages);
        ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);

        sttitle.setText(store.gettitle());
        stkind.setText(store.getkind());
        staddress.setText(store.getaddress());
        stopentime.setText(store.getopentime());
        stclosetime.setText(store.getclosetime());
        stphonenumber.setText(store.getphonenumber());
        stdescription.setText(store.getdescription());
        stimages.setText(store.getimages());
        Thread mThread = new Thread(){

            @Override
            public void run(){
                try {
                    String baseurl= "http://52.79.216.222/"+stimages.getText();
                    url = new URL(baseurl);

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };

        mThread.start();
        try {
            mThread.join();
            ivPosterImage.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
