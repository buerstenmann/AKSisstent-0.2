package com.example.ims.aksisstent02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noah on 29.12.2017.
 */

public class TimetableList {

    Timetable[] timetableLists = null;
    private List<Lessons> lessonsMon = new ArrayList<Lessons>();
    private List<Lessons> lessonsTue = new ArrayList<Lessons>();
    private List<Lessons> lessonsWen = new ArrayList<Lessons>();
    private List<Lessons> lessonsThu = new ArrayList<Lessons>();
    private List<Lessons> lessonsFri = new ArrayList<Lessons>();

    TimetableList(int count, int count2) {
        timetableLists = new Timetable[count];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 4; i++) {
                List<Lessons> lessons = new ArrayList<Lessons>();
                for (int k = 0; k < count2; k++) {
                    lessons.add(new Lessons("Fach " + k, 2 + k, null, null));
                }

                if (j == 0) {
                    lessonsMon = lessons;
                } else if (j == 1) {
                    lessonsTue = lessons;
                } else if (j == 2) {
                    lessonsWen = lessons;
                } else if (j == 3) {
                    lessonsThu = lessons;
                } else if (j == 4) {
                    lessonsFri = lessons;
                }

            }
            timetableLists[i] = new Timetable(lessonsMon, lessonsTue, lessonsWen, lessonsThu, lessonsFri, "Stundenplan " + i);
            System.out.println(timetableLists[i]);
        }
    }
}
