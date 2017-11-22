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

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText etId;
    private EditText etPassword;
    private ListView list;
    private Button btnRegist;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etId =(EditText) findViewById(R.id.etId);
        etPassword = (EditText) findViewById(R.id.etPassword);
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
                list = (ListView)findViewById(R.id.txtList);
                boolean bool= false;
                for (int i=0 ; i<list.getAdapter().getCount();i++){
                    String st = list.getAdapter().getItem(i).toString();
                    String[] arr = st.split("\n");
                    if(etId.getText().toString().equals(arr[0])==true && etPassword.getText().toString().equals(arr[1])==true){
                        bool=true;
                    }
                }
                if (bool==true){Intent intent = new Intent(getApplicationContext(),TestActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivityForResult(intent,1000);
                    Toast.makeText(LoginActivity.this, etId.getText().toString() +"님 환영합니다.", Toast.LENGTH_SHORT).show();
                    bool=false;
                }
                else{
                    Toast.makeText(LoginActivity.this, "아이디 및 비밀번호를 확인해주세요!", Toast.LENGTH_SHORT).show();
                    etId.requestFocus();
                }
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        new GetData(LoginActivity.this).execute();

        // setResult를 통해 받아온 요청번호, 상태, 데이터
        Log.d("RESULT", requestCode + "");
        Log.d("RESULT", resultCode + "");
        Log.d("RESULT", data + "");



        if(requestCode == 1000 && resultCode == RESULT_OK) {
            Toast.makeText(LoginActivity.this, "회원가입을 완료했습니다!", Toast.LENGTH_SHORT).show();
            etId.setText(data.getStringExtra("ID"));
        }
    }

}
