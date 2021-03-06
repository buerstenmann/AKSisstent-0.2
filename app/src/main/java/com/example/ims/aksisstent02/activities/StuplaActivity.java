package com.example.ims.aksisstent02.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ims.aksisstent02.R;
import com.example.ims.aksisstent02.objects.Lessons;
import com.example.ims.aksisstent02.objects.Timetable;
import com.example.ims.aksisstent02.services.TimetableDAO;

import java.util.List;

public class StuplaActivity extends AppCompatActivity {
    private String className;

    TextView[] monTextView = new TextView[12];
    TextView[] tueTextView = new TextView[12];
    TextView[] wenTextView = new TextView[12];
    TextView[] thuTextView = new TextView[12];
    TextView[] friTextView = new TextView[12];

    Button btnDay;
    TextView viewDetailsSubject;
    TextView viewDetailsTeacher;
    TextView viewDetailsRoom;

    Timetable tt;

    public StuplaActivity() {
        System.out.println("guggibuu");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stupla);

        Intent intent = getIntent();
        className = intent.getStringExtra(Intent.EXTRA_TEXT);

        int[] monId = {R.id.mon1, R.id.mon2, R.id.mon3, R.id.mon4, R.id.mon5, R.id.mon6, R.id.mon7, R.id.mon8, R.id.mon9, R.id.mon10, R.id.mon11, R.id.mon12};
        int[] tueId = {R.id.tue1, R.id.tue2, R.id.tue3, R.id.tue4, R.id.tue5, R.id.tue6, R.id.tue7, R.id.tue8, R.id.tue9, R.id.tue10, R.id.tue11, R.id.tue12};
        int[] wenId = {R.id.wen1, R.id.wen2, R.id.wen3, R.id.wen4, R.id.wen5, R.id.wen6, R.id.wen7, R.id.wen8, R.id.wen9, R.id.wen10, R.id.wen11, R.id.wen12};
        int[] thuId = {R.id.thu1, R.id.thu2, R.id.thu3, R.id.thu4, R.id.thu5, R.id.thu6, R.id.thu7, R.id.thu8, R.id.thu9, R.id.thu10, R.id.thu11, R.id.thu12};
        int[] friId = {R.id.fri1, R.id.fri2, R.id.fri3, R.id.fri4, R.id.fri5, R.id.fri6, R.id.fri7, R.id.fri8, R.id.fri9, R.id.fri10, R.id.fri11, R.id.fri12};
        System.out.println(monId);

        btnDay = (Button) findViewById(R.id.btnDay);
        viewDetailsSubject = (TextView) findViewById(R.id.viewLessonSubject);
        viewDetailsTeacher = (TextView) findViewById(R.id.viewLessonTeacher);
        viewDetailsRoom = (TextView) findViewById(R.id.viewLessonRoom);

        for (int i = 0; i < 12; i++) {
            monTextView[i] = (TextView) findViewById(monId[i]);
            tueTextView[i] = (TextView) findViewById(tueId[i]);
            wenTextView[i] = (TextView) findViewById(wenId[i]);
            thuTextView[i] = (TextView) findViewById(thuId[i]);
            friTextView[i] = (TextView) findViewById(friId[i]);
        }

        tt = loadTt(className);


        btnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StuplaActivity.this, StuplaTagActivity.class)); //open Tagesansicht

            }
        });
        for (int i = 0; i < 12; i++) {
            if (monTextView[i].getText().toString() != "") {
                final int j = i;
                monTextView[i].setOnClickListener(new View.OnClickListener() {

                                                      @Override
                                                      public void onClick(View v) {
//                                                          viewDetails.getBackground().setAlpha(255);
                                                          viewDetailsSubject.setText(tt.getLessonsMon().get(j).getSubject());
                                                          viewDetailsTeacher.setText(tt.getLessonsMon().get(j).getTeacher());
                                                          viewDetailsRoom.setText(tt.getLessonsMon().get(j).getRoom());
                                                      }
                                                  }
                );
            }

            if (tueTextView[i].getText().toString() != "") {
                final int j = i;
                tueTextView[i].setOnClickListener(new View.OnClickListener() {

                                                      @Override
                                                      public void onClick(View v) {
//                                                          viewDetails.getBackground().setAlpha(255);
                                                          viewDetailsSubject.setText(tt.getLessonsTue().get(j).getSubject());
                                                          viewDetailsTeacher.setText(tt.getLessonsTue().get(j).getTeacher());
                                                          viewDetailsRoom.setText(tt.getLessonsTue().get(j).getRoom());
                                                      }
                                                  }
                );
            }

            if (wenTextView[i].getText().toString() != "") {
                final int j = i;
                wenTextView[i].setOnClickListener(new View.OnClickListener() {

                                                      @Override
                                                      public void onClick(View v) {
//                                                          viewDetails.getBackground().setAlpha(255);
                                                          viewDetailsSubject.setText(tt.getLessonsWen().get(j).getSubject());
                                                          viewDetailsTeacher.setText(tt.getLessonsWen().get(j).getTeacher());
                                                          viewDetailsRoom.setText(tt.getLessonsWen().get(j).getRoom());
                                                      }
                                                  }
                );
            }

            if (thuTextView[i].getText().toString() != "") {
                final int j = i;
                thuTextView[i].setOnClickListener(new View.OnClickListener() {

                                                      @Override
                                                      public void onClick(View v) {
//                                                          viewDetails.getBackground().setAlpha(255);
                                                          viewDetailsSubject.setText(tt.getLessonsThu().get(j).getSubject());
                                                          viewDetailsTeacher.setText(tt.getLessonsThu().get(j).getTeacher());
                                                          viewDetailsRoom.setText(tt.getLessonsThu().get(j).getRoom());
                                                      }
                                                  }
                );
            }

            if (friTextView[i].getText().toString() != "") {
                final int j = i;
                friTextView[i].setOnClickListener(new View.OnClickListener() {

                                                      @Override
                                                      public void onClick(View v) {
//                                                          viewDetails.getBackground().setAlpha(255);
                                                          viewDetailsSubject.setText(tt.getLessonsFri().get(j).getSubject());
                                                          viewDetailsTeacher.setText(tt.getLessonsFri().get(j).getTeacher());
                                                          viewDetailsRoom.setText(tt.getLessonsFri().get(j).getRoom());
                                                      }
                                                  }
                );
            }
        }
    }


    public Timetable loadTt(String className) {
        Timetable timetable;
        TimetableDAO timeDao = new TimetableDAO();
        timetable = timeDao.getTimetable(className, MenuActivity.menuContext);

        List<Lessons> lessonMon = timetable.getLessonsMon();
        List<Lessons> lessonTue = timetable.getLessonsTue();
        List<Lessons> lessonWen = timetable.getLessonsWen();
        List<Lessons> lessonThu = timetable.getLessonsThu();
        List<Lessons> lessonFri = timetable.getLessonsFri();

        for (int i = 0; i < 12; i++) {
            monTextView[i].setText(getSafeSubject(lessonMon, i));
            tueTextView[i].setText(getSafeSubject(lessonTue, i));
            wenTextView[i].setText(getSafeSubject(lessonWen, i));
            thuTextView[i].setText(getSafeSubject(lessonThu, i));
            friTextView[i].setText(getSafeSubject(lessonFri, i));

        }
        return timetable;
    }

    private String getSafeSubject(List<Lessons> lesson, int index) {
        if (lesson.size() > index) {
            if (lesson.get(index) != null) {
                return lesson.get(index).getSubject();
            }
        }
        return "";
    }
}
