package com.example.layouttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name_et;
    Button c_btn;
    Button ok_btn;
    TextView result_tv;
    ArrayList<String> list = new ArrayList<String>();

    int end_index = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_4);

        name_et = (EditText)findViewById(R.id.et_input);
        c_btn = (Button)findViewById(R.id.btn_Cancel);
        ok_btn = (Button)findViewById(R.id.btn_Ok);
        result_tv = (TextView)findViewById(R.id.result_tv);

        c_btn.setOnClickListener(mListener);
        ok_btn.setOnClickListener(mListener);

    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = "";

            if(v.getId() == c_btn.getId()) {
                if(list.size() > 0){
                    list.remove(--end_index);
                    for(int i = 0; i < list.size(); i++){
                        str += list.get(i) +"\n";
                    }
                    result_tv.setText(str);
                }
                else{
                    Toast.makeText(MainActivity.this, "값이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }else if(v.getId() == ok_btn.getId()){
                if(name_et.getText().toString().length() > 0){
                    list.add(name_et.getText().toString());
                    end_index++;
                    for(int i = 0; i < list.size(); i++){
                        str += list.get(i) +"\n";
                    }
                    result_tv.setText(str);
                }
                else{
                    Toast.makeText(MainActivity.this, "값을 입력가세요", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}