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
    @Getter
    @Setter
    private Room searchResultRoom;
    private List<Teacher> teacherList;
    private List<String> roomList;
    static public Context menuContext;
    EditText editSuche;
    Button btnEnter;
    Button btnNoten;
    Button btnStupla;
    Button btnPrufung;
    TextView viewBegrussung;

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

        System.out.println("\nFile Directory.... MenuActivity");            //Test der Abspeicherung von Stunenpläne
        String[] files = menuContext.getFilesDir().list();
        if (files == null) {
            Log.e("Speicherfehler", "Stundenplan files nicht generiert");
        }
        for (int i = 0; i < files.length; i++) {
            System.out.println("\nFile: " + files[i]);
        }


        System.out.println("----------------------MenuActivity------------------------");
        viewBegrussung = (TextView) findViewById(R.id.viewBegrussung);
        editSuche = (EditText) findViewById(R.id.editSuche);
        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnNoten = (Button) findViewById(R.id.btnNoten);
        btnStupla = (Button) findViewById(R.id.btnStupla);
        btnPrufung = (Button) findViewById(R.id.btnTests);

        //TODO Noah Look up onKeyListener Suchfeld

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
                Intent intent = new Intent(getBaseContext(), StuplaActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, "I3a");
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
        searchQuery = alphaInputValidator.validateText(editSuche.getText().toString());

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
            startActivity(intent);
        } else if (searchResultRoom != null) {
//            Intent intent = new Intent(getBaseContext(), DataRoomActivity.class);
//             DataHolder.getInstance().setRoom(searchRoomTeacher);
//
// startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Kein Suchresultat gefunden", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}






