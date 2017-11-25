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


public class contentFragment extends Fragment {
    private ListView listview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        new GetStore(getActivity()).execute();
        View view = inflater.inflate(R.layout.storelistview,null);

        listview = (ListView)view.findViewById(R.id.storelist);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                String st = listview.getAdapter().getItem(position).toString();
                String[] arr = st.split("\n");
                intent.putExtra("title",arr[0]);
                intent.putExtra("kind",arr[1]);
                intent.putExtra("address",arr[2]);
                intent.putExtra("opentime",arr[3]);
                intent.putExtra("closetime",arr[4]);
                intent.putExtra("phonenumber",arr[5]);
                startActivity(intent);
            }
        });

        return view;
    }
}
