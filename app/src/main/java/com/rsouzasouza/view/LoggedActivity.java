package com.rsouzasouza.view;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.rsouzasouza.synclogin.R;


public class LoggedActivity extends AppCompatActivity {

    private Button btnLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);


        btnLogout = findViewById(R.id.btnLogout);


    }
}
