package com.example.ex_03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {
    EditText et;
    Button ch_btn;
    Button cancel_btn;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        et = findViewById(R.id.et1);
        ch_btn = findViewById(R.id.ch_btn);
        cancel_btn = findViewById(R.id.cancel_btn);

        Intent intent = getIntent();


        position = intent.getExtras().getInt("position");
        et.setText(intent.getExtras().getString("str"));

        ch_btn.setOnClickListener(mListener);
        cancel_btn.setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(ch_btn.getId() == v.getId()){
                Intent intent = new Intent();
                String str = et.getText().toString();
                intent.putExtra("str", str);
                intent.putExtra("pos", position);
                setResult(RESULT_OK, intent);
                finish();
            }
            else if(cancel_btn.getId() == v.getId()){
                setResult(RESULT_CANCELED);
                finish();
            }
        }
    };


}