package com.example.touch_game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button start_btn;
    ImageView iv;
    TextView count_view;
    TextView time_view;
    int count = 0;
    public static final int GAME_TIME = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_btn = findViewById(R.id.btn);
        iv = findViewById(R.id.iv);
        count_view = findViewById(R.id.count_view);
        time_view = findViewById(R.id.time_view);

        start_btn.setOnClickListener(mListener);
        iv.setOnTouchListener(tmListener);
        iv.setEnabled(false);
        iv.setBackgroundColor(Color.BLACK);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            start_btn.setEnabled(false);
            iv.setEnabled(true);
            iv.setBackgroundColor(Color.GREEN);
            count = 0;
            new CountDownTimer(5000, 2000){
                @Override
                public void onTick(long millisUntilFinished) {
                    time_view.setText(String.format(Locale.getDefault(), "%d sec", millisUntilFinished /1000L));
                }

                @Override
                public void onFinish() {
                    iv.setEnabled(false);
                    iv.setBackgroundColor(Color.BLACK);
                    start_btn.setEnabled(true);
                }
            }.start();
//            TimerTask timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    iv.setEnabled(false);
//                    iv.setBackgroundColor(Color.BLACK);
//                }
//            };
//
//            Timer timer = new Timer();
//            timer.schedule(timerTask, 1000*GAME_TIME);
        }
    };

    View.OnTouchListener tmListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(v.getId() == iv.getId()){
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    count++;
                }
                count_view.setText(count+"");
            }
            return false;
        }
    };
}