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


public class GetStore extends GetRequest {
    public GetStore(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        String serverURLStr = "http://52.79.216.222";
        try {
            url = new URL(serverURLStr+"/get"+"-"+"store");  // http://serverURLStr/get-store
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected void onPostExecute(String jsonString) {
        if (jsonString == null)
            return;
        ArrayList<Store> arrayList = getArrayListFromJSONString(jsonString);

        /*ArrayAdapter adapter = new ArrayAdapter(activity,
                android.R.layout.simple_list_item_1,
                arrayList.toArray());*/
        StoreAdapter adapter = new StoreAdapter(activity,arrayList);
        ListView txtList = (ListView)activity.findViewById(R.id.storelist);
        txtList.setAdapter(adapter);
        txtList.getAdapter();
    }

    protected ArrayList<Store> getArrayListFromJSONString(String jsonString) {
        ArrayList<Store> output = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Store store = new Store(jsonObject.getString("title"),
                        jsonObject.getString("kind"),
                        jsonObject.getString("address"),
                        jsonObject.getString("opentime"),
                        jsonObject.getString("closetime"),
                        jsonObject.getString("phonenumber"),
                        jsonObject.getString("description"));

                output.add(store);
            }

        } catch (JSONException e) {
            Log.e(TAG, "Exception in processing JSONString.", e);
            e.printStackTrace();
        }
        return output;

    }



}
