package com.example.ex01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Ex01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);

        btn1.setOnClickListener(mListener);
        btn2.setOnClickListener(mListener);
        btn3.setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button)v;
            if(button.getText().toString().equals("1번")){
                Toast.makeText(Ex01.this, "1번눌림", Toast.LENGTH_SHORT).show();
            }
            else if(button.getText().toString().equals("2번")){
                Toast.makeText(Ex01.this, "2번눌림", Toast.LENGTH_SHORT).show();
            }
            else if(button.getText().toString().equals("3번")){
                Toast.makeText(Ex01.this, "1번눌림", Toast.LENGTH_SHORT).show();
            }
//            int id = v.getId();
//
//            if (id == R.id.btn1) {
//                Toast.makeText(Ex01.this, "1번눌림", Toast.LENGTH_SHORT).show();
//            } else if (id == R.id.btn2) {
//                Toast.makeText(Ex01.this, "2번눌림", Toast.LENGTH_SHORT).show();
//            } else if (id == R.id.btn3) {
//                Toast.makeText(Ex01.this, "3번눌림", Toast.LENGTH_SHORT).show();
//            }
        }
    };
    public void myOnClick(View v){
        Toast.makeText(Ex01.this, "4번눌림", Toast.LENGTH_SHORT).show();
    }
}