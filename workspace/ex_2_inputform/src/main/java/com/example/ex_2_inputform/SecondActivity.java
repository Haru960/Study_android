package com.example.ex_2_inputform;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    //    ListView resultlv;
//    ArrayAdapter<Info> aa;
    ArrayList<Info> al;
    //    Button btn;
    TextView tv;
    Button call_btn;
    Button back_btn;
    Button re_btn;
    Button text_btn;
    Info info;
    int position;

    static final int REQCODE_PERMISSION_CALLPHONE = 1;
    static final int REQUEST_CODE_START_INPUT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv = findViewById(R.id.tv123);
        call_btn = findViewById(R.id.call_btn);
        back_btn = findViewById(R.id.back_btn);
        re_btn = findViewById(R.id.re_btn);
        text_btn = findViewById(R.id.text_btn);

        Intent intent = getIntent();
        info = intent.getParcelableExtra("stu_info");
        position = intent.getExtras().getInt("pos");
//        info = al.get(position);
        tv.setText(info.print_());

        back_btn.setOnClickListener(mListener);
        re_btn.setOnClickListener(mListener);
        call_btn.setOnClickListener(mListener);
        text_btn.setOnClickListener(mListener);


//        btn = findViewById(R.id.back_btn);
//
//        al = intent.getParcelableArrayListExtra("stu");
//
//        aa = new  MyAdapter( this , R.layout.row, R.id.tv, al);
//
//        resultlv = findViewById(R.id.resultlv1);
//
//        resultlv.setAdapter(aa);
//
//        btn.setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == call_btn.getId()) {
                if (ContextCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(SecondActivity.this, Manifest.permission.CALL_PHONE)) {
                        Toast.makeText(SecondActivity.this, "권한이 없습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    String str = al.get(position).id;
                    intent.setData(Uri.parse("tel:"+str));
                    startActivity(intent);
                }
            } else if (v.getId() == back_btn.getId()) {
                Intent intent = new Intent();
                intent.putExtra("stu_info", info);
                intent.putExtra("pos", position);
                setResult(RESULT_OK,intent);

                finish();
            } else if (v.getId() == re_btn.getId()) {
                Intent intent = new Intent(SecondActivity.this, DummyActivity.class);
                intent.putExtra("stu_info", info);
                intent.putExtra("pos", position);

                startActivityForResult(intent, REQUEST_CODE_START_INPUT);

            }
            else if(v.getId() == text_btn.getId()){

                Toast.makeText(SecondActivity.this, "눌림", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+info.id));
                startActivity(intent);
            }
        }
    };
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQCODE_PERMISSION_CALLPHONE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "다시 전화버튼 누르세요 (권한 얻음)", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_START_INPUT){
            if(resultCode == RESULT_OK){
                info = data.getParcelableExtra("stu_info");
//                info = al.get(position);
                tv.setText(info.print_());
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("stu_info", info);
        intent.putExtra("pos", position);
        setResult(RESULT_OK,intent);

        super.onBackPressed();
    }
}


//class MyAdapter extends ArrayAdapter<Info>{
//    LayoutInflater inflater;
//    ArrayList<Info> mItems;
//
//    public MyAdapter(@NonNull Context context, int resource, int textViewResourceId, ArrayList objects) {
//        super(context, resource, textViewResourceId, objects);
//        mItems = objects;
//        inflater = LayoutInflater.from(context);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View view = convertView;
//        if(view == null){
//            view = inflater.inflate(R.layout.row, null);
//        }
//        ImageView iv = (ImageView)view.findViewById(R.id.Listeitem_iv);
//        int nResId = R.drawable.hhhhhh;
//        if(mItems.get(position).class_.contains("주말반")){
//            nResId = R.drawable.sssss;
//        }
//        iv.setImageResource(nResId);
//        TextView tv = (TextView)view.findViewById(R.id.tv);
//        String str = mItems.get(position).print_(); // 여기 수정 필요
//        tv.setText(str);
//        return view;
//    }
//}

