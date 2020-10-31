package com.example.arraylist_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name_input;
    Button input_btn;
    ArrayList<String> al;
    ListView lv;

    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_input = (EditText)findViewById(R.id.et);
        input_btn = (Button)findViewById(R.id.btn_add);
        lv = (ListView)findViewById(R.id.list);


        al = new ArrayList<>();


        aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, al);

        lv.setAdapter(aa);


    }

    public void onClickAdd(View v){
        al.add(name_input.getText().toString());
        aa.notifyDataSetChanged();
    }
}