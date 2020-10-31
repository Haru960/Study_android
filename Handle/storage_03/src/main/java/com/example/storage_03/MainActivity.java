package com.example.storage_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText id_et;
    EditText pass_et;
    Button save_btn;

    @Override
    protected void onResume() {
        super.onResume();
        id_et = findViewById(R.id.id_et);
        pass_et = findViewById(R.id.pass_et);

        SharedPreferences pref = getSharedPreferences("com.example.storage_03.stove", MODE_PRIVATE);
        String id = pref.getString("id", "");
        String pass = pref.getString("pass", "");

        id_et.setText(id);
        pass_et.setText(pass);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save_btn = findViewById(R.id.btn);
        save_btn.setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences pref = getSharedPreferences("com.example.storage_03.stove", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("id", id_et.getText().toString());
            editor.putString("pass", pass_et.getText().toString());
            editor.commit();
        }
    };

}