package com.example.listview_image;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String[] items = {"red", "blue", "green","black","white"};
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> aa = new MyAdapter(this, R.layout.row, R.id.tv, items);

        lv.setAdapter(aa);

    }
}

class MyAdapter extends ArrayAdapter<String>{

    LayoutInflater inflater;
    String[] mItems;

    public MyAdapter(@NonNull Context context, int resource, int textViewResourceId, String[] objects) {
        super(context, resource, textViewResourceId, objects);
        mItems = objects;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.row, null);
        }
        ImageView iv = (ImageView)view.findViewById(R.id.Listeitem_iv);
        int nResId = R.drawable.hhhhhh;
        if(position%2 == 0){
            nResId = R.drawable.sssss;
        }
        iv.setImageResource(nResId);
        TextView tv = (TextView)view.findViewById(R.id.tv);
        tv.setText(mItems[position]);
        return view;
    }
}