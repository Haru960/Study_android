package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button btn;
    AsyncTask ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);

        ac = new AccumulateTask().execute(100);

        btn.setOnClickListener(mListener);

    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ac.cancel(true);
        }
    };


    class AccumulateTask extends AsyncTask<Integer, String, Long>{

        long value = 0;


        @Override
        protected Long doInBackground(Integer... integers) {

            while(isCancelled() == false){
                value++;
                if(value <= integers[0]){
                    publishProgress(value + "");
                }
                else{
                    break;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return value;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            tv.setText(values[0]);
        }

        @Override
        protected void onPostExecute(Long result) {
            tv.setText("success!!!");
            Log.i("jenn", "작업을 마친 후 반환 값은 " +result +"입니다.");
        }
    }
}



