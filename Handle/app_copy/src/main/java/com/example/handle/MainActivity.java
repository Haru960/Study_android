package com.example.handle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv_count);
    }


    public void onClickStop(View view) {
        Toast.makeText(this, "중지 버튼을 눌렀습니다. 현재 count는 " + count + "입니다.", Toast.LENGTH_SHORT).show();
    }

    public void onClickStart(View view) {

        count = 0;
        tv.setText(count + "초");


        Thread thread = new Thread("count thread") {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    count++;

                    Log.i("jenn", "count : " + count);
                }
            }
        };
        thread.start();

        tv.setText("count : " + count);
    }
}