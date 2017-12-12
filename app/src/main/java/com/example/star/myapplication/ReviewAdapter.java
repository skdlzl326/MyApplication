package com.example.star.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.util.Locale;

public class ReviewAdapter extends ArrayAdapter<Review> {
    public ReviewAdapter(Context context, ArrayList<Review> reviews) {
        super(context, 0, reviews);
    }

    private Bitmap bitmap;
    URL url;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Review review = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adapter_review, parent, false);
        }

        // Lookup views within item layout
        TextView stwriter= (TextView) convertView.findViewById(R.id.stwriter);
        TextView sttime = (TextView) convertView.findViewById(R.id.sttime);
        TextView stgrade = (TextView) convertView.findViewById(R.id.stgrade);
        TextView stcontent = (TextView) convertView.findViewById(R.id.stcontent);
        TextView ststorename = (TextView) convertView.findViewById(R.id.ststorename);
        final TextView stimages = (TextView) convertView.findViewById(R.id.stimages2);
        ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);
        String st = review.getDate();
        String[] arr = st.split("T");
        stwriter.setText(review.getWriter());
        sttime.setText(arr[0]);
        stgrade.setText(review.getGrade());
        stcontent.setText(review.getContent());
        ststorename.setText(review.getStorename());
        stimages.setText(review.getImages());

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