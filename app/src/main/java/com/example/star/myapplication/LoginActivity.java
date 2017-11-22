package com.example.star.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText etId;
    private ArrayList<Book> arraylist;
    private ListView list;
    private EditText etNickname;
    private Button btnRegist;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new GetData(LoginActivity.this).execute();
        //list = (ListView)findViewById(R.id.txtList);
        /*Log.d("RESULT", list.getAdapter().getCount() + "ggggggggggggggggggg");
        for (int i=0 ; i<list.getAdapter().getCount();i++){
            String st = list.getAdapter().getItem(i).toString();
            String[] arr = st.split("\n");
            Toast.makeText(this, arr[0], Toast.LENGTH_SHORT).show();
        }*/
        etId = (EditText) findViewById(R.id.etId);
        btnRegist = (Button) findViewById(R.id.btnRegist);
        btnLogin =  (Button) findViewById(R.id.btnLogin);
        btnRegist.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),JoinActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent,1000);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent,1000);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //new GetData(LoginActivity.this).execute();
        // setResult를 통해 받아온 요청번호, 상태, 데이터
        list = (ListView)findViewById(R.id.txtList);
        Log.d("RESULT", list.getAdapter().getCount() + "ggggggggggggggggggg");
        for (int i=0 ; i<list.getAdapter().getCount();i++){
            String st = list.getAdapter().getItem(i).toString();
            String[] arr = st.split("\n");
            Toast.makeText(this, arr[0], Toast.LENGTH_SHORT).show();
        }
        if(requestCode == 1000 && resultCode == RESULT_OK) {
            Toast.makeText(LoginActivity.this, "회원가입을 완료했습니다!", Toast.LENGTH_SHORT).show();
            etId.setText(data.getStringExtra("ID"));
           /*
            Toast.makeText(this, list.getAdapter().getItem(0).toString(), Toast.LENGTH_SHORT).show();*/

        }
    }

    /*public void onStart(){
        super.onStart();
        new GetData(LoginActivity.this).execute();
        list = (ListView)findViewById(R.id.txtList);
        Log.d("RESULT", list.getAdapter().getCount() + "ggggggggggggggggggg");
        for (int i=0 ; i<list.getAdapter().getCount();i++){
            String st = list.getAdapter().getItem(i).toString();
            String[] arr = st.split("\n");
            Toast.makeText(this, arr[0], Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this, list.getAdapter().getCount(), Toast.LENGTH_SHORT).show();
    }*/
}
