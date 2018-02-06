package com.example.ims.aksisstent02.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ims.aksisstent02.R;
import com.example.ims.aksisstent02.objects.Klasse;
import com.example.ims.aksisstent02.objects.Room;
import com.example.ims.aksisstent02.objects.Teacher;
import com.example.ims.aksisstent02.objects.User;
import com.example.ims.aksisstent02.services.DataHolder;
import com.example.ims.aksisstent02.services.FileMaker;
import com.example.ims.aksisstent02.services.InputValidation;
import com.example.ims.aksisstent02.services.KlassenDAO;
import com.example.ims.aksisstent02.services.RoomDAO;
import com.example.ims.aksisstent02.services.TeacherSearch;
import com.example.ims.aksisstent02.services.TeachersDAO;
import com.example.ims.aksisstent02.services.TimetableDAO;
import com.example.ims.aksisstent02.services.XStreamer;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class MenuActivity extends AppCompatActivity {
    public String searchQuery;
    @Getter
    @Setter
    private Teacher searchResultTeacher;
    @Getter
    @Setter
    private Room searchResultRoom;
    private List<Teacher> teacherList;
    private List<Room> roomList;
    private List<Klasse> klasseList;
    static public Context menuContext;
    EditText editSuche;
    Button btnEnter;
    Button btnNoten;
    Button btnStupla;
    Button btnPrufung;
    TextView viewLektion;

    XStreamer streamer;
    FileMaker filemaker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuContext = this;

        streamer = new XStreamer();
        filemaker = new FileMaker();

        TeachersDAO alphaTeacherDAO = new TeachersDAO(menuContext);
        RoomDAO alphaRoomDAO = new RoomDAO(menuContext);
        KlassenDAO alphaKlassenDAO = new KlassenDAO(menuContext);
        TimetableDAO timetableDownloadTt = new TimetableDAO(); //Generierung der Stundenpläne TODO Noah HTML parser

        teacherList = alphaTeacherDAO.doXML();
        roomList = alphaRoomDAO.doXML();
        klasseList = alphaKlassenDAO.doXML();
        System.out.println(teacherList);

        timetableDownloadTt.downloadTtKlasse(klasseList, menuContext);
        timetableDownloadTt.downloadTtTeacher(teacherList, menuContext);
        timetableDownloadTt.downloadTtRoom(roomList, menuContext);

        System.out.println("\nFile Directory.... MenuActivity");            //Test der Abspeicherung von Stunenpläne
        String[] files = menuContext.getFilesDir().list();
        if (files == null) {
            Log.e("Speicherfehler", "Stundenplan files nicht generiert");
        }
        for (int i = 0; i < files.length; i++) {
            System.out.println("\nFile: " + files[i]);
        }


        System.out.println("----------------------MenuActivity------------------------");
        editSuche = (EditText) findViewById(R.id.editSuche);
        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnNoten = (Button) findViewById(R.id.btnNoten);
        btnStupla = (Button) findViewById(R.id.btnStupla);
        btnPrufung = (Button) findViewById(R.id.btnTests);
        viewLektion = (TextView) findViewById(R.id.viewLektion);

        //TODO Noah Look up onKeyListener Suchfeld
        viewLektion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewLektion.setText("Hallo Welt");
            }
        });


        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findTeacher();
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
                User user = streamer.fromXmlUser(filemaker.getTimetableFromFile(MainActivity.mainContext, "user"));

                Intent intent = new Intent(getBaseContext(), StuplaActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, user.getKlasse());
                startActivity(intent);
            }
        });
        btnPrufung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, PrufungActivity.class));

            }
        });
    }

    public void findTeacher() {
        InputValidation alphaInputValidator = new InputValidation();
        TeacherSearch alphaTeacherSearch = new TeacherSearch();
        searchQuery = alphaInputValidator.validateText(editSuche.getText().toString());

        if (TextUtils.isDigitsOnly(searchQuery) == true) {
            searchResultRoom = alphaTeacherSearch.findRoom(searchQuery, roomList);
        } else {
            searchResultTeacher = alphaTeacherSearch.findTeacher(searchQuery, teacherList);
        }

        if (searchResultTeacher != null) {
            System.out.println("Lehrer gefunden");
            Intent intent = new Intent(getBaseContext(), DataTeacherActivity.class);
            DataHolder.getInstance().setTeacher(searchResultTeacher);
            startActivity(intent);
        } else if (searchResultRoom != null) {
            System.out.println("Raum gefunden");
            Intent intentRoom = new Intent(getBaseContext(), DataRoomActivity.class);
            DataHolder.getInstance().setRoom(searchResultRoom);
            startActivity(intentRoom);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Kein Suchresultat gefunden", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}






