package com.example.progress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    static final  int PROGRESSBAR_START = 1;

    ProgressBar pb;
    Button btn;

    Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == PROGRESSBAR_START){
                if(pb.getProgress() < pb.getMax()) {
                    pb.setProgress(pb.getProgress() + 1);
                    sendEmptyMessageDelayed(PROGRESSBAR_START, 25);
                    if(pb.getProgress() == pb.getMax()){
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                        btn.setEnabled(true);

                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.progress_btn);
        pb = findViewById(R.id.progressBar);
    }

    public void onClick (View v){
        btn.setEnabled(false);
        pb.setProgress(0);
        handler.sendEmptyMessage(PROGRESSBAR_START);
    }
}