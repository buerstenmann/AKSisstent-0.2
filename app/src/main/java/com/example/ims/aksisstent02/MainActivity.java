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
    final EditText etName = (EditText) findViewById(R.id.editName);
    final EditText etClass = (EditText) findViewById(R.id.editKlasse);
    Button btnEnter = (Button) findViewById(R.id.btnLogin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entryName = etName.getText().toString();
                entryClass = etClass.getText().toString();

                if (entryName != null & entryClass != null) {
                    startActivity(new Intent(MainActivity.this, MenuActivity.class)); //open Menu Acitvity
                }
            }
        });
    }
}



