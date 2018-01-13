package com.example.ims.aksisstent02.objects;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


/**
 * Created by Noah on 29.12.2017.
 */

public class Timetable {
    @Setter
    @Getter
    private List<Lessons> lessonsMon = new ArrayList<Lessons>();

    @Setter
    @Getter
    private List<Lessons> lessonsTue = new ArrayList<Lessons>();

    @Setter
    @Getter
    private List<Lessons> lessonsWen = new ArrayList<Lessons>();

    @Setter
    @Getter
    private List<Lessons> lessonsThu = new ArrayList<Lessons>();

    @Getter
    @Setter
    private List<Lessons> lessonsFri = new ArrayList<Lessons>();

    @Setter
    @Getter
    private String klassenname;

    private String[][] klasse = {{"I1a", "I2a", "I3a", "W1a", "W2a", "W3a"}, {"I1aURL", "I2aURL", "I3aURL", "W1aURL", "W2aURL", "W3aURL"}};
    private int anzahlKlassen = klasse[1].length;
    private String[] xmlStundenpläne = new String[anzahlKlassen];


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

    public Timetable getTimetable(String name, int mode) {
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
        if (index != 404) {

            returnTable = fromXML(xmlStundenpläne[index]);
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


    public void deleteTimetable() {
        lessonsMon = null;
        lessonsTue = null;
        lessonsWen = null;
        lessonsThu = null;
        lessonsFri = null;
        klassenname = null;
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

    public Timetable fromXML(String xml) {
        Object[] tt;

        XStream xstream = new XStream();
        xstream.alias("Lessons", Lessons.class);
        System.out.println("\nfromXML(xml):\n" + xml);
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
