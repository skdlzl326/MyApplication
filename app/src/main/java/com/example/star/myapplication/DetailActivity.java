package com.example.star.myapplication;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class DetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        setFragment(new DetailFragment());

        /*Intent intent=getIntent();
        String title =intent.getStringExtra("title");
        String kind =intent.getStringExtra("kind");
        String address =intent.getStringExtra("address");
        String opentime =intent.getStringExtra("opentime");
        String closetime =intent.getStringExtra("closetime");
        String phonenumber =intent.getStringExtra("phonenumber");

        TextView textView = (TextView)findViewById(R.id.infokind);
        TextView textView2 = (TextView)findViewById(R.id.infotitle);
        textView.setText(title);
        textView2.setText(kind);
        Toast.makeText(this, title, Toast.LENGTH_LONG).show();*/
    }

    public void onStart(){
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setFragment(Fragment fragment){
        if(fragment!=null){
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_test,fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
