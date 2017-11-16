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


public class GetData extends GetRequest {
    public GetData(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        String serverURLStr = "http://52.79.216.222";
        try {
            url = new URL(serverURLStr+"/get"+"-"+"data");  // http://serverURLStr/get-data
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected void onPostExecute(String jsonString) {
        if (jsonString == null)
            return;
        ArrayList<Book> arrayList = getArrayListFromJSONString(jsonString);

        ArrayAdapter adapter = new ArrayAdapter(activity,
                android.R.layout.simple_list_item_1,
                arrayList.toArray());
        ListView txtList = (ListView)activity.findViewById(R.id.txtList);
        txtList.setAdapter(adapter);
        txtList.setDividerHeight(10);
    }

    protected ArrayList<Book> getArrayListFromJSONString(String jsonString) {
        ArrayList<Book> output = new ArrayList();
        try {

            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                Book book = new Book(jsonObject.getString("username"),
                        jsonObject.getString("password"),
                        jsonObject.getString("nickname"),
                        jsonObject.getString("gender"));

                output.add(book);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Exception in processing JSONString.", e);
            e.printStackTrace();
        }
        return output;
    }
}
