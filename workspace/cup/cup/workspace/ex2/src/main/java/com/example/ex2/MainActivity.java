package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener{
    TextView change;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        change = (TextView)findViewById(R.id.changeText);
        et = (EditText)findViewById(R.id.editText);

        CheckBox cb = (CheckBox)findViewById(R.id.CheckBox01);
        cb.setOnCheckedChangeListener(this);

        RadioGroup rb = (RadioGroup)findViewById(R.id.RadioGroup01);
//        rb.setOnCheckedChangeListener(onCheckedChangeListener);
        rb.setOnCheckedChangeListener(this);

        Button btn = (Button)findViewById(R.id.inputButton);
        btn.setOnClickListener(btnListenr);

        Button btn2 = (Button)findViewById(R.id.appendText);
        btn2.setOnClickListener(btnListenr);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String str = (isChecked) ? "설정" : "해제";
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        String str = "";


        TextView tv = (TextView)findViewById(R.id.TitleofRadio);

            switch (checkedId) {
                case R.id.RadioButton01:
                    str = "첫번째";
                    tv.setText(str);
                    break;
                case R.id.RadioButton02:
                    str = "두번째";
                    tv.setText(str);
                    break;
                case R.id.RadioButton03:
                    str = "세번째";
                    tv.setText(str);
                    break;
            }
                Toast.makeText(MainActivity.this, str+ "라디오버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
    }
    View.OnClickListener btnListenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.inputButton) {
                change.setText(et.getText().toString());
            }
            else if(v.getId() == R.id.appendText){
                change.append(et.getText().toString());
                change.append("\n");
            }
        }
    };
//        RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//            String str = "";
//
//            switch (checkedId){
//                case R.id.RadioButton01:
//                    str = "첫번째";
//                    break;
//                case R.id.RadioButton02:
//                    str = "두번째";
//                    break;
//                case R.id.RadioButton03:
//                    str = "세번째";
//                    break;
//
//            }
//            Toast.makeText(MainActivity.this, str+ "라디오버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
//        }
//    };
}