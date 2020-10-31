package com.example.ex_2_easyinputform;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText et1;
    Button btn1;
    ListView lv;
    ArrayList<String> al = new ArrayList<String>();
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et);
        btn1 = findViewById(R.id.btn1);
        lv = findViewById(R.id.lv1);

        aa = new MyAapter(this, android.R.layout.simple_list_item_1, al);

        lv.setAdapter(aa);

        btn1.setOnClickListener(listener);


    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            al.add(et1.getText().toString());
            aa.notifyDataSetChanged();
        }

    };
}

class MyAapter extends ArrayAdapter<String>{
    public MyAapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}