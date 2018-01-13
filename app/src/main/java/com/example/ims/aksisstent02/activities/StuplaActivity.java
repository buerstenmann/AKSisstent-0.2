package com.example.ims.aksisstent02.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ims.aksisstent02.R;

public class StuplaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stupla);
        Button btnBack = (Button) findViewById(R.id.btnZuruck);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StuplaActivity.this, MenuActivity.class)); //open Data Acitvity

            }
        });
    }
}
