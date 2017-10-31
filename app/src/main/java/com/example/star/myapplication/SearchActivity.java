package com.example.star.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SearchActivity extends MainActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ImageView btnReturn = (ImageView) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
    }
}
