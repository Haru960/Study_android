package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButton easy_rb;
    RadioButton nomal_rb;
    Button start_btn2;
    Button start_btn4;
    static final int GAME_EASY_MODE = 0;
    static final int GAME_NOMAL_MODE = 1;

    static final int GAME_2 = 2;
    static final int GAME_4 = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easy_rb = findViewById(R.id.easy_btn);
        nomal_rb = findViewById(R.id.nomal_btn);
        start_btn2 = findViewById(R.id.btn2);
        start_btn4 = findViewById(R.id.btn4);

        start_btn2.setOnClickListener(mListener);
        start_btn4.setOnClickListener(mListener);

    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == start_btn2.getId()){
                Intent intent = new Intent(MainActivity.this, Game_Activity.class);
                intent.putExtra("mode", checkMode());
                intent.putExtra("btn", GAME_2);

                startActivity(intent);
            }
            else if(v.getId() == start_btn4.getId()){
                Intent intent = new Intent(MainActivity.this, Game_Activity.class);
                intent.putExtra("mode", checkMode());
                intent.putExtra("btn", GAME_4);
                startActivity(intent);
            }
        }
    };
    int checkMode(){
       if(easy_rb.isChecked()){
            return GAME_EASY_MODE;
       }
       else if(nomal_rb.isChecked()){
           return GAME_NOMAL_MODE;
       }
       return GAME_EASY_MODE;
    }

}