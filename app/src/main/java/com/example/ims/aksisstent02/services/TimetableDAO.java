package com.example.ims.aksisstent02.services;

import android.content.Context;

import com.example.ims.aksisstent02.objects.Klasse;
import com.example.ims.aksisstent02.objects.Lessons;
import com.example.ims.aksisstent02.objects.Room;
import com.example.ims.aksisstent02.objects.Teacher;
import com.example.ims.aksisstent02.objects.Timetable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noah on 18.01.2018.
 */

public class TimetableDAO {

    XStreamer streamer;
    FileMaker fileMaker;

    public void downloadTtRoom(List<Room> roomList, Context context) {
        System.out.println("Start TeacherTimetable Loop");
        for (int i = 0; i < roomList.size(); i++) {
            try {
                fileMaker.stringToDom(parseHTML(roomList.get(i).getRoomUrl()), roomList.get(i).getRoomNumber(), context);
            } catch (Exception E) {
            }
        }
    }

    public void downloadTtTeacher(List<Teacher> teacherList, Context context) {
        System.out.println("Start TeacherTimetable Loop");
        for (int i = 0; i < teacherList.size(); i++) {
            try {
                fileMaker.stringToDom(parseHTML(teacherList.get(i).getTeacherUrl()), teacherList.get(i).getTeacherName(), context);
            } catch (Exception E) {
            }
        }
    }

    public void downloadTtKlasse(List<Klasse> klassenList, Context context) {
        System.out.println("Start timetable loop");
        fileMaker = new FileMaker();
        streamer = new XStreamer();
        for (int i = 0; i < klassenList.size(); i++) {
            try {
                fileMaker.stringToDom(parseHTML(klassenList.get(i).getKlassenURL()), klassenList.get(i).getKlasseName(), context);
            } catch (Exception E) {
            }

        }

    }

    public String parseHTML(String url) {
        String returnXML;
        String[] input = {url, "", ""};
        TimetableParser timetableParser = new TimetableParser();

        fileMaker = new FileMaker();
        streamer = new XStreamer();

//        if ("I3a" == "I3a") {
//            System.out.println("parse html generate i3att-----------------------------------------------------------");
//            tt = getI3aTt();
//        } else {
//            for (int j = 0; j < 5; j++) {                                                           //loop Tage
//                //System.out.println("\nTimetable constructor loop 2:" + j + "\n");
//                List<Lessons> lessons = new ArrayList<Lessons>();
//                for (int k = 0; k < 6; k++) {                                                  //loop Stunden pro tag
//                    lessons.add(new Lessons(j + "Fach " + k, j + "2" + k, j + "teacher" + k));
//                }
//
//                if (j == 0) {
//                    tt.setLessonsMon(lessons);
//                } else if (j == 1) {
//                    tt.setLessonsTue(lessons);
//                } else if (j == 2) {
//                    tt.setLessonsWen(lessons);
//                } else if (j == 3) {
//                    tt.setLessonsThu(lessons);
//                } else if (j == 4) {
//                    tt.setLessonsFri(lessons);
//                }
//            }
//            tt.setKlassenname("Example");
//        }
//        returnXML = ;
        String returnString;
        try {
            returnString = streamer.toXmlTt(timetableParser.execute(input).get());
        } catch (Exception e) {
            returnString = "";
        }
        return returnString;
    }

    public Timetable getTimetable(String name, Context context) {
        fileMaker = new FileMaker();
        streamer = new XStreamer();
        String xmlTt = fileMaker.getTimetableFromFile(context, name);
        return streamer.fromXmlTt(xmlTt);
    }


    public void deleteTimetable(Timetable tt) {
        tt.setLessonsMon(null);
        tt.setLessonsTue(null);
        tt.setLessonsWen(null);
        tt.setLessonsThu(null);
        tt.setLessonsFri(null);
    }


    private Timetable getI3aTt() {
        Timetable tt = new Timetable();

        List<Lessons> lessonMon = new ArrayList<Lessons>();
        List<Lessons> lessonTue = new ArrayList<Lessons>();
        List<Lessons> lessonWen = new ArrayList<Lessons>();
        List<Lessons> lessonThu = new ArrayList<Lessons>();
        List<Lessons> lessonFri = new ArrayList<Lessons>();

        lessonMon.add(new Lessons("Informatik", "BBB", "Flick"));
        lessonMon.add(new Lessons("Informatik", "BBB", "Flick"));
        lessonMon.add(new Lessons("Informatik", "BBB", "Flick"));
        lessonMon.add(new Lessons("Informatik", "BBB", "Flick"));
        lessonMon.add(new Lessons("Informatik", "BBB", "Flick"));
        lessonMon.add(new Lessons("Mittag", "", ""));
        lessonMon.add(new Lessons("Mittag", "", ""));
        lessonMon.add(new Lessons("VBR", "17", "Schoohf"));
        lessonMon.add(new Lessons("VBR", "17", "Schoohf"));
        lessonMon.add(new Lessons("ABT", "17", "Schoohf"));

        lessonTue.add(new Lessons("", "", ""));
        lessonTue.add(new Lessons("FWR", "39", "Meier"));
        lessonTue.add(new Lessons("FWR", "39", "Meier"));
        lessonTue.add(new Lessons("Mathe", "214", "Sachs"));
        lessonTue.add(new Lessons("Franz", "27", "Schlatter"));
        lessonTue.add(new Lessons("Franz", "27", "Schlatter"));
        lessonTue.add(new Lessons("", "", ""));
        lessonTue.add(new Lessons("VBR", "26", "Schoohf"));
        lessonTue.add(new Lessons("VBR", "26", "Schoohf"));

        lessonWen.add(new Lessons("Informatik", "BBB", "Flick"));
        lessonWen.add(new Lessons("Informatik", "BBB", "Flick"));
        lessonWen.add(new Lessons("Informatik", "BBB", "Flick"));
        lessonWen.add(new Lessons("Informatik", "BBB", "Flick"));
        lessonWen.add(new Lessons("Informatik", "BBB", "Flick"));
        lessonWen.add(new Lessons("", "", ""));
        lessonWen.add(new Lessons("", "", ""));
        lessonWen.add(new Lessons("Englisch", "33", "Grünwald"));
        lessonWen.add(new Lessons("", "", ""));
        lessonWen.add(new Lessons("Sport", "Turnhalle", "Gross"));
        lessonWen.add(new Lessons("Sport", "Turnhalle", "Gross"));

        lessonThu.add(new Lessons("", "", ""));
        lessonThu.add(new Lessons("", "", ""));
        lessonThu.add(new Lessons("", "", ""));
        lessonThu.add(new Lessons("Englisch", "38", "Grünwald"));
        lessonThu.add(new Lessons("Deutsch", "518", "Engel"));

        lessonFri.add(new Lessons("Deutsch", "512", "Engel"));
        lessonFri.add(new Lessons("Deutsch", "512", "Engel"));
        lessonFri.add(new Lessons("Mathe", "214", "Sachs"));
        lessonFri.add(new Lessons("FWR", "39", "Meier"));
        lessonFri.add(new Lessons("VBR", "518", "Schoohf"));

        tt.setKlassenname("I3a");
        tt.setLessonsMon(lessonMon);
        tt.setLessonsTue(lessonTue);
        tt.setLessonsWen(lessonWen);
        tt.setLessonsThu(lessonThu);
        tt.setLessonsFri(lessonFri);
        System.out.println(tt);
        return tt;
    }
}