package com.example.ims.aksisstent02.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ims.aksisstent02.R;
import com.example.ims.aksisstent02.objects.Teacher;
import com.example.ims.aksisstent02.services.InputValidation;
import com.example.ims.aksisstent02.services.RoomDAO;
import com.example.ims.aksisstent02.services.TeacherSearch;
import com.example.ims.aksisstent02.services.TeachersDAO;
import com.example.ims.aksisstent02.services.TimetableDAO;
import com.google.gson.Gson;

import java.util.List;

public class MenuActivity extends AppCompatActivity {
    public String searchQuery;
    private Teacher searchResultTeacher;
    private Boolean searchState;
    private List<Teacher> teacherList;
    private List<String> roomList;
    public Context menuContext;
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
        TimetableDAO timetableDownloadTt = new TimetableDAO();
        timetableDownloadTt.downloadTtKlasse(this);

        menuContext = this;

        TeachersDAO AlphaTeacherDAO = new TeachersDAO(this);
        teacherList = AlphaTeacherDAO.doXML();
        timetableDownloadTt.downloadTt(teacherList, this);
        RoomDAO Beta = new RoomDAO(this);
        roomList = Beta.doXML();

        System.out.println("\nFile Directory.... ");
        String[] files = this.getFilesDir().list();
        for (int i = 0; i < files.length; i++)
            System.out.println("\nFile: " + files[i]);

        System.out.println("----------------------MenuActivity------------------------");
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

                // if (TextUtils.isDigitsOnly(searchQuery) == true) {
                //  RoomSearch Alpha = new RoomSearch();
                    //searchResult = Alpha.doSearch(searchQuery, roomList);
                // } else {
                    TeacherSearch Alpha = new TeacherSearch();
                    searchResultTeacher = Alpha.doSearch(searchQuery, teacherList);
                // }

                if (searchResultTeacher != null) {
                    Intent intent = new Intent(getBaseContext(), DataActivity.class);
                    Gson gS = new Gson();
                    String target = gS.toJson(searchResultTeacher); // Converts the object to a JSON String
                    String target2 = gS.toJson(menuContext);
                    intent.putExtra("resultTeacherAsString", target);
                    intent.putExtra("menuActivityContext", target2);
                    startActivity(intent);
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

            }
        });

        stupla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), StuplaActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, "I3a");
                startActivity(intent);
            }
        });
        prufung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MenuActivity.this, PrufungActivity.class));

            }
        });
    }

}




