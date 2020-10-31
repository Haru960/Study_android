package com.example.adapterview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] str = {"장진순","이승용","안세준","박종민"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ListView lv = findViewById(R.id.id_lv);
        ArrayAdapter<String> aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str);

        lv.setAdapter(aa);

        lv.setOnItemClickListener(ocListener);


    }

    AdapterView.OnItemClickListener ocListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Toast.makeText(MainActivity.this, str[position], Toast.LENGTH_SHORT).show();

//            Toast.makeText(MainActivity.this, ((TextView)view).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    };

}