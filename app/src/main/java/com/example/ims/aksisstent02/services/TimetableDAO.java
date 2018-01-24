package com.example.ims.aksisstent02.services;

import android.content.Context;
import android.util.Log;

import com.example.ims.aksisstent02.objects.Lessons;
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
    private String[][] klasse = {{"I1a", "I2a", "I3a", "W1a", "W2a", "W3a"},
            {"I1aURL", "I2aURL", "I3aURL", "W1aURL", "W2aURL", "W3aURL"}};

    private int anzahlKlassen = klasse[1].length;
    private String[] xmlStundenpläne = new String[anzahlKlassen];

    public void downloadTt(List<Teacher> teacherList, Context context) {
        System.out.println("Start TeacherTimetable Loop");
        for (int i = 0; i < teacherList.size(); i++) {
            try {
                stringToDom(parseHTML(teacherList.get(i).getName()), teacherList.get(i).getName(), context);

            } catch (Exception E) {
            }
        }
    }

    public void downloadTtKlasse(Context context) {
        System.out.println("Start timetable loop");
        for (int i = 0; i < anzahlKlassen; i++) {
            // System.out.println("\nTimetable constructor loop 1:" + i + "\n");
            try {
                stringToDom(parseHTML(klasse[0][i]), klasse[0][i], context);

            } catch (Exception E) {
            }

        }

    }

    public String parseHTML(String tklasse) {
        Timetable tt = new Timetable();
        for (int j = 0; j < 5; j++) {                                                           //loop Tage
            //System.out.println("\nTimetable constructor loop 2:" + j + "\n");
            List<Lessons> lessons = new ArrayList<Lessons>();
            for (int k = 0; k < 6; k++) {                                                  //loop Stunden pro tag
                lessons.add(new Lessons("Fach " + k, "2" + k, "teacher" + k, null));
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
        tt.setKlassenname(tklasse);
        String returnXML = toXML(tt);
        deleteTimetable(tt);
        return returnXML;
    }

    public Timetable getTimetable(String name, int mode, Context context) {
        /* String[][] arrayList={{},{}};
        if (mode == 0) {
            arrayList = klasse;
        } else if (mode == 1) {
            //arrayList = lehrer;
        } else if (mode == 2) {
            //arrayList = raum;
        } else {
        }*/


        System.out.println("getTimetable " + name);
        int index = getIndex(name, klasse);
        Timetable returnTable;
        String xmlTt = getTimetableFromFile(context, "I3a");
        System.out.println(xmlTt + " xml des Timetable");
        if (index != 404) {

            returnTable = fromXML(xmlTt);
        } else {
            returnTable = null;
            System.out.println("Warning Fehler Index wurde nicht definiert");
        }
        return returnTable;
    }

    public int getIndex(String klassenname, String[][] arrayList) {
        int index = 3;
        for (int i = 0; i > anzahlKlassen; i++) {
            if (klassenname == arrayList[0][i]) {
                index = 3;
            }
        }
        return index;
    }


    public void deleteTimetable(Timetable tt) {
        tt.setLessonsMon(null);
        tt.setLessonsTue(null);
        tt.setLessonsWen(null);
        tt.setLessonsThu(null);
        tt.setLessonsFri(null);
    }

    public static void stringToDom(String xmlSource, String name, Context context)
            throws IOException {

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
        file = file + ".txt";
        try {
            System.out.println("\nFile Directory.... ");
            String[] files = context.getFilesDir().list();
            for (int i = 0; i < files.length; i++)
                System.out.println("\nFile: " + files[i]);
            System.out.println(file + " file");
            FileInputStream fin = context.openFileInput(file);
            InputStreamReader inputStream = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println("\nZurück Aus dem File:\n" + sb.toString());
            returnString = sb.toString();

        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString()
            );
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

        XStream xstream = new XStream();
        xstream.alias("Lessons", Lessons.class);
        //System.out.println("\nfromXML(xml):\n" + xml);
        tt = (Object[]) xstream.fromXML(xml);
        Timetable fromXml = new Timetable();
        fromXml.setLessonsMon((List<Lessons>) tt[0]);
        fromXml.setLessonsTue((List<Lessons>) tt[1]);
        fromXml.setLessonsWen((List<Lessons>) tt[2]);
        fromXml.setLessonsThu((List<Lessons>) tt[3]);
        fromXml.setLessonsFri((List<Lessons>) tt[4]);
        fromXml.setKlassenname((String) tt[5]);
        return fromXml;
    }

}
