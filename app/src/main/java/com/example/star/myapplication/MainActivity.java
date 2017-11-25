package com.example.star.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView f1 = (ImageView)findViewById(R.id.f1);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uri uri = Uri.parse("https://store.naver.com/restaurants/detail?id=31676210&entry=plt&query=%EB%A9%94%EC%A2%85%EB%93%9C%EB%A3%A8%EC%A6%88");
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                startActivity(intent);
            }
        });
        ImageView f2 = (ImageView)findViewById(R.id.f2);
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Uri uri = Uri.parse("https://store.naver.com/restaurants/detail?id=37420979");
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                startActivity(intent);
            }
        });
        ImageView f3 = (ImageView)findViewById(R.id.f3);
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uri uri = Uri.parse("https://store.naver.com/restaurants/detail?id=11716359");
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                startActivity(intent);
            }
        });
        ImageView f4 = (ImageView)findViewById(R.id.f4);
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uri uri = Uri.parse("https://store.naver.com/restaurants/detail?id=11569524&entry=plt&query=%EC%88%98%EC%97%B0%EC%82%B0%EB%B0%A9");
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                startActivity(intent);
            }
        });
        ImageView f5 = (ImageView)findViewById(R.id.f5);
        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uri uri = Uri.parse("https://store.naver.com/restaurants/detail?id=37090281&entry=plt&query=%EC%9C%A4%ED%9C%98%EC%8B%9D%EB%8B%B9");
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                startActivity(intent);
            }
        });

       /* ImageView nt = (ImageView)findViewById(R.id.notice);
        nt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("회원정보");
              //  dlg.setMessage(id + " 님 환영합니다.");
                dlg.setIcon(R.drawable.icon);
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });*/


        ImageView imgsearch = (ImageView) findViewById(R.id.imgsearch);
        imgsearch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),TestActivity.class);
                startActivity(intent);
            }
        });
        ImageView imgreview = (ImageView) findViewById(R.id.imgreview);
        imgreview.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),TestActivity.class);
                startActivity(intent);
            }
        });
        ImageView imgadd = (ImageView) findViewById(R.id.imgadd);
        imgadd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),TestActivity.class);
                startActivity(intent);
            }
        });

    }
}
