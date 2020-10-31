package com.example.storage_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox cb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb1 = findViewById(R.id.cb_sound);

        cb1.setOnCheckedChangeListener(ccListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getSharedPreferences("com.example.storage_01.sound", MODE_PRIVATE);
        boolean bSound = pref.getBoolean("sound", false);

        cb1.setChecked(bSound);
        pref.registerOnSharedPreferenceChangeListener(myListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener myListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            boolean bSound = sharedPreferences.getBoolean(key, false);

            String str = (bSound) ? "설정" : "해제";
            Toast.makeText(MainActivity.this, "sound가 " +str +"되었습니다.", Toast.LENGTH_SHORT).show();
        }
    };

    CompoundButton.OnCheckedChangeListener ccListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            SharedPreferences pref = getSharedPreferences("com.example.storage_01.sound", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("sound", isChecked);
            editor.commit();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences pref = getSharedPreferences("com.example.storage_01.sound", MODE_PRIVATE);
        pref.unregisterOnSharedPreferenceChangeListener(myListener);
    }
}