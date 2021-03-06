package com.example.star.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class JoinActivity extends AppCompatActivity {
    final static String TAG = "AndroidNodeJS";

    private EditText etId;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private EditText etNick;
    private Button btnDone;
    private Button btnCancel;
    private String Gender = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        etId = (EditText) findViewById(R.id.etId);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
        etNick = (EditText) findViewById(R.id.etNickname);

        btnDone = (Button) findViewById(R.id.btnDone);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        final CheckBox cb1 = (CheckBox)findViewById(R.id.etSex1);
        final CheckBox cb2 = (CheckBox)findViewById(R.id.etSex2);


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cb1.isChecked() == true) Gender += cb1.getText().toString();
                if(cb2.isChecked() == true) Gender += cb2.getText().toString();

                // 아이디 입력 확인
                if( etId.getText().toString().length() == 0 ) {
                    Toast.makeText(JoinActivity.this, "아이디를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etId.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                else if( etPassword.getText().toString().length() == 0 ) {
                    Toast.makeText(JoinActivity.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }

                // 비밀번호 확인 입력 확인
                else if( etPasswordConfirm.getText().toString().length() == 0 ) {
                    Toast.makeText(JoinActivity.this, "'비밀번호 확인'을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPasswordConfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                else if( !etPassword.getText().toString().equals(etPasswordConfirm.getText().toString()) ) {
                    Toast.makeText(JoinActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    etPassword.setText("");
                    etPasswordConfirm.setText("");
                    etPassword.requestFocus();
                    return;
                }

                else if( etNick.getText().toString().length() == 0 ) {
                    Toast.makeText(JoinActivity.this, "닉네임을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etNick.requestFocus();
                    return;
                }

                else if( Gender.length() == 0 ) {
                    Toast.makeText(JoinActivity.this, "성별을 골라주세요!", Toast.LENGTH_SHORT).show();
                    cb1.requestFocus();
                    return;
                }
                JSONObject postDataParam = new JSONObject();
                try {
                    postDataParam.put("username", etId.getText().toString());
                    postDataParam.put("password", etPassword.getText().toString());
                    postDataParam.put("nickname", etNick.getText().toString());
                    postDataParam.put("gender", Gender);
                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }
                new InsertUser(JoinActivity.this).execute(postDataParam);
                new GetUser(JoinActivity.this).execute();


                Intent result = new Intent();
                result.putExtra("ID", etId.getText().toString());

                // 자신을 호출한 Activity로 데이터를 보낸다.
                setResult(RESULT_OK, result);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
