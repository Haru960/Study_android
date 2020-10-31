package com.example.mysql_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText insert_et1;
    EditText insert_et2;
    EditText update_et1;
    EditText update_et2;
    EditText update_et3;
    EditText delete_et;
    TextView tv;

    MySQLiteOpenHelper mysql;
    SQLiteDatabase mdb;
    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mysql = new MySQLiteOpenHelper(this, "mydb2", null, 1);
        mdb = mysql.getWritableDatabase();

        insert_et1 = findViewById(R.id.Insert_et1);
        insert_et2 = findViewById(R.id.Insert_et2);
        update_et1 = findViewById(R.id.Update_et1);
        update_et2 = findViewById(R.id.Update_et2);
        update_et3 = findViewById(R.id.Update_et3);

        delete_et = findViewById(R.id.delete_et);
        tv = findViewById(R.id.tv);

        read();
    }

    public void onClickDelete(View v){
        mCursor.moveToFirst();
        int id = mCursor.getInt(0);

        String selection = "_id = ?";
        String[] selectionArgs = {""+id};

        mdb.delete("world", selection, selectionArgs);
    }

    public void onClickUpdate(View v){
        mCursor.moveToFirst();
        int id = mCursor.getInt(0);

        ContentValues values = new ContentValues();
        values.put("capital", "서울");

//        String selection = "_id = ?";
//        String[] selectionArgs = {""+id};

//        mdb.update("world", values, selection, selectionArgs);
        mdb.update("world", values,"_id = " +id, null);
    }

    public void onClickInsert(View v){
        ContentValues values = new ContentValues();
        values.put("country", "korea");
        values.put("capital", "seoul");

        mdb.insert("world", null, values);

    }

    public void onClickRead(View v){
        read();
    }

    void read(){

//        mCursor = mdb.rawQuery("SELECT * FROM world", null);

        mCursor = mdb.query("world", null,null,null,null,null,null);

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