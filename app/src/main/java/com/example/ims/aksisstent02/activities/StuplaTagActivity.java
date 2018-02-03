package com.example.ims.aksisstent02.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ims.aksisstent02.R;
import com.example.ims.aksisstent02.objects.Lessons;
import com.example.ims.aksisstent02.objects.Timetable;
import com.example.ims.aksisstent02.services.TimetableDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StuplaTagActivity extends AppCompatActivity {
    Timetable tt;

    TextView[] viewSubject;
    TextView[] viewTeacher;
    TextView[] viewRoom;

    int[] idSubject = {R.id.subject1, R.id.subject2, R.id.subject3, R.id.subject4, R.id.subject5, R.id.subject6, R.id.subject7, R.id.subject8, R.id.subject9, R.id.subject10, R.id.subject11, R.id.subject12};
    int[] idTeacher = {R.id.teacher1, R.id.teacher2, R.id.teacher3, R.id.teacher4, R.id.teacher5, R.id.teacher6, R.id.teacher7, R.id.teacher8, R.id.teacher9, R.id.teacher10, R.id.teacher11, R.id.teacher12};
    int[] idRoom = {R.id.room1, R.id.room2, R.id.room3, R.id.room4, R.id.room5, R.id.room6, R.id.room7, R.id.room8, R.id.room9, R.id.room10, R.id.room11, R.id.room12};

    Button btnWeek;
    Button btnMon;
    Button btnTue;
    Button btnWen;
    Button btnThu;
    Button btnFri;

    String klassenName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stupla_tag);

        viewSubject = new TextView[12];
        viewTeacher = new TextView[12];
        viewRoom = new TextView[12];

        btnWeek = (Button) findViewById(R.id.btnWeek);
        btnMon = (Button) findViewById(R.id.btnMon);
        btnTue = (Button) findViewById(R.id.btnTue);
        btnWen = (Button) findViewById(R.id.btnWen);
        btnThu = (Button) findViewById(R.id.btnThu);
        btnFri = (Button) findViewById(R.id.btnFri);


        for (int i = 0; i < 12; i++) {
            viewSubject[i] = (TextView) findViewById(idSubject[i]);
            viewTeacher[i] = (TextView) findViewById(idTeacher[i]);
            viewRoom[i] = (TextView) findViewById(idRoom[i]);
        }

        TimetableDAO ttDao = new TimetableDAO();
        tt = ttDao.getTimetable("I3a", MenuActivity.menuContext);
        loadDay(tt, getCurrentDay());

        btnWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StuplaTagActivity.this, StuplaActivity.class)); //open Tagesansicht
            }
        });

        btnMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDay(tt, "Montag");

            }
        });
        btnTue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDay(tt, "Dienstag");

            }
        });
        btnWen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDay(tt, "Mitwoch");

            }
        });
        btnThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDay(tt, "Donnerstag");

            }
        });
        btnFri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDay(tt, "Freitag");

            }
        });
    }

    public void loadDay(Timetable tt, String currentDay) {
        List<Lessons> ttLessons = new ArrayList<Lessons>();

        if (currentDay == "Montag") {
            ttLessons = tt.getLessonsMon();
        } else if (currentDay == "Dienstag") {
            ttLessons = tt.getLessonsTue();
        } else if (currentDay == "Mitwoch") {
            ttLessons = tt.getLessonsWen();
        } else if (currentDay == "Donnerstag") {
            ttLessons = tt.getLessonsThu();
        } else if (currentDay == "Freitag") {
            ttLessons = tt.getLessonsFri();
        } else if (currentDay == "Samstag") {
            ttLessons = tt.getLessonsMon();
        } else if (currentDay == "Sontag") {
            ttLessons = tt.getLessonsMon();
        } else {
            Lessons errorLesson = new Lessons("Fehler, Tag nicht gefunden", "", "");
            ttLessons.add(errorLesson);
            Log.i("T", "Fehler, Tag nicht gefunden");

        }

        for (int i = 0; i < ttLessons.size(); i++) {
            viewSubject[i].setText(ttLessons.get(i).getSubject());
            viewTeacher[i].setText(ttLessons.get(i).getTeacher());
            System.out.println(ttLessons.get(i).getRoom());
            System.out.println(i);
            viewRoom[i].setText(ttLessons.get(i).getRoom());
        }

        for (int i = ttLessons.size(); i < 12; i++) {
            viewSubject[i].setText("");
            viewTeacher[i].setText("");
            viewRoom[i].setText("");
        }
    }

    public String getCurrentDay() {
        String daysArray[] = {"Samstag", "Sontag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"};
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        return daysArray[day];

    }
}