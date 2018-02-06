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
import com.example.ims.aksisstent02.objects.User;
import com.example.ims.aksisstent02.services.FileMaker;
import com.example.ims.aksisstent02.services.XStreamer;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    static public Context mainContext;

    private String entryName;
    private String entryClass;

    private String outputName = "Name";
    private String outputClass = "Klasse";

    EditText etName;
    Button btnEnter;
    EditText etClass;

    User user;
    XStreamer streamer;
    FileMaker fileMaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContext = this;

        btnEnter = (Button) findViewById(R.id.btnLogin);     //Defines Button
        etName = (EditText) findViewById(R.id.editName);    //Defines EditTextes
        etClass = (EditText) findViewById(R.id.editKlasse);

        user = new User();
        streamer = new XStreamer();
        fileMaker = new FileMaker();

        user.setName("");
        user.setKlasse("");
        try {
            User usert = streamer.fromXmlUser(fileMaker.getTimetableFromFile(mainContext, "user"));
            user.setKlasse(usert.getKlasse());
            user.setName(usert.getName());
        } catch (Exception e) {

        }
        if (user.getName().trim().length() > 0 & user.getKlasse().trim().length() > 0) {
            outputName = user.getName();
            outputClass = user.getKlasse();
        }
        etName.setText(outputName);
        etClass.setText(outputClass);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                entryName = etName.getText().toString();
                entryClass = etClass.getText().toString();
                user.setName(entryName);
                user.setKlasse(entryClass);

                if (entryName.trim().length() > 0 & entryClass.trim().length() > 0) {  //entryName != "Name" & entryClass !="Klasse"    muss später bei Veröfentlichung hinzugefügt werden. Auskommentiert um einfacher testen zu können
                    try {
                        startActivity(new Intent(MainActivity.this, MenuActivity.class)); //open Menu Acitvity
                        File file = new File("user");
                        if (file.exists()) {
                            System.out.println("File exists");
                        } else {
                            System.out.println("File doesn't existz");
                            fileMaker.stringToDom(streamer.toXmlUser(user), "user", mainContext);
                        }


                    } catch (Exception e) {
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Bitte geben Sie etwas ein", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }


}



