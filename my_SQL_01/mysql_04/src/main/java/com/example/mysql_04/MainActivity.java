package com.example.mysql_04;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static  final int RQCODE_INSERT = 1;
    private static final int RQCODE_UPDEAT = 2;

    private static final int MENU_INSULT = Menu.FIRST;
    private static final int MENU_UPDATE = Menu.FIRST+1;
    private static final int MENU_DELETE = Menu.FIRST+2;

    MySQLiteOpenHelper mydb;
    SQLiteDatabase mdb;
    ListView lv;
    Cursor cursor;
    SimpleCursorAdapter ca;

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String strCol[] = {"country", "capital"};

        mydb = new MySQLiteOpenHelper(this, "mydb.db", null, 1);
        mdb = mydb.getWritableDatabase();
        cursor = mdb.rawQuery("SELECT * FROM world", null);

        lv = findViewById(R.id.list);
        ca = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, strCol, new int[]{android.R.id.text2, android.R.id.text2}, 1);

        lv.setAdapter(ca);

        lv.setOnItemClickListener(this);

        registerForContextMenu(lv);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, JitemActivity.class);
        cursor.moveToPosition(position);
        this.position = position;
        String country = cursor.getString(1);
        String capital = cursor.getString(2);
        int _id = cursor.getInt(0);

        i.putExtra("country", country);
        i.putExtra("capital", capital);
        i.putExtra("_id", (int)id);

        startActivityForResult(i, RQCODE_UPDEAT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            if(requestCode == RQCODE_UPDEAT){
//            cursor.moveToPosition(position);
//            int id = cursor.getInt(0);
//        int id = Integer.valueOf(update_et1.getText().toString());
                mdb.execSQL("UPDATE world SET country='"+data.getStringExtra("country") +"', capital='"+data.getStringExtra("capital") +"' WHERE _id = "+data.getExtras().getInt("_id")+";");
                cursor = mdb.rawQuery("SELECT * FROM world", null);
                Cursor old = ca.swapCursor(cursor);
                Toast.makeText(this, "레코드 수정", Toast.LENGTH_SHORT).show();
            }
            else if(requestCode == RQCODE_INSERT){
                mdb.execSQL("INSERT INTO world VALUES (null, '"+data.getStringExtra("country") +"', '"+data.getStringExtra("capital") +"');");
                cursor = mdb.rawQuery("SELECT * FROM world", null);
                Cursor old = ca.swapCursor(cursor);
                old.close();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.equals(lv)){
            menu.add(Menu.NONE, MENU_UPDATE, Menu.NONE, "update");
            menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "delete");
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        int position = info.position;

        cursor.moveToPosition(position);

        int id = cursor.getInt(0);
        String country = cursor.getString(1);
        String capital = cursor.getString(2);

        Intent i = new Intent(this, JitemActivity.class);
        i.putExtra("country", country);
        i.putExtra("capital", capital);
        i.putExtra("_id", (int)id);


        switch (item.getItemId()){
            case MENU_UPDATE:
                startActivityForResult(i, RQCODE_UPDEAT);
                break;
            case MENU_DELETE:
                mdb.execSQL("DELETE FROM world WHERE _id = " +id +";" );
                cursor = mdb.rawQuery("SELECT * FROM world", null);
                Cursor old = ca.swapCursor(cursor);
                old.close();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, MENU_INSULT, Menu.NONE, "insert");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case MENU_INSULT:
                Intent i =  new Intent(this,JitemActivity.class);
                i.putExtra("country", "");
                i.putExtra("capital", "");
                startActivityForResult(i, RQCODE_INSERT);
        }
        return super.onOptionsItemSelected(item);
    }
}