package com.example.storage_04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView tv_name;
    EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_name = findViewById(R.id.tv_name);
        readUserName();
    }

    void readUserName(){
        SharedPreferences pref  = getPreferences(MODE_PRIVATE);
        String str_name = pref.getString("user_name", "unkown");
        tv_name.setText(str_name);
    }

    void writeName(String name){
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("user_name", name);
        editor.commit();
    }

    public void onClickInput(View v){
        AlertDialog.Builder dialong = new AlertDialog.Builder(this);
        dialong.setTitle("이름 입력");
        dialong.setMessage("사용자의 이름을 입력합니다.");

        et_name = new EditText(this);
        dialong.setView(et_name);

        dialong.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = et_name.getText().toString();
                writeName(str);
                readUserName();
            }
        });

        dialong.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                writeName("unknown");
                readUserName();
            }
        });
        dialong.show();
    }
}