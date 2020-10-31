package com.example.ex_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name_et;
    EditText num_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_et = findViewById(R.id.name_et);
        num_et = findViewById(R.id.num_et);
    }

    public void onClickbtn(View v){
        Intent intent  = new Intent(this, SecondActivity.class);
        intent.putExtra("와우", name_et.getText().toString());
        intent.putExtra("왓", num_et.getText().toString());
        startActivity(intent);

    }
}