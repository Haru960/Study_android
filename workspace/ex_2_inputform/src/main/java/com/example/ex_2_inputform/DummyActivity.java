package com.example.ex_2_inputform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class DummyActivity extends AppCompatActivity {
    ArrayList<Info> al;
    EditText dum_nameEt;
    EditText dum_idEt;
    CheckBox dum_javaCb;
    CheckBox dum_cCb;
    CheckBox dum_androidCb;
    RadioGroup dum_radioGroup;
    RadioButton dum_rb1;
    RadioButton dum_rb2;
    Button dum_re_Btn;
    Button dum_back_btn;
    Info info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        dum_nameEt = (EditText) findViewById(R.id.dum_nameInput);
        dum_idEt = (EditText) findViewById(R.id.dum_idInput);
        dum_javaCb = (CheckBox) findViewById(R.id.dum_checkbox01);
        dum_cCb = (CheckBox) findViewById(R.id.dum_checkbox02);
        dum_androidCb = (CheckBox) findViewById(R.id.dum_checkbox03);
        dum_radioGroup = (RadioGroup) findViewById(R.id.dum_radioGroup);
        dum_rb1 = (RadioButton) findViewById(R.id.dum_radioButton01);
        dum_rb2 = (RadioButton) findViewById(R.id.dum_radioButton02);
        dum_re_Btn = (Button) findViewById(R.id.dum_re_Button);
        dum_back_btn = (Button) findViewById(R.id.dum_back_btn);



        Intent intent = getIntent();
        info = intent.getParcelableExtra("stu_info");
//        position = intent.getExtras().getInt("pos");
//        info = al.get(position);

        dum_nameEt.setText(info.name);
        dum_idEt.setText(info.id);
        if(info.java.contains("Java")){
            dum_javaCb.setChecked(true);
        }
        if(info.c.contains("C")){
            dum_cCb.setChecked(true);
        }
        if(info.android.contains("Android")){
            dum_androidCb.setChecked(true);
        }

        if(info.class_.contains("평일반")){
            dum_rb1.setChecked(true);
        }
        else{
            dum_rb2.setChecked(true);
        }

        dum_re_Btn.setOnClickListener(mListener);
        dum_back_btn.setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == dum_re_Btn.getId()){

                info.name = dum_nameEt.getText().toString();
                info.id = dum_idEt.getText().toString();
                if (dum_javaCb.isChecked()) {
                    info.java = dum_javaCb.getText().toString();
                }
                else{
                    info.java = "";
                }
                if (dum_cCb.isChecked()) {
                    info.c = dum_cCb.getText().toString();
                }
                else{
                    info.c = "";
                }
                if (dum_androidCb.isChecked()) {
                    info.android = dum_androidCb.getText().toString();
                }
                else{
                    info.android = "";
                }

                if (dum_rb1.isChecked()) {
                    info.class_ = "평일반 입니다.";
                } else if (dum_rb2.isChecked()) {
                    info.class_ = "주말반 입니다.";
                }

//                al.set(position, info);

                Intent intent = new Intent();
                intent.putExtra("stu_info", info);
                setResult(RESULT_OK,intent);

                finish();
            }
            else if(v.getId() == dum_back_btn.getId()){
                finish();
            }
        }
    };
}