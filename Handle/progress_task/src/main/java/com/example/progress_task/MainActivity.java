package com.example.progress_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    android.widget.ProgressBar pb;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = findViewById(R.id.progressBar1);

        btn = findViewById(R.id.progress_btn1);
    }

    public void onClick1(View v){
        pb.setProgress(0);
        new ProgressBar().execute(pb.getMax());

    }
    class ProgressBar extends AsyncTask<Integer, Integer, Integer>{

        int progress = 0;

        @Override
        protected void onPreExecute() {
            progress = 0;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            while (true){
                if(progress < integers[0]) {
                    progress++;
                    publishProgress(progress);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    break;
                }
            }
            return progress;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            pb.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer aLong) {
            Toast.makeText(MainActivity.this, aLong+"", Toast.LENGTH_SHORT).show();
        }
    }
}
