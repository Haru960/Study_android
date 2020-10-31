package com.example.handle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    int count = 0;
    static final int WHAT_HANDLER_MSG_COUNT = 1;
    boolean temp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv_count);
    }


    public void onClickStop(View view) {
        myHandler.removeMessages(WHAT_HANDLER_MSG_COUNT);
        temp = false;
        Toast.makeText(this, "중지 버튼을 눌렀습니다. 현재 count는 " + count + "입니다.", Toast.LENGTH_SHORT).show();
    }

    public void onClickStart(View view) {

        count = 0;
        tv.setText(count + "초");
        temp = true;


        Thread thread = new Thread("count thread") {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(temp == true){
                        Message msg = myHandler.obtainMessage(WHAT_HANDLER_MSG_COUNT, count);
                        myHandler.sendMessage(msg);
                    }
                    else if(temp == false){
                        break;
                    }
                    Log.i("jenn", "count : " + count);
                }
            }
        };
        thread.start();
    }

    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == WHAT_HANDLER_MSG_COUNT && msg.arg1 <5){
                count++;
                tv.setText(count + "초");
            }
        }
    };
}