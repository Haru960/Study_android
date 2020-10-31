package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class tttttt extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tttttt);

        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);

        btn1.setOnClickListener(mListener);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(tttttt.this, "3번 버튼 눌림", Toast.LENGTH_SHORT).show();
            }
        });
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(tttttt.this,  "1번 버튼 눌림", Toast.LENGTH_SHORT).show();

//            if(v.getClass().getName() == "btn1"){
//                Toast.makeText(tttttt.this, "1번 버튼 클릭", Toast.LENGTH_SHORT).show();
//            }
//            else if(v.getClass().getName() == "btn2"){
//                Toast.makeText(tttttt.this, "2번 버튼 클릭", Toast.LENGTH_SHORT).show();
//            }
//            else if(v.getClass().getName() == "btn3"){
//                Toast.makeText(tttttt.this, "3번 버튼 클릭", Toast.LENGTH_SHORT).show();
//            }
        }
    };

    @Override
    public void onClick(View v) {
        Toast.makeText(tttttt.this, "2번 버튼 눌림", Toast.LENGTH_SHORT).show();
    }
}