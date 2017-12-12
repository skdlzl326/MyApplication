package com.example.star.myapplication;

import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;


public class InsertUser extends PostRequest {
    public InsertUser(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        String serverURLStr = "http://52.79.216.222";
        try {
            url = new URL(serverURLStr + "/signup");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}