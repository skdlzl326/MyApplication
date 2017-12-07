package com.example.star.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StoreAdapter extends ArrayAdapter<Store> {
    public StoreAdapter(Context context, ArrayList<Store> stores) {
        super(context, 0, stores);
    }

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
        ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);

        sttitle.setText(store.gettitle());
        stkind.setText(store.getkind());
        staddress.setText(store.getaddress());
        stopentime.setText(store.getopentime());
        stclosetime.setText(store.getclosetime());
        stphonenumber.setText(store.getphonenumber());
        stdescription.setText(store.getdescription());
        return convertView;
    }
}
