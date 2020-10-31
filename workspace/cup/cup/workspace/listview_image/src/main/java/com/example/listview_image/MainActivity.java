package com.example.listview_image;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] items = {"red", "blue", "green","black","white"};
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> aa = new ArrayAdapter(this, R.layout.row, R.id.tv,items);

        lv.setAdapter(aa);

    }
}