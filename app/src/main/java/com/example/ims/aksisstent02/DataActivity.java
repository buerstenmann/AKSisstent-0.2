package com.example.ims.aksisstent02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {
    private String teacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Button btnBack = (Button) findViewById(R.id.btnZuruck);
        TextView viewName = (TextView) findViewById(R.id.viewName);
        viewName.setText(teacher);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataActivity.this, MenuActivity.class)); //open Data Acitvity

            }
        });


    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

}

