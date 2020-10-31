package com.example.list_view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView item_tv;
    ListView lv;
    String[] items = {"red", "blue", "green","black","white"};
    int[] colors = {Color.RED,Color.BLUE,Color.GREEN,Color.BLACK,Color.WHITE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item_tv = (TextView)findViewById(R.id.item_tv);



        ArrayAdapter aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);

        lv = (ListView)findViewById(R.id.ListView01);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(listener);
    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, position+1 +"번째 아이템이 클릭됐습니다.", Toast.LENGTH_SHORT).show();
            TextView tv = (TextView)view;

            item_tv.setText(tv.getText().toString());

            lv.setBackgroundColor(colors[position]);


//            if(position == 0){
//                lv.setBackgroundColor(Color.RED);
//            }
//            else if(position == 1){
//                lv.setBackgroundColor(Color.BLUE);
//            }
//            else if(position == 2){
//                lv.setBackgroundColor(Color.GREEN);
//            }
//            else if(position == 3){
//                lv.setBackgroundColor(Color.BLACK);
//            }
//            else if(position == 4){
//                lv.setBackgroundColor(Color.WHITE);
//            }
        }
    };
}