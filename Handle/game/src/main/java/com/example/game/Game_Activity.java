package com.example.game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game_Activity extends AppCompatActivity {
    static final int GAME_EASY_MODE = 0;
    static final int GAME_NOMAL_MODE = 1;

    static final int GAME_2 = 2;
    static final int GAME_4 = 4;

    static final int START_TIME = 1000;

    int count = 0;
    int gameMode = 0;
    int gameBtn = 0;
    int two_four;
    int gaming = 0;

    int one = 0;
    int onePos = 0;
    int two = 0;
    ArrayList<CardInfo> al = new ArrayList<CardInfo>();
    ImageControll ic = new ImageControll();

    ImageView[] iv = new ImageView[GAME_4*2];

    TextView time_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);

        time_tv = findViewById(R.id.time_tv);

        iv[0] = findViewById(R.id.image1);
        iv[1] = findViewById(R.id.image2);
        iv[2] = findViewById(R.id.image3);
        iv[3] = findViewById(R.id.image4);
        iv[4] = findViewById(R.id.image5);
        iv[5] = findViewById(R.id.image6);
        iv[6] = findViewById(R.id.image7);
        iv[7] = findViewById(R.id.image8);

        Intent intent = getIntent();
        gameMode = intent.getExtras().getInt("mode");
        gameBtn = intent.getExtras().getInt("btn");

        if(gameBtn == GAME_2){
            ic.gameStart(GAME_2);
            two_four = GAME_2;
        }
        else if (gameBtn == GAME_4){
            ic.gameStart(GAME_4);
            two_four = GAME_4;
        }
        for(int i = 0; i < iv.length; i++){
            iv[i].setOnTouchListener(otListener);
        }
    }

    View.OnTouchListener otListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if(event.getAction() == MotionEvent.ACTION_DOWN){
                for(int i = 0; i < two_four*2; i++){
                    if(iv[i].getId() == v.getId()){
                        ic.mainGmae(i);
                    }
                }
            }
            return false;
        }
    };



    class ImageControll{
        int[] resIds = {R.drawable.bibimbap, R.drawable.buble, R.drawable.mandu, R.drawable.susireal};
        int backresIds = R.drawable.card_back;
        int[] whatThe = {1,2,3,4};
        CardInfo[] card;

        GameTimer gt = new GameTimer();

        class blind_timerTask extends TimerTask{
            public void run() {
                handler.sendEmptyMessage(START_TIME);
            }

        }

        void blindImage(){
            for(int i = 0; i < al.size(); i++){
                iv[i].setImageResource(backresIds);
            }
        }

        void ranImage(){
            Random random = new Random();
            for(int i = 0; i < al.size(); i++){
                int ran = random.nextInt(al.size());
                CardInfo temp = al.get(i);
                al.set(i, al.get(ran));
                al.set(ran, temp);
            }

        }

        void getImage(){
            iv[0].findViewById(R.id.image1);
            iv[1].findViewById(R.id.image2);
            iv[2].findViewById(R.id.image3);
            iv[3].findViewById(R.id.image4);
            iv[4].findViewById(R.id.image5);
            iv[5].findViewById(R.id.image6);
            iv[6].findViewById(R.id.image7);
            iv[7].findViewById(R.id.image8);

            for(int i = 0; i<al.size(); i++){
                iv[i].setImageResource(al.get(i).card);
            }
        }

        void gameStart(int gamebtn){
            card = new CardInfo[gamebtn];
            if(gameMode == GAME_EASY_MODE){
                gt.execute(15);
            }
            else if(gameMode == GAME_NOMAL_MODE){
                gt.execute(10);
            }

            for(int i = 0; i < gamebtn; i++) {
                card[i] = new CardInfo(resIds[i],whatThe[i]);
                al.add(i, card[i]);
            }
            al.addAll(al);

            ranImage();
            getImage();

            Timer timer = new Timer();
            timer.schedule(new blind_timerTask(), 1000);
        }

        void mainGmae(int pos){
            if (count == 0){
                iv[pos].setImageResource(al.get(pos).card);
                iv[pos].setEnabled(false);
                one = al.get(pos).card_num;
                onePos = pos;
                count++;
            }
            else if(count == 1){
                count = 1;
                iv[pos].setImageResource(al.get(pos).card);
                iv[pos].setEnabled(false);
                two = al.get(pos).card_num;

                if(one == two){
                    Toast.makeText(Game_Activity.this, "정답", Toast.LENGTH_SHORT).show();
                    iv[onePos].setVisibility(View.INVISIBLE);
                    iv[pos].setVisibility(View.INVISIBLE);
                    gmaeSet(++gaming);
                }
                else{
                    Toast.makeText(Game_Activity.this, "실패", Toast.LENGTH_SHORT).show();
                    Timer timer = new Timer();
                    for(int i = 0; i < two_four*2; i++){
                        iv[i].setEnabled(false);
                    }
                    timer.schedule(new blind_timerTask(), 1500);
                    iv[onePos].setEnabled(true);
                    iv[pos].setEnabled(true);
                }
                count = 0;
            }
        }

        void gmaeSet(int val){
            if(two_four == val){//모든걸 다 찾은경우
                gt.cancel(true);
                AlertDialog.Builder dialong = new AlertDialog.Builder(Game_Activity.this);
                dialong.setTitle("Win!!!!!");
                dialong.setMessage("메인으로이동합니다!");


                TextView tv = new TextView(Game_Activity.this);
                dialong.setView(tv);

                dialong.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialong.show();
            }
            else if(val == -1){//타임업
                AlertDialog.Builder dialong = new AlertDialog.Builder(Game_Activity.this);
                dialong.setTitle("Defeat....");
                dialong.setMessage("메인으로이동합니다!");

                TextView tv = new TextView(Game_Activity.this);
                dialong.setView(tv);

                dialong.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialong.show();
            }
        }

        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what == START_TIME){
                    blindImage();
                    for(int i = 0; i < two_four*2; i++){
                        iv[i].setEnabled(true);
                    }
                }
            }
        };
    }

    class GameTimer extends AsyncTask<Integer, Integer, Integer>{
        int time = 0;

        @Override
        protected Integer doInBackground(Integer... integers) {
                while(integers[0]>=time){
                    if(isCancelled()){
                        break;
                    }
                    publishProgress(integers[0]);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    integers[0]--;
                }
            return -1;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            time_tv.setText(values[0]+"");
        }

        @Override
        protected void onPostExecute(Integer b) {
            ImageControll ic = new ImageControll();
            ic.gmaeSet(b);
        }
    }
}

class CardInfo{
    int card;
    int card_num;

    public CardInfo(int resId, int card_num){
        this.card = resId;
        this.card_num = card_num;
    }
}