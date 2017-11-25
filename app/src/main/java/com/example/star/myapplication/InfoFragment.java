package com.example.star.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class InfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //new GetStore(getActivity()).execute();
        View view = inflater.inflate(R.layout.infoview,null);
        Intent intent=getActivity().getIntent();
        String title =intent.getStringExtra("title");
        String kind =intent.getStringExtra("kind");
        String address =intent.getStringExtra("address");
        String opentime =intent.getStringExtra("opentime");
        String closetime =intent.getStringExtra("closetime");
        String phonenumber =intent.getStringExtra("phonenumber");

        TextView infotitle = (TextView)view.findViewById(R.id.infotitle);
        TextView infokind = (TextView)view.findViewById(R.id.infokind);
        TextView infoaddress = (TextView)view.findViewById(R.id.infoaddress);
        TextView infotime = (TextView)view.findViewById(R.id.infotime);

        infotitle.setText(title);
        infokind.setText(kind);
        infoaddress.setText(address);
        infotime.setText(opentime +" ~ "+closetime);

      return view;
    }
}
