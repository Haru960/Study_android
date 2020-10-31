package com.example.storage_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    static final int IMAGE_SIZE = 4;
    static final int IMAGE_KIND1 = 0;

    static final int IMAGE_KIND2 = 1;

    static final int IMAGE_KIND3 = 2;

    static final int IMAGE_KIND4 = 3;
    ImageView main_iv;
    RadioGroup rg;
    RadioButton btn1,btn2,btn3,btn4;

    ImageController ic = new ImageController();

    @Override
    protected void onResume() {
        super.onResume();

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        SharedPreferences pref = getSharedPreferences("com.example.storage_02.food", MODE_PRIVATE);

        int type_food = pref.getInt("food_num", IMAGE_KIND1);

        if(type_food == IMAGE_KIND1){
            btn1.setChecked(true);
        }
        else if(type_food == IMAGE_KIND2){
            btn2.setChecked(true);
        }
        else if(type_food == IMAGE_KIND3){
            btn3.setChecked(true);
        }
        else if(type_food == IMAGE_KIND4){
            btn4.setChecked(true);
        }

        pref.registerOnSharedPreferenceChangeListener(myListener);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_iv = findViewById(R.id.main_image);

        rg = findViewById(R.id.rg);

        rg.setOnCheckedChangeListener(rListener);

        ic.setImage();


    }

    SharedPreferences.OnSharedPreferenceChangeListener myListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//            int type_food = sharedPreferences.getInt("food_num", IMAGE_KIND1);
//            if(type_food == IMAGE_KIND1){
//                btn1.setChecked(true);
//            }
//            else if(type_food == IMAGE_KIND2){
//
//                btn2.setChecked(true);
//            }
//            else if(type_food == IMAGE_KIND3){
//
//                btn3.setChecked(true);
//            }
//            else if(type_food == IMAGE_KIND4){
//
//                btn4.setChecked(true);
//            }

        }
    };

    RadioGroup.OnCheckedChangeListener rListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == R.id.btn1){
                ic.drawImage(IMAGE_KIND1);
            }
            else if(checkedId == R.id.btn2){
                ic.drawImage(IMAGE_KIND2);
            }
            else if(checkedId == R.id.btn3){
                ic.drawImage(IMAGE_KIND3);
            }
            else if(checkedId == R.id.btn4){
                ic.drawImage(IMAGE_KIND4);
            }
        }
    };

    class ImageController{
        int[] resIds = {R.drawable.bibimbap, R.drawable.buble, R.drawable.mandu, R.drawable.susireal};
        int[] whatThe = {0,1,2,3};

        ImageViewInfo[] ivi = new ImageViewInfo[IMAGE_SIZE];

        void setImage(){
            for(int i = 0; i < IMAGE_SIZE; i++){
                ivi[i] = new ImageViewInfo(resIds[i], whatThe[i]);
            }
        }

        void drawImage(int kinds){
            main_iv.setImageResource(ivi[kinds].card);
//            saveImage(kinds);
        }
        void saveImage(int kinds){
            SharedPreferences pref = getSharedPreferences("com.example.storage_02.food", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("food_num", ivi[kinds].card_num);
            editor.commit();
        }
    }

    class ImageViewInfo{
        int card;
        int card_num;

        ImageViewInfo(int resId, int card_num){
            this.card = resId;
            this.card_num = card_num;
        }
    }

    @Override
    public void onBackPressed() {
        if(btn1.isChecked()){
            ic.saveImage(IMAGE_KIND1);
        }
        else if(btn2.isChecked()){
            ic.saveImage(IMAGE_KIND2);
        }
        else if(btn3.isChecked()){
            ic.saveImage(IMAGE_KIND3);
        }
        else if(btn4.isChecked()){
            ic.saveImage(IMAGE_KIND4);
        }
        super.onBackPressed();
    }
}