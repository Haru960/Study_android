package com.example.ex_2_inputform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
    TextView resultTextView;
    String str = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEt = (EditText)findViewById(R.id.nameInput);
        idEt = (EditText)findViewById(R.id.idInput);
        javaCb = (CheckBox)findViewById(R.id.checkbox01);
        cCb = (CheckBox)findViewById(R.id.checkbox02);
        androidCb = (CheckBox)findViewById(R.id.checkbox03);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        inputBtn = (Button)findViewById(R.id.inputButton);
        resultTextView = (TextView)findViewById(R.id.resultTv);
        rb1 = (RadioButton)findViewById(R.id.radioButton01);
        rb2 = (RadioButton)findViewById(R.id.radioButton02);

        str = "2";
        nameEt.setOnClickListener(mListener);
        idEt.setOnClickListener(mListener);

        inputBtn.setOnClickListener(mListener);

    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = nameEt.getText().toString();
            String id = idEt.getText().toString();

            String Java = javaCb.getText().toString();
            String C = cCb.getText().toString();
            String android = androidCb.getText().toString();


            resultTextView.setText("이름 : " +name + "\n" +"ID : " +id +"\n");

            resultTextView.append("==신청과목== \n");
            if(javaCb.isChecked()){
                resultTextView.append(Java +"\n");
            }
            if(cCb.isChecked()){
                resultTextView.append(C +"\n");
            }
            if(androidCb.isChecked()){
                resultTextView.append(android +"\n");
            }


            if(rb1.isChecked()){
                resultTextView.append("평일반 입니다. \n");
            }
            else if(rb2.isChecked()){
                resultTextView.append("주말반 입니다. \n");
                resultTextView.append(str);
            }


            nameEt.setText("");
            idEt.setText("");
            javaCb.setChecked(false);
            cCb.setChecked(false);
            androidCb.setChecked(false);
            rb1.setChecked(true);
        }
    };
}