package com.example.star.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ContentFragment extends Fragment {
    private ListView listview;
    private String nickname;

    String[] arr;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        new GetStore(getActivity()).execute();
        Intent intent=getActivity().getIntent();
        nickname =intent.getStringExtra("nickname");
        View view = inflater.inflate(R.layout.storelistview,null);
        listview = (ListView)view.findViewById(R.id.storelist);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                String st = listview.getAdapter().getItem(position).toString();
                arr = st.split("\n");
                intent.putExtra("title",arr[0]);
                intent.putExtra("kind",arr[1]);
                intent.putExtra("address",arr[2]);
                intent.putExtra("opentime",arr[3]);
                intent.putExtra("closetime",arr[4]);
                intent.putExtra("phonenumber",arr[5]);
                intent.putExtra("description",arr[6]);
                intent.putExtra("images",arr[7]);
                intent.putExtra("nickname",nickname);
                startActivity(intent);

            }
        });

        return view;
    }
}
