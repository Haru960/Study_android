package com.example.my_sql_01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MySQLiteOpenHelper dbHelper;
    SQLiteDatabase mdb;
    TextView tv;
    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MySQLiteOpenHelper(this ,"mydb.db", null, 1);

        mdb = dbHelper.getWritableDatabase();

        tv = findViewById(R.id.id);
    }

    public void onClickDelete (View v){

        mCursor.moveToFirst();

        int id = mCursor.getInt(0);
        mdb.execSQL("DELETE FROM world WHERE _id=" +id +";");
        Toast.makeText(this, "레코드 삭제", Toast.LENGTH_SHORT).show();
    }

    public void onClickUpdate(View v){
        mCursor.moveToFirst();

        int id = mCursor.getInt(0);
        mdb.execSQL("UPDATE world SET capital='서울' WHERE _id= "+id +";");
        Toast.makeText(this, "레코드 수정", Toast.LENGTH_SHORT).show();
    }

    public void onClickInsert(View v){
        mdb.execSQL("INSERT INTO world VALUES( null, 'korea', 'seoul');");
        Toast.makeText(this, "레코드 추가", Toast.LENGTH_SHORT).show();
    }

    public void onClickRead(View v){
        mCursor = mdb.rawQuery("SELECT * FROM world", null);
        String str = "";

        while (mCursor.moveToNext()){
            String country = mCursor.getString(1);

            String capital = mCursor.getString(2);
            str +=(country +" - " +capital +"\n");
        }
        if (str.length() > 0){
            tv.setText(str);
        }
        else{
            Toast.makeText(this, "읽어올 레코드가 없습니다", Toast.LENGTH_SHORT).show();
            tv.setText("");
        }
    }
}