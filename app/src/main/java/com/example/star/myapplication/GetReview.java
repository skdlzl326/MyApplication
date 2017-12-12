package com.example.star.myapplication;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
public class GetReview extends GetRequest {
    public GetReview(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        String serverURLStr = "http://52.79.216.222";
        try {
            url = new URL(serverURLStr+"/get"+"-"+"review");  // http://serverURLStr/get-store
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected void onPostExecute(String jsonString) {
        if (jsonString == null)
            return;
        ArrayList<Review> arrayList = getArrayListFromJSONString(jsonString);

        /*ArrayAdapter adapter = new ArrayAdapter(activity,
                android.R.layout.simple_list_item_1,
                arrayList.toArray());*/
        ReviewAdapter reviewAdapter= new ReviewAdapter(activity,arrayList);
        ListView txtList = (ListView)activity.findViewById(R.id.reviewlist);
        txtList.setAdapter(reviewAdapter);
        txtList.getAdapter();
    }

    protected ArrayList<Review> getArrayListFromJSONString(String jsonString) {
        ArrayList<Review> output = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Review review = new Review(jsonObject.getString("storename"),
                        jsonObject.getString("content"),
                        jsonObject.getString("writer"),
                        jsonObject.getString("images"),
                        jsonObject.getString("grade"),
                        jsonObject.getString("date"));

                output.add(review);
            }

        } catch (JSONException e) {
            Log.e(TAG, "Exception in processing JSONString.", e);
            e.printStackTrace();
        }
        return output;

    }



}