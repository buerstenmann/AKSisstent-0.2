package com.example.ims.aksisstent02.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.example.ims.aksisstent02.services.TeacherSearch;
import com.example.ims.aksisstent02.services.TeachersDAO;
import com.example.ims.aksisstent02.services.TimetableDAO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class MenuActivity extends AppCompatActivity {
    public String searchQuery;
    @Getter
    @Setter
    public Teacher searchResultTeacher;
    private Room searchResultRoom;
    private List<Teacher> teacherList;
    private List<String> roomList;
    static public Context menuContext;
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

        menuContext = this;
        TeachersDAO alphaTeacherDAO = new TeachersDAO(menuContext);
//        RoomDAO alphaRoomDAO = new RoomDAO(menuContext);
        teacherList = alphaTeacherDAO.doXML();
        System.out.println(teacherList);
//        roomList = alphaRoomDAO.doXML();

        TimetableDAO timetableDownloadTt = new TimetableDAO(); //Generierung der Stundenpläne TODO Noah HTML parser
        timetableDownloadTt.downloadTtKlasse(menuContext);
        timetableDownloadTt.downloadTt(teacherList, menuContext);

        System.out.println("\nFile Directory.... ");            //Test der Abspeicherung von Stunenpläne
        String[] files = menuContext.getFilesDir().list();
        if (files == null) {
            Log.e("Speicherfehler", "Stundenplan files nicht generiert");
        }
        for (int i = 0; i < files.length; i++) {
            System.out.println("\nFile: " + files[i]);
        }


        System.out.println("----------------------MenuActivity------------------------");
        begrussung = (TextView) findViewById(R.id.viewBegrussung);
        suche = (EditText) findViewById(R.id.editSuche);
        enter = (Button) findViewById(R.id.btnEnter);
        noten = (Button) findViewById(R.id.btnNoten);
        stupla = (Button) findViewById(R.id.btnStupla);
        prufung = (Button) findViewById(R.id.btnTests);

        //TODO Noah Look up onKeyListener

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findTeacher();
            }
        });

        noten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, NotenActivity.class));

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
                startActivity(new Intent(MenuActivity.this, PrufungActivity.class));

            }
        });
    }

    public void findTeacher() {
        InputValidation alphaInputValidator = new InputValidation();
        searchQuery = alphaInputValidator.validateText(suche.getText().toString());

        // if (TextUtils.isDigitsOnly(searchQuery) == true) {
        //  RoomSearch Alpha = new RoomSearch();
        //searchResultRoom = Alpha.doSearch(searchQuery, roomList);
        // } else {
        TeacherSearch Alpha = new TeacherSearch();
        searchResultTeacher = Alpha.doSearch(searchQuery, teacherList);
        // }

        if (searchResultTeacher != null) {
            Intent intent = new Intent(getBaseContext(), DataTeacherActivity.class);
            DataHolder.getInstance().setTeacher(searchResultTeacher);
//            XStream xstream = new XStream();
//            String target = xstream.toXML(searchResultTeacher);
//            String target2 = xstream.toXML(menuContext);
//            intent.putExtra("resultTeacherAsString", target);
//            intent.putExtra("menuActivityContext", target2);
            startActivity(intent);
        } else if (searchResultRoom != null) {
//            Intent intent = new Intent(getBaseContext(), DataRoomActivity.class);
//            XStream xstream = new XStream();
//            String target = xstream.toXML(searchResultRoom);
//            String target2 = xstream.toXML(menuContext);
//            intent.putExtra("resultRoomAsString", target);
//            intent.putExtra("menuActivityContext", target2);
//            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Kein Suchresultat gefunden", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}






