package com.example.mysql_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JitemActivity extends AppCompatActivity {
    private static  final int RQCODE_INSERT = 1;
    private static final int RQCODE_UPDEAT = 2;

    String country;
    String capital;
    EditText et_country;
    EditText et_capital;
    Button btn;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        et_capital = findViewById(R.id.et_capital);
        et_country = findViewById(R.id.et_country);
        btn = findViewById(R.id.button);

        Intent intent = getIntent();
        country = intent.getExtras().getString("country", "");
        capital = intent.getExtras().getString("capital", "");
        id = intent.getExtras().getInt("_id");

        et_capital.setText(capital);
        et_country.setText(country);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                i.putExtra("country", et_country.getText().toString());
                i.putExtra("capital", et_capital.getText().toString());
                i.putExtra("_id", id);
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }
}