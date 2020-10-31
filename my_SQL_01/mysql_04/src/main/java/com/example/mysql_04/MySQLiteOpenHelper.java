package com.example.mysql_04;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    Context context;

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE world (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "country TEXT, capital TEXT);");

        db.execSQL("INSERT INTO world VALUES (null, 'korea', 'seoul');");
        db.execSQL("INSERT INTO world VALUES (null, 'frnace', 'paris');");
        db.execSQL("INSERT INTO world VALUES (null, 'japen', 'tokyo');");

        Toast.makeText(context, "데이터베이스와 테이블 생성됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS world");
        onCreate(db);
    }
}
