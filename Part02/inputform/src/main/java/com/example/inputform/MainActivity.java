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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText nameEt;
    EditText idEt;
    CheckBox javaCb;
    CheckBox cCb;
    CheckBox androidCb;
    RadioGroup radioGroup;
    RadioButton rb1;
    RadioButton rb2;
    Button inputBtn;
    //    ListView resultlv;
//    ArrayAdapter<Info> aa;
    ArrayList<Info> al;


    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEt = (EditText) findViewById(R.id.nameInput);
        idEt = (EditText) findViewById(R.id.idInput);
        javaCb = (CheckBox) findViewById(R.id.checkbox01);
        cCb = (CheckBox) findViewById(R.id.checkbox02);
        androidCb = (CheckBox) findViewById(R.id.checkbox03);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        inputBtn = (Button) findViewById(R.id.inputButton);
//        resultlv = (ListView) findViewById(R.id.resultlv);
        rb1 = (RadioButton) findViewById(R.id.radioButton01);
        rb2 = (RadioButton) findViewById(R.id.radioButton02);

        al = new ArrayList();
//        aa = new MyAdapter(MainActivity.this, R.layout.row, R.id.tv, al);
//        resultlv.setAdapter(aa);


        nameEt.setOnClickListener(mListener);
        idEt.setOnClickListener(mListener);

        inputBtn.setOnClickListener(mListener);

    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = nameEt.getText().toString();
            String id = idEt.getText().toString();
            String Java = "";
            String C = "";
            String android = "";
            String class_ = "";

            if (javaCb.isChecked()) {
                Java = javaCb.getText().toString();
            }
            if (cCb.isChecked()) {
                C = cCb.getText().toString();
            }
            if (androidCb.isChecked()) {
                android = androidCb.getText().toString();
            }

            if (rb1.isChecked()) {
                class_ = "평일반 입니다.";
            } else if (rb2.isChecked()) {
                class_ = "주말반 입니다.";
            }

            Info info = new Info(name, id, Java, C, android, class_);


//            String name = nameEt.getText().toString();
//            String id = idEt.getText().toString();
//
//            String Java = javaCb.getText().toString();
//            String C = cCb.getText().toString();
//            String android = androidCb.getText().toString();


//
//
//            str = "이름 : " +name + "\n" +"ID : " +id +"\n";
//            str += "==신청과목== \n";
//
//
//            if(javaCb.isChecked()){
//                str += Java +"\n";
//            }
//            if(cCb.isChecked()){
//                str += C +"\n";
//            }
//            if(androidCb.isChecked()){
//                str +=android +"\n";
//            }
//
//
//            if(rb1.isChecked()){
//                str +="평일반 입니다. \n";
//            }
//            else if(rb2.isChecked()){
//                str  += "주말반 입니다. \n";
//            }

            al.add(info);

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("stu", al);

            nameEt.setText("");
            idEt.setText("");
            javaCb.setChecked(false);
            cCb.setChecked(false);
            androidCb.setChecked(false);
            rb1.setChecked(true);

//            aa.notifyDataSetChanged(); // 이게 필요할까?

            startActivity(intent);
        }
    };
}
//class MyAdapter extends ArrayAdapter<Info>{
//    LayoutInflater inflater;
//    ArrayList<Info> mItems;
//
//    public MyAdapter(@NonNull Context context, int resource, int textViewResourceId, ArrayList objects) {
//        super(context, resource, textViewResourceId, objects);
//        mItems = objects;
//        inflater = LayoutInflater.from(context);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View view = convertView;
//        if(view == null){
//            view = inflater.inflate(R.layout.row, null);
//        }
//        ImageView iv = (ImageView)view.findViewById(R.id.Listeitem_iv);
//        int nResId = R.drawable.hhhhhh;
//        if(mItems.get(position).class_.contains("주말반")){
//            nResId = R.drawable.sssss;
//        }
//        iv.setImageResource(nResId);
//        TextView tv = (TextView)view.findViewById(R.id.tv);
//        String str = mItems.get(position).print_(); // 여기 수정 필요
//        tv.setText(str);
//        return view;
//    }
//}

class Info implements Parcelable {
    String name;
    String id;
    String java;
    String c;
    String android;
    String class_;

    Info(String name, String id, String java, String c, String android, String class_) {
        this.name = name;
        this.id = id;
        this.java = java;
        this.c = c;
        this.android = android;
        this.class_ = class_;
    }

    public String print_() {
        String str = "이름 : " + name + "\n" + "ID : " + id + "\n" + "==신청과목== \n" + java + "\n" + c + "\n" + android + "\n \n" + class_ + "\n";

        return str;
    }


    //여기부터                    11
    public Info(Parcel in) {
        name = in.readString();
        id = in.readString();
        java = in.readString();
        c = in.readString();
        android = in.readString();
        class_ = in.readString();
    }

    public static final Creator<Info> CREATOR = new Creator<Info>() {
        @Override
        public com.example.ex_2_inputform.Info createFromParcel(Parcel in) {
            return new com.example.ex_2_inputform.Info(in);
        }

        @Override
        public com.example.ex_2_inputform.Info[] newArray(int size) {
            return new com.example.ex_2_inputform.Info[size];
        }
    };
    //여기까지 묶음임             11


    //여기부터                                     22
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(java);
        dest.writeString(c);
        dest.writeString(android);
        dest.writeString(class_);
    }

    //여기까지 무조건 선언해야하는 추상 메서드들           22
}


//            resultlv.setText("이름 : " +name + "\n" +"ID : " +id +"\n");
//
//                    resultlv.append("==신청과목== \n");
//
//                    if(javaCb.isChecked()){
//                    resultTextView.append(Java +"\n");
//                    }
//                    if(cCb.isChecked()){
//                    resultTextView.append(C +"\n");
//                    }
//                    if(androidCb.isChecked()){
//                    resultTextView.append(android +"\n");
//                    }
//
//
//                    if(rb1.isChecked()){
//                    resultTextView.append("평일반 입니다. \n");
//                    }
//                    else if(rb2.isChecked()){
//                    resultTextView.append("주말반 입니다. \n");
//                    resultTextView.append(str);
//                    }