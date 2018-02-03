package com.example.ims.aksisstent02.services;

import android.content.Context;
import android.util.Log;

import com.example.ims.aksisstent02.objects.Klasse;
import com.example.ims.aksisstent02.objects.Lessons;
import com.example.ims.aksisstent02.objects.Room;
import com.example.ims.aksisstent02.objects.Teacher;
import com.example.ims.aksisstent02.objects.Timetable;
import com.thoughtworks.xstream.XStream;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noah on 18.01.2018.
 */

public class TimetableDAO {
    public void downloadTtRoom(List<Room> roomList, Context context) {
        System.out.println("Start TeacherTimetable Loop");
        for (int i = 0; i < roomList.size(); i++) {
            try {
                stringToDom(parseHTML(roomList.get(i).getRoomNumber()), roomList.get(i).getRoomNumber(), context);
            } catch (Exception E) {
            }
        }
    }

    public void downloadTtTeacher(List<Teacher> teacherList, Context context) {
        System.out.println("Start TeacherTimetable Loop");
        for (int i = 0; i < teacherList.size(); i++) {
            try {
                stringToDom(parseHTML(teacherList.get(i).getTeacherName()), teacherList.get(i).getTeacherName(), context);
            } catch (Exception E) {
            }
        }
    }

    public void downloadTtKlasse(List<Klasse> klassenList, Context context) {
        System.out.println("Start timetable loop");
        for (int i = 0; i < klassenList.size(); i++) {
            try {
                stringToDom(parseHTML(klassenList.get(i).getKlasseName()), klassenList.get(i).getKlasseName(), context);
            } catch (Exception E) {
            }

        }

    }

    public String parseHTML(String tklasse) {
        String returnXML;
        Timetable tt = new Timetable();
        if ("I3a" == "I3a") {
            System.out.println("parse html generate i3att-----------------------------------------------------------");
            tt = getI3aTt();
        } else {
            for (int j = 0; j < 5; j++) {                                                           //loop Tage
                //System.out.println("\nTimetable constructor loop 2:" + j + "\n");
                List<Lessons> lessons = new ArrayList<Lessons>();
                for (int k = 0; k < 6; k++) {                                                  //loop Stunden pro tag
                    lessons.add(new Lessons(j + "Fach " + k, j + "2" + k, j + "teacher" + k));
                }

                if (j == 0) {
                    tt.setLessonsMon(lessons);
                } else if (j == 1) {
                    tt.setLessonsTue(lessons);
                } else if (j == 2) {
                    tt.setLessonsWen(lessons);
                } else if (j == 3) {
                    tt.setLessonsThu(lessons);
                } else if (j == 4) {
                    tt.setLessonsFri(lessons);
                }
            }
            tt.setKlassenname("Example");
        }
        returnXML = toXML(tt);
        deleteTimetable(tt);
        return returnXML;
    }

    public Timetable getTimetable(String name, Context context) {
        String xmlTt = getTimetableFromFile(context, name);
        return fromXML(xmlTt);
    }


    public void deleteTimetable(Timetable tt) {
        tt.setLessonsMon(null);
        tt.setLessonsTue(null);
        tt.setLessonsWen(null);
        tt.setLessonsThu(null);
        tt.setLessonsFri(null);
    }

    public static void stringToDom(String xmlSource, String name, Context context) throws IOException {
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(name, Context.MODE_PRIVATE);
            outputStream.write(xmlSource.getBytes("UTF-8"));
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTimetableFromFile(Context context, String file) {
        String returnString = "";
        String line;

        try {
            System.out.println(file + " file");
            System.out.println("\nFile Direct000000000ory.... ");

            FileInputStream fin = context.openFileInput(file);
            StringBuilder sb = new StringBuilder();

            InputStreamReader inputStream = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(inputStream);

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }

            System.out.println("\nZurück Aus dem File:\n" + sb.toString());
            returnString = sb.toString();

        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
            System.out.println("file not found");
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return returnString;
    }

    String toXML(Timetable tt) {
        Object[] ttObjectList = new Object[6];

        ttObjectList[0] = tt.getLessonsMon();
        ttObjectList[1] = tt.getLessonsTue();
        ttObjectList[2] = tt.getLessonsWen();
        ttObjectList[3] = tt.getLessonsThu();
        ttObjectList[4] = tt.getLessonsFri();
        ttObjectList[5] = tt.getKlassenname();

        XStream xstream = new XStream();
        xstream.alias("Lessons", Lessons.class);
        String xml = xstream.toXML(ttObjectList);
        // System.out.println("\nXML:\n" + xml);
        return xml;
    }

    public Timetable fromXML(String xml) {
        Object[] tt;
        Timetable fromXml = new Timetable();
        XStream xstream = new XStream();

        xstream.alias("Lessons", Lessons.class);
        //System.out.println("\nfromXML(xml):\n" + xml);
        System.out.println(xml);
        tt = (Object[]) xstream.fromXML(xml);
        fromXml.setLessonsMon((List<Lessons>) tt[0]);
        fromXml.setLessonsTue((List<Lessons>) tt[1]);
        fromXml.setLessonsWen((List<Lessons>) tt[2]);
        fromXml.setLessonsThu((List<Lessons>) tt[3]);
        fromXml.setLessonsFri((List<Lessons>) tt[4]);
        fromXml.setKlassenname((String) tt[5]);
        return fromXml;
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