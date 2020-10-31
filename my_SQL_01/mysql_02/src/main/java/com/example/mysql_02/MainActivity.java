package com.example.mysql_02;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    MySQLiteOpenHelper mysql;
    SQLiteDatabase mdb;
    EditText insert_et1;
    EditText insert_et2;
    EditText update_et1;
    EditText update_et2;
    EditText update_et3;
    EditText delete_et;
    TextView tv;

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
        mCursor.moveToPosition(Integer.valueOf(delete_et.getText().toString()) -1);
        int id = mCursor.getInt(0);

//        int id = Integer.valueOf(delete_et.getText().toString());
        mdb.execSQL("DELETE FROM world WHERE _id=" +(id) +";");
        Toast.makeText(this, "레코드 삭제", Toast.LENGTH_SHORT).show();
        read();
    }

    public void onClickUpdate(View v){
        mCursor.moveToPosition(Integer.valueOf(update_et1.getText().toString())-1);
        int id = mCursor.getInt(0);

//        int id = Integer.valueOf(update_et1.getText().toString());
        mdb.execSQL("UPDATE world SET country='"+update_et2.getText().toString() +"', capital='"+update_et3.getText().toString() +"' WHERE _id = "+(id) +";");
        Toast.makeText(this, "레코드 수정", Toast.LENGTH_SHORT).show();
        read();
    }

    public void onClickInsert(View v){
        mCursor = mdb.rawQuery("SELECT * FROM world", null);
        String strC = insert_et1.getText().toString();
        mdb.execSQL("INSERT INTO world VALUES(null, '"+strC+"' ,'"+insert_et2.getText().toString()+"');");
        Toast.makeText(this, "레코드 추가", Toast.LENGTH_SHORT).show();
        read();
    }

    public void onClickRead(View v){
        read();
    }

    void read(){
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