package com.example.ex_02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText web_et;
    EditText serch_et;
    EditText hour_et;
    EditText min_et;
    EditText num_et;
    Button web_btn;
    Button serch_btn;
    Button time_btn;
    Button num_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web_et = findViewById(R.id.et_wep);
        serch_et = findViewById(R.id.et_serch);
        hour_et = findViewById(R.id.et_hour);
        min_et = findViewById(R.id.et_min);
        num_et = findViewById(R.id.et_num);

        web_btn = findViewById(R.id.wep_btn);
        serch_btn = findViewById(R.id.serch_btn);
        time_btn = findViewById(R.id.time_btn);
        num_btn = findViewById(R.id.num_btn);


        web_btn.setOnClickListener(mListener);
        serch_btn.setOnClickListener(mListener);
        time_btn.setOnClickListener(mListener);
        num_btn.setOnClickListener(mListener);
    }
    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == web_btn.getId()){
                Intent intent = new Intent();
                intent.setAction(intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://"+web_et.getText().toString()));

                startActivity(intent);
            }
            else if(v.getId() == serch_btn.getId()){
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,serch_et.getText().toString());

                startActivity(intent);
            }
            else if(v.getId() == time_btn.getId()){
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_HOUR, Integer.valueOf(hour_et.getText().toString()))
                        .putExtra(AlarmClock.EXTRA_MINUTES, Integer.valueOf(min_et.getText().toString()));

                startActivity(intent);
            }
            else if(v.getId() == num_btn.getId()){
                Uri uri = Uri.parse("tel:"+num_et.getText());
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);

                startActivity(intent);
            }

        }
    };
}