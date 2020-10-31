package com.example.ex03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.imgPepe);
        imageView.setOnTouchListener(mListener);
    }

    View.OnTouchListener mListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN)
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_LONG).show();
            return false;
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.i("asd", "==================");
        Log.i("jenn", "onTouchEvent()");
        Log.i("jenn", "- ActionCode : " + event.getAction());
        Log.i("jenn", "- XY Position : " + event.getX() +", " +event.getY());
        Log.i("jenn", "- Event Time : " +event.getEventTime());
        Log.i("jenn", "- Down vent Time : " +event.getDownTime());

        return super.onTouchEvent(event);
    }
}