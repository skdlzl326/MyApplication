package com.example.star.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etId;
    private Button btnRegist;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
