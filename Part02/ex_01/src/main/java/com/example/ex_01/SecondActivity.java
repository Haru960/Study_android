package com.example.ex_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView name_tv;
    TextView num_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent i = getIntent();

        String str_name = i.getStringExtra("와우");
        String str_no = i.getStringExtra("왓");

        name_tv = findViewById(R.id.name_tv);
        num_tv = findViewById(R.id.num_tv);

        name_tv.setText("이름 : " +str_name);
        num_tv.setText("전화번호 : " +str_no);
    }

    public void onClickbtn(View v){
        Intent intent  = new Intent(this, MainActivity.class);

        startActivity(intent);

    }
}