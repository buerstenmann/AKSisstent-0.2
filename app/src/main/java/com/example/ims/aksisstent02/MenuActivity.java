package com.example.ims.aksisstent02;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MenuActivity extends AppCompatActivity {
    public String searchQuery;
    private String searchResult;
    private Boolean searchState;
    private List<String> teacherList;
    private List<String> roomList;

    EditText suche;
    Button enter;
    Button noten;
    Button stupla;
    Button prufung;
    TextView begrussung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        TeachersDAO AlphaTeacherDAO = new TeachersDAO(this);
        teacherList = AlphaTeacherDAO.doXML();
        RoomDAO Beta = new RoomDAO(this);
        roomList = Beta.doXML();

        System.out.println("----------------------MenuActivity------------------------");
        System.out.println(teacherList);
        System.out.println(roomList);
        begrussung = (TextView) findViewById(R.id.viewBegrussung);
        suche = (EditText) findViewById(R.id.editSuche);
        enter = (Button) findViewById(R.id.btnEnter);
        noten = (Button) findViewById(R.id.btnNoten);
        stupla = (Button) findViewById(R.id.btnStupla);
        prufung = (Button) findViewById(R.id.btnTests);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputValidation Charlie = new InputValidation();
                searchQuery = Charlie.validateText(suche.getText().toString());

                if (TextUtils.isDigitsOnly(searchQuery) == true) {
                    RoomSearch Alpha = new RoomSearch();
                    searchResult = Alpha.doSearch(searchQuery, roomList);
                } else {
                    TeacherSearch Alpha = new TeacherSearch();
                    searchResult = Alpha.doSearch(searchQuery, teacherList);
                }

                if (searchResult != null) {
                    startActivity(new Intent(MenuActivity.this, DataActivity.class));
                    DataActivity Beta = new DataActivity();
                    Beta.setTeacher(searchResult);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Kein Suchresultat gefunden", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        noten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MenuActivity.this, NotenActivity.class));
                Timetable Alpha = new Timetable(false);
                Alpha.downloadTT();
            }
        });

        stupla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MenuActivity.this, StuplaActivity.class));
                Timetable Alpha = new Timetable();
            }
        });
        prufung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MenuActivity.this, PrufungActivity.class));
                Timetable Alpha = new Timetable();
                Alpha.test();
            }
        });
    }
}




