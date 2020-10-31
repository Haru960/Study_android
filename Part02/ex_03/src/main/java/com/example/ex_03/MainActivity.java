package com.example.ex_03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] str = {"한국", "중국", "일본"};
    ListView lv;
    int getPosition;
    final static int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str);

        lv.setAdapter(aa);
        lv.setOnItemClickListener(mcListener);


    }

    AdapterView.OnItemClickListener mcListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("str", ((TextView)view).getText().toString());
            intent.putExtra("position", position);
            getPosition = position;
            startActivityForResult(intent, REQUEST_CODE);
        }

    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                str[data.getExtras().getInt("pos")] = data.getExtras().getString("str");
            }
        }
    }


}