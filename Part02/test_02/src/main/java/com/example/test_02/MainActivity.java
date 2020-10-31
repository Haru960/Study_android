package com.example.test_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{

    private static final int PALGANG = 8;
    private static final int SAGANG = 4;
    private static final int FINAL_GAME = 2;
    private static final int FINISH_GAME = 0;


    int[] resIds = {R.drawable.a_1,R.drawable.a_2, R.drawable.a_3, R.drawable.a_4,
            R.drawable.a_5, R.drawable.a_6, R.drawable.a_7, R.drawable.a_8};

    String[] strNames = {"박보영", "수지", "OOO", "신민아",
            "아이린", "아이유", "제니", "김다미"};

    CardInfo[] ideals ;
    boolean bGame = false;
    int nGameMode = FINAL_GAME;
    int indexOfLeftImg;
    int indexOfRightImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.iv_left).setOnTouchListener(this);
        findViewById(R.id.iv_right).setOnTouchListener(this);

    }


    @Override
    public void onClick(View view) {
        if(!bGame){
            view.setEnabled(false);
            initGame();
        }
    }

    void initGame(){

        bGame = true;
        nGameMode = PALGANG;

        ideals =  new CardInfo[resIds.length];
        for(int i = 0 ;i < ideals.length; i++){
            ideals[i]= new CardInfo(resIds[i], strNames[i]);
        }

        shuffle(ideals);
        indexOfLeftImg = 0;
        indexOfRightImg = nGameMode - 1;
        setDojunImage();
        ((TextView)findViewById(R.id.tv_result)).setText("8강전");
    }

    void finishGame(){
        bGame = false;
        indexOfLeftImg = 0;
        ((TextView)findViewById(R.id.tv_result)).setText("당신의 이상형은 " +ideals[indexOfLeftImg].name +"입니다.");
        findViewById(R.id.btn_start).setEnabled(true);

        //게임 모드 변경
        //시작버튼 활성화
        //결과 출력 " 당신의 이상형은 OOO 입니다"

    }

    void setDojunImage(){
        findViewById(R.id.iv_left).setBackgroundResource(ideals[indexOfLeftImg].resId);
        findViewById(R.id.iv_right).setBackgroundResource(ideals[indexOfRightImg].resId);
    }

    void calcGame(){

        if(indexOfLeftImg<indexOfRightImg){
            setDojunImage();
        }else{
            if(changeGameMode() == FINISH_GAME){
                finishGame();
                return;
            }
            else{
                setDojunImage();
            }

            //게임의 모드를 변경하고 이미지를 바꿔 보여준다
            //게임종료 모드 시 게임을 종료한다
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if(!bGame) return false;

        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            Log.i("jenn", "onTouch");
            if(view.getId() == R.id.iv_right){
                CardInfo temp = ideals[indexOfLeftImg];
                ideals[indexOfLeftImg] = ideals[indexOfRightImg];
                ideals[indexOfRightImg] = temp;
            }
            indexOfLeftImg++;
            indexOfRightImg--;
            calcGame();
        }
        return true;
    }

    int changeGameMode(){
        switch (nGameMode){
            case PALGANG :
                nGameMode = SAGANG;
                indexOfLeftImg = 0;
                indexOfRightImg = nGameMode -1;
                ((TextView)findViewById(R.id.tv_result)).setText("4강전");
                break;
            case SAGANG :
                nGameMode = FINAL_GAME ;
                indexOfLeftImg = 0;
                indexOfRightImg = nGameMode -1;
                ((TextView)findViewById(R.id.tv_result)).setText("결승전");
                break;
            case FINAL_GAME :
                nGameMode = FINISH_GAME ;
                break;
        }

        return nGameMode;
    }


    void shuffle(CardInfo[] arr){

        int rand_index = 0;
        Random rand = new Random();
        CardInfo card;

        for(int i = 0 ;i < resIds.length*3 ; i++){
            rand_index = rand.nextInt(nGameMode);
            card = arr[0];
            arr[0] = arr[rand_index];
            arr[rand_index] =card;
        }
    }


}

class CardInfo{
    int resId;
    String name;

    public CardInfo(int resId, String name){
        this.resId = resId;
        this.name = name;

    }
}