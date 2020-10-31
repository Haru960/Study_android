package com.example.part1;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button)findViewById(R.id.btn);



        Log.i("Wow", "onTouchEvent()");

        btn1.setOnClickListener(this);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "와우", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "홀리몰리 과카몰리", Toast.LENGTH_LONG).show();
    }
    //    View.OnClickListener mListenner = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Toast.makeText(MainActivity.this, "홀리몰리 과카몰리", Toast.LENGTH_LONG).show();
//        }
//
//    };



}