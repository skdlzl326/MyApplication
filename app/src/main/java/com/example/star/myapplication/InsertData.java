package com.example.star.myapplication;

import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;


public class InsertData extends PostRequest {
    public InsertData(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        String serverURLStr = "http://52.79.216.222";
        try {
            url = new URL(serverURLStr + "/insert");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}