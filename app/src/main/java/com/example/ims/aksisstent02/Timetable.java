package com.example.ims.aksisstent02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noah on 29.12.2017.
 */

public class Timetable {
    private List<Lessons> lessonsMon = new ArrayList<Lessons>();
    private List<Lessons> lessonsTue = new ArrayList<Lessons>();
    private List<Lessons> lessonsWen = new ArrayList<Lessons>();
    private List<Lessons> lessonsThu = new ArrayList<Lessons>();
    private List<Lessons> lessonsFri = new ArrayList<Lessons>();
    private String klasse;

    Timetable() {
        lessonsMon = null;
        lessonsTue = null;
        lessonsWen = null;
        lessonsThu = null;
        lessonsFri = null;
        klasse = null;
    }

    Timetable(List t_lessonsMon, List t_lessonsTue, List t_lessonsWen, List t_lessonsThu, List t_lessonsFri, String t_klasse) {
        lessonsMon = t_lessonsMon;
        lessonsTue = t_lessonsTue;
        lessonsWen = t_lessonsWen;
        lessonsThu = t_lessonsThu;
        lessonsFri = t_lessonsFri;
        klasse = t_klasse;
    }

    public void setLessonsMon(List t_lesson) {
        lessonsMon = t_lesson;
    }

    public List getLessonsMon() {
        return lessonsMon;
    }

    public void setLessonsTue(List t_lesson) {
        lessonsTue = t_lesson;
    }

    public List getLessonsTue() {
        return lessonsTue;
    }

    public void setLessonsWen(List t_lesson) {
        lessonsWen = t_lesson;
    }

    public List getLessonsWen() {
        return lessonsWen;
    }

    public void setLessonsThu(List t_lesson) {
        lessonsThu = t_lesson;
    }

    public List getLessonsThu() {
        return lessonsThu;
    }

    public void setLessonsFri(List t_lesson) {
        lessonsFri = t_lesson;
    }

    public List getLessonsFri() {
        return lessonsFri;
    }

    public void setKlasse(String t_Klasse) {
        klasse = t_Klasse;
    }

    public String getKlasse() {
        return klasse;
    }
}
