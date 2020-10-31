package com.example.storage_05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String FILENAME = "myFile.txt";
    EditText et;
    TextView tv;

    FileOutputStream fos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv_data);

        et.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
    }

    public void onClickSave (View v){
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(et.getText().toString().getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        et.setVisibility(View.INVISIBLE);
    }

    public void onClickRead (View v){
        tv.setVisibility(View.VISIBLE);
        FileInputStream fis = null;
        int len = 0;
        int result = 0;
        try {
            fis = openFileInput(FILENAME);
            len = fis.available();
            if(len>0){
                byte buff[] = new byte[len];
                while(result > -1){
                    result = fis.read(buff);
                }
                tv.setText(new String(buff));
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickMode(View v){
        et.setVisibility(View.VISIBLE);
    }
}