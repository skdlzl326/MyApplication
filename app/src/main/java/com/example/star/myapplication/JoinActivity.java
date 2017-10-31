package com.example.star.myapplication;


import android.os.Bundle;
import android.widget.EditText;

public class JoinActivity extends MainActivity{

    EditText et_id, et_pw, et_pw_chk;
    String sId, sPw, sPw_chk;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

    }
}
