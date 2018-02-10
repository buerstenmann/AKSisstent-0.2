package com.example.ims.aksisstent02.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ims.aksisstent02.R;
import com.example.ims.aksisstent02.objects.Room;
import com.example.ims.aksisstent02.objects.Teacher;
import com.example.ims.aksisstent02.services.DataHolder;
import com.example.ims.aksisstent02.services.InputValidation;
import com.example.ims.aksisstent02.services.Search;

import java.util.List;

public class MenuActivity extends AppCompatActivity {
    static public Context menuContext;

    public String searchQuery;

    private Teacher searchResultTeacher;
    private Room searchResultRoom;

    private List<Teacher> teacherList;
    private List<Room> roomList;

    EditText editSuche;
    Button btnEnter;
    Button btnNoten;
    Button btnStupla;
    Button btnSettings;
    TextView viewLektion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuContext = this;

        editSuche = (EditText) findViewById(R.id.editSuche);
        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnNoten = (Button) findViewById(R.id.btnNoten);
        btnStupla = (Button) findViewById(R.id.btnStupla);
        btnSettings = (Button) findViewById(R.id.btnSettings);
        viewLektion = (TextView) findViewById(R.id.viewLektion);

        editSuche.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                    findSearchResult();
                    return true;
                } else
                    return false;
            }
        });

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findSearchResult();
            }
        });

        btnNoten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, NotenActivity.class));
            }
        });

        btnStupla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), StuplaActivity.class);
                startActivity(intent);
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, SettingsActivity.class));
            }
        });
    }

    public void findSearchResult() {
        InputValidation alphaInputValidator = new InputValidation();
        Search alphaSearch = new Search();

        searchQuery = alphaInputValidator.validateText(editSuche.getText().toString()); //InputValidator bereinigt die Suchanfrage. Es werden je nach Anfrage nur due Raumnummer oder Nachname zurückgegeben

        if (TextUtils.isDigitsOnly(searchQuery) == true) {
            searchResultRoom = alphaSearch.findRoom(searchQuery);
        } else {
            searchResultTeacher = alphaSearch.findTeacher(searchQuery);
        }

        if (searchResultTeacher != null) {
            Intent intent = new Intent(getBaseContext(), DataTeacherActivity.class);
            DataHolder.getInstance().setTeacher(searchResultTeacher);
            startActivity(intent);
        } else if (searchResultRoom != null) {
            Intent intentRoom = new Intent(getBaseContext(), DataRoomActivity.class);
            DataHolder.getInstance().setRoom(searchResultRoom);
            startActivity(intentRoom);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Kein Suchresultat gefunden", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}