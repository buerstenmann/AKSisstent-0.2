package com.example.ims.aksisstent02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DataActivity extends AppCompatActivity {
    private String teacher;
    Button btnBack;
    TextView viewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        btnBack = (Button) findViewById(R.id.btnZuruck);
        viewName = (TextView) findViewById(R.id.viewName);
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

    public void toast(String output) {
        //  for (int i = 1; i < 9; i++) {
        if (output != null) {

            Toast toast = Toast.makeText(this, output, Toast.LENGTH_SHORT);
            toast.show();
            //}
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "nope", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
