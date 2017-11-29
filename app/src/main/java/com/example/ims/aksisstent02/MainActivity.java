package com.example.ims.aksisstent02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    String entryName;
    String entryClass;

    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnter = (Button) findViewById(R.id.btnLogin);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etName = (EditText) findViewById(R.id.editName);
                EditText etClass = (EditText) findViewById(R.id.editKlasse);
                entryName = etName.getText().toString();
                entryClass = etClass.getText().toString();

                if (entryName != null & entryClass != null) {
                    startActivity(new Intent(MainActivity.this, MenuActivity.class)); //open Menu Acitvity
                }
            }
        });
    }
}



