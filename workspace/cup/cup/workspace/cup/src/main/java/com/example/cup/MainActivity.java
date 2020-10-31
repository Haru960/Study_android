package com.example.cup;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button start_btn;
    ImageView imageView01;
    ImageView imageView02;
    BitmapDrawable[] bd;
    BitmapDrawable[] winbd;
    BitmapDrawable check;
    TextView tv;
    LinearLayout ll;


    boolean canTouch = true;
    int one = 0;
    int two = 1;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_btn = findViewById(R.id.start_btn);
        imageView01 = findViewById(R.id.image_01);
        imageView02 = findViewById(R.id.image_02);
        tv = findViewById(R.id.tv);
        ll = findViewById(R.id.ll1);
        
        check = (BitmapDrawable)getResources().getDrawable(R.drawable.winimage) ;

        start_btn.setOnClickListener(mListener);

        imageView01.setOnTouchListener(tlistener);
        imageView02.setOnTouchListener(tlistener);


    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            imageView01.setClickable(true);
            imageView02.setClickable(true);
            bd = new BitmapDrawable[8];
            winbd = new BitmapDrawable[bd.length/2];

            bd[0] = (BitmapDrawable)getResources().getDrawable(R.drawable.bibimbap);
            bd[1] = (BitmapDrawable)getResources().getDrawable(R.drawable.buble);
            bd[2] = (BitmapDrawable)getResources().getDrawable(R.drawable.justone);
            bd[3] = (BitmapDrawable)getResources().getDrawable(R.drawable.ka);
            bd[4] = (BitmapDrawable)getResources().getDrawable(R.drawable.mandu);
            bd[5] = (BitmapDrawable)getResources().getDrawable(R.drawable.rarararara);
            bd[6] = (BitmapDrawable)getResources().getDrawable(R.drawable.susireal);
            bd[7] = (BitmapDrawable)getResources().getDrawable(R.drawable.wu);
            one = 0;
            two = 1;
            count = 0;

            tv.setText(bd.length + "");

            Random random = new Random();

            for(int i = 0; i < bd.length; i++){
                int ran = random.nextInt(bd.length);
                BitmapDrawable temp = bd[i];
                bd[i] = bd[ran];
                bd[ran] = temp;
            }

            Toast.makeText(MainActivity.this, count +"입니다", Toast.LENGTH_SHORT).show();
            imageView01.setImageDrawable(bd[one]);
            imageView02.setImageDrawable(bd[two]);
            canTouch = true;
        }
    };
    View.OnTouchListener tlistener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if(event.getAction() == MotionEvent.ACTION_DOWN){

                if(canTouch == true){
                    if(v.getId() == R.id.image_01){
                        winbd[count] = bd[one]; // 배열 번호초과 버그
                    }
                    else if(v.getId() == R.id.image_02){
                        winbd[count] = bd[two]; // 배열 번호초과 버그
                    }
                    one += 2;
                    two += 2;
                    count++;

                    if(count >= (bd.length/2) && winbd.length != 1 ){
                        bd = new BitmapDrawable[winbd.length];
                        Random random = new Random();

                        for(int i = 0; i < bd.length; i++){
                            bd[i] = winbd[i];
                        }
                        for(int i = 0; i < bd.length; i++){
                            int ran = random.nextInt(bd.length);
                            BitmapDrawable temp = bd[i];
                            bd[i] = bd[ran];
                            bd[ran] = temp;
                        }
                        one = 0;
                        two = 1;
                        count = 0;
                        winbd = new BitmapDrawable[bd.length/2];

                        tv.setText(bd.length + "");
                    }
                    if((winbd.length == 1) && (count == 1)){
                        tv.setText("결과 / 다시 시작해주세요");
                        imageView01.setImageDrawable(check);
                        imageView02.setImageDrawable(winbd[0]);
                        canTouch = false;
                    }
                    else {
                        imageView01.setImageDrawable(bd[one]);
                        imageView02.setImageDrawable(bd[two]);
                    }
                }
                else if(canTouch == false){
                    return false;
                }
            }

            return false;
        }
    };
}