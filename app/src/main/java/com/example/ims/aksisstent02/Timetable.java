package com.example.ims.aksisstent02;

import com.thoughtworks.xstream.XStream;

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
    private String klassenname;
    private String[][] klasse = {{"I1a", "I2a", "I3a", "W1a", "W2a", "W3a"}, {"I1aURL", "I2aURL", "I3aURL", "W1aURL", "W2aURL", "W3aURL"}};
    private int anzahlKlassen = klasse[1].length;
    private String[] xmlStundenpläne = new String[anzahlKlassen];

    public void test() {
        System.out.println(anzahlKlassen);
    }

    public void downloadTT() {
        System.out.println("Start timetable loop");
        for (int i = 0; i < anzahlKlassen; i++) {
            System.out.println("\nTimetable constructor loop 1:" + i + "\n");
            xmlStundenpläne[i] = parseHTML(klasse[0][i]);

        }

    }

    public String parseHTML(String tklasse) {

        for (int j = 0; j < 5; j++) {                                                           //loop Tage
            System.out.println("\nTimetable constructor loop 2:" + j + "\n");
            List<Lessons> lessons = new ArrayList<Lessons>();
            for (int k = 0; k < 6; k++) {                                                  //loop Stunden pro tag
                lessons.add(new Lessons("Fach " + k, 2 + k, "teacher" + k, null));
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
        klassenname = tklasse;
        String returnXML = toXML();
        deleteTimetable();
        return returnXML;
    }

    public void deleteTimetable() {
        lessonsMon = null;
        lessonsTue = null;
        lessonsWen = null;
        lessonsThu = null;
        lessonsFri = null;
        klassenname = null;
    }

    Timetable(boolean sep) {
        System.out.println("\nTimetable constructor:\n");
        lessonsMon = null;
        lessonsTue = null;
        lessonsWen = null;
        lessonsThu = null;
        lessonsFri = null;
        klassenname = null;


    }


    Timetable() {
        lessonsMon = null;
        lessonsTue = null;
        lessonsWen = null;
        lessonsThu = null;
        lessonsFri = null;
        fromXML(xml);
    }

    String toXML() {
        Object[] tt = new Object[6];

        tt[0] = lessonsMon;
        tt[1] = lessonsTue;
        tt[2] = lessonsWen;
        tt[3] = lessonsThu;
        tt[4] = lessonsFri;
        tt[5] = klassenname;

        XStream xstream = new XStream();
        xstream.alias("Lessons", Lessons.class);
        String xml = xstream.toXML(tt);
        System.out.println("\nXML:\n" + xml);
        return xml;
    }

    void fromXML(String xml) {
        Object[] tt;

        XStream xstream = new XStream();
        xstream.alias("Lessons", Lessons.class);
        tt = (Object[]) xstream.fromXML(xml);
        System.out.println("\nXML:\n" + xml);

        setLessonsMon((List<Lessons>) tt[0]);
        setLessonsTue((List<Lessons>) tt[1]);
        setLessonsWen((List<Lessons>) tt[2]);
        setLessonsTue((List<Lessons>) tt[3]);
        setLessonsFri((List<Lessons>) tt[4]);
        setKlasse((String) tt[5]);
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
        klassenname = t_Klasse;
    }

    public String getKlasse() {
        return klassenname;
    }

    private String xml = "<object-array>  " +
            "<list>" +      //mon
            " <Lessons> " +
            " <room>2</room>" +
            "   <subject>Fach 0</subject>" +
            "  </Lessons>" +
            "        <Lessons>" +
            "         <room>3</room>" +
            "         <subject>Fach 1</subject>" +
            "       </Lessons>" +
            "       <Lessons>" +
            "          <room>4</room>" +
            "          <subject>Fach 2</subject>" +
            "        </Lessons>" +
            "        <Lessons>" +
            "          <room>5</room>" +
            "          <subject>Fach 3</subject>" +
            "       </Lessons>" +
            "       <Lessons>" +
            "          <room>6</room>" +
            "         <subject>Fach 4</subject>" +
            "       </Lessons>" +
            "      </list>" +
            " <list>" +         //tue
            "   <Lessons>" +
            "     <room>2</room>" +
            "      <subject>Fach 0</subject>" +
            "    </Lessons>" +
            "    <Lessons>" +
            "      <room>3</room>" +
            "      <subject>Fach 1</subject>" +
            "      </Lessons>" +
            "      <Lessons>" +
            "         <room>4</room>" +
            "        <subject>Fach 2</subject>" +
            "      </Lessons>" +
            "      <Lessons>" +
            "       <room>5</room>" +
            "        <subject>Fach 3</subject>" +
            "      </Lessons>" +
            "      <Lessons>" +
            "       <room>6</room>" +
            "        <subject>Fach 4</subject>" +
            "      </Lessons>" +
            "     </list>" +
            "    <list>" +      //wed
            "     <Lessons>" +
            "         <room>2</room>" +
            "         <subject>Fach 0</subject>" +
            "       </Lessons>" +
            "       <Lessons>" +
            "         <room>3</room>" +
            "         <subject>Fach 1</subject>" +
            "       </Lessons>" +
            "       <Lessons>" +
            "         <room>4</room>" +
            "        <subject>Fach 2</subject>" +
            "      </Lessons>" +
            "     <Lessons>" +
            "       <room>5</room>" +
            "        <subject>Fach 3</subject>" +
            "     </Lessons>" +
            "      <Lessons>" +
            "         <room>6</room>" +
            "         <subject>Fach 4</subject>" +
            "      </Lessons>" +
            "    </list>" +
            "     <list>" +     //thu
            "      <Lessons>" +
            "        <room>2</room>" +
            "        <subject>Fach 0</subject>" +
            "      </Lessons>" +
            "      <Lessons>" +
            "        <room>3</room>" +
            "       <subject>Fach 1</subject>" +
            "      </Lessons>" +
            "     <Lessons>" +
            "       <room>4</room>" +
            "       <subject>Fach 2</subject>" +
            "     </Lessons>" +
            "     <Lessons>" +
            "      <room>5</room>" +
            "       <subject>Fach 3</subject>" +
            "    </Lessons>" +
            "     <Lessons>" +
            "       <room>6</room>" +
            "       <subject>Fach 4</subject>" +
            "     </Lessons>" +
            "    </list>" +
            "<list>" +          //fri
            " <Lessons>" +
            "        <room>2</room>" +
            "        <subject>Fach 0</subject>" +
            "      </Lessons>" +
            "      <Lessons>" +
            "        <room>3</room>" +
            "       <subject>Fach 1</subject>" +
            "      </Lessons>" +
            "     <Lessons>" +
            "       <room>4</room>" +
            "       <subject>Fach 2</subject>" +
            "     </Lessons>" +
            "     <Lessons>" +
            "      <room>5</room>" +
            "       <subject>Fach 3</subject>" +
            "    </Lessons>" +
            "     <Lessons>" +
            "       <room>6</room>" +
            "       <subject>Fach 4</subject>" +
            "     </Lessons>" +
            "    </list>" +
            "<string>I3a</string>" +

            "  </object-array>";


}
