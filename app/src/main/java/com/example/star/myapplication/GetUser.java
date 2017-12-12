package com.example.star.myapplication;

import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class GetUser extends GetRequest {
    public GetUser(Activity activity) {
        super(activity,null);
    }

    @Override
    protected void onPreExecute() {
        String serverURLStr = "http://52.79.216.222";
        try {
            url = new URL(serverURLStr+"/get"+"-"+"user");  // http://serverURLStr/get-store
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    protected void onPostExecute(String jsonString) {
        if (jsonString == null)
            return;
        ArrayList<User> arrayList = getArrayListFromJSONString(jsonString);

        ArrayAdapter adapter = new ArrayAdapter(activity,
                android.R.layout.simple_list_item_1,
                arrayList.toArray());
        ListView txtList = (ListView)activity.findViewById(R.id.txtList);
        txtList.setAdapter(adapter);
        txtList.getAdapter();
    }

    protected ArrayList<User> getArrayListFromJSONString(String jsonString) {
        ArrayList<User> output = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                User user = new User(jsonObject.getString("username"),
                        jsonObject.getString("password"),
                        jsonObject.getString("nickname"),
                        jsonObject.getString("gender"));
                output.add(user);
            }

        } catch (JSONException e) {
            Log.e(TAG, "Exception in processing JSONString.", e);
            e.printStackTrace();
        }
        return output;
    }
}
