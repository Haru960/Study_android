package com.example.ex_2_inputform;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView resultlv;
    ArrayAdapter<Info> aa;
    ArrayList<Info> al = new ArrayList<Info>();
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();

        btn = findViewById(R.id.back_btn);

        al = intent.getParcelableArrayListExtra("stu");

        aa = new  MyAdapter( this , R.layout.row, R.id.tv, al);

        resultlv = findViewById(R.id.resultlv1);

        resultlv.setAdapter(aa);

        btn.setOnClickListener(mListener);
    }
    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };
}


class MyAdapter extends ArrayAdapter<Info>{
    LayoutInflater inflater;
    ArrayList<Info> mItems;

    public MyAdapter(@NonNull Context context, int resource, int textViewResourceId, ArrayList objects) {
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
        if(mItems.get(position).class_.contains("주말반")){
            nResId = R.drawable.sssss;
        }
        iv.setImageResource(nResId);
        TextView tv = (TextView)view.findViewById(R.id.tv);
        String str = mItems.get(position).print_(); // 여기 수정 필요
        tv.setText(str);
        return view;
    }
}

