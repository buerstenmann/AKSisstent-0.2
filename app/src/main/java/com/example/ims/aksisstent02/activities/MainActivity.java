package com.example.ims.aksisstent02.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ims.aksisstent02.R;
import com.example.ims.aksisstent02.objects.Klasse;
import com.example.ims.aksisstent02.objects.User;
import com.example.ims.aksisstent02.services.FileMaker;
import com.example.ims.aksisstent02.services.KlassenDAO;
import com.example.ims.aksisstent02.services.XStreamer;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    static public Context mainContext;      //mainContext wird benötigt um auf das file für den User zuzugreifen

    private String inputName;
    private String inputClass;

    private String outputName = "Name";
    private String outputClass = "Klasse";

    EditText editName;
    EditText editClass;
    Button btnEnter;

    User user;
    XStreamer streamer;
    FileMaker fileMaker;
    Class goToClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContext = this;

        user = new User();
        streamer = new XStreamer();
        fileMaker = new FileMaker();

        btnEnter = (Button) findViewById(R.id.btnLogin);     //Defines Button
        editName = (EditText) findViewById(R.id.editName);    //Defines EditTextes
        editClass = (EditText) findViewById(R.id.editKlasse);

        editName.setText("Name");
        editClass.setText("Klasse");
        user.setName("");
        user.setKlasse("");
        user.setLastUpdate(null);
        goToClass = SettingsActivity.class;

        try {
            user = streamer.fromXmlUser(fileMaker.getTimetableFromFile(mainContext, "user"));
        } catch (Exception e) {

        }

        if (user.getName().trim().length() > 0 & user.getKlasse().trim().length() > 0) {
            outputName = user.getName();
            outputClass = user.getKlasse();
        }
        if (user.getLastUpdate() != null) {
            goToClass = MenuActivity.class;
            System.out.println("ToMenuactivity");
        }

        editName.setText(outputName);
        editClass.setText(outputClass);

        editName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (editName.getText().toString().equals("Name")) {
                        editName.setText("", TextView.BufferType.EDITABLE);
                    }
                }
            }
        });

        editClass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (editClass.getText().toString().equals("Klasse")) {
                        editClass.setText("", TextView.BufferType.EDITABLE);
                    }
                }
            }
        });

        editClass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                    editClass.requestFocus();
                    return true;
                } else
                    return false;
            }
        });

        editClass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                    doLogin(goToClass);
                    return true;
                } else
                    return false;
            }
        });

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin(goToClass);
            }
        });


    }

    public void doLogin(Class goToClass) {
        inputName = editName.getText().toString();
        inputClass = editClass.getText().toString();

        if (inputName.trim().length() > 0 & inputClass.trim().length() > 0) {
            if (inputName != "Name" & testClass(inputClass)) {
                user.setName(inputName);
                user.setKlasse(inputClass);
                try {
                    startActivity(new Intent(MainActivity.this, goToClass)); //open Menu Acitvity
                    fileMaker.stringToDom(streamer.toXmlUser(user), "user", mainContext);
                } catch (Exception e) {
                }
            } else {
                postToast("Bitte geben Sie eine gültige Klasse ein");
            }
        } else {
            postToast("Bitte geben Sie etwas ein");
        }
    }

    public boolean testClass(String entryClass) {
        KlassenDAO klassenDAO = new KlassenDAO(mainContext);
        List<Klasse> klassenList = klassenDAO.doXML();
        boolean returnBool = false;

        for (int i = 0; i < klassenList.size(); i++) {
            if (entryClass.toLowerCase().equals(klassenList.get(i).getKlasseName().toLowerCase())) {
                System.out.println(entryClass + "----------" + klassenList.get(i).getKlasseName().toLowerCase() + "----" + i);
                returnBool = true;
            } else {
                System.out.println("nyet " + klassenList.get(i).getKlasseName().toLowerCase());
            }
        }
        return returnBool;
    }

    public void postToast(String outputToast) {
        Toast toast = Toast.makeText(getApplicationContext(), outputToast, Toast.LENGTH_SHORT);
        toast.show();
    }
}




