package com.example.ims.aksisstent02.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ims.aksisstent02.R;


public class MainActivity extends AppCompatActivity {
    String entryName;
    String entryClass;

    EditText etName;
    Button btnEnter;
    EditText etClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnter = (Button) findViewById(R.id.btnLogin);     //Defines Button
        etName = (EditText) findViewById(R.id.editName);    //Defines EditTextes
        etClass = (EditText) findViewById(R.id.editKlasse);

        Context context;

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                entryName = etName.getText().toString();
                entryClass = etClass.getText().toString();


                if (entryName.trim().length() > 0 & entryClass.trim().length() > 0) {  //entryName != "Name" & entryClass !="Klasse"    muss später bei Veröfentlichung hinzugefügt werden. Auskommentiert um einfacher testen zu können
                    startActivity(new Intent(MainActivity.this, MenuActivity.class)); //open Menu Acitvity
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Bitte geben Sie etwas ein", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}



