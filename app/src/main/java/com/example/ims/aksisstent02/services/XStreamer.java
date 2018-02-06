package com.example.ims.aksisstent02.services;

import com.example.ims.aksisstent02.objects.Lessons;
import com.example.ims.aksisstent02.objects.Timetable;
import com.example.ims.aksisstent02.objects.User;
import com.thoughtworks.xstream.XStream;

import java.util.List;

/**
 * Created by Noah on 05.02.2018.
 */

public class XStreamer {

    public String toXmlTt(Timetable tt) {
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

    public String toXmlUser(User user) {
        XStream xstream = new XStream();
        xstream.alias("Lessons", Lessons.class);
        String xml = xstream.toXML(user);
        return xml;
    }

    public Timetable fromXmlTt(String xml) {
        Object[] tt;
        Timetable fromXml = new Timetable();
        XStream xstream = new XStream();

        xstream.alias("Lessons", Lessons.class);
        //System.out.println("\nfromXML(xml):\n" + xml);
        System.out.println(xml + "---------------------------------------------------");
        tt = (Object[]) xstream.fromXML(xml);
        fromXml.setLessonsMon((List<Lessons>) tt[0]);
        fromXml.setLessonsTue((List<Lessons>) tt[1]);
        fromXml.setLessonsWen((List<Lessons>) tt[2]);
        fromXml.setLessonsThu((List<Lessons>) tt[3]);
        fromXml.setLessonsFri((List<Lessons>) tt[4]);
        fromXml.setKlassenname((String) tt[5]);
        return fromXml;
    }

    public User fromXmlUser(String xml) {
        XStream xstream = new XStream();
        xstream.alias("User", User.class);
        return (User) xstream.fromXML(xml);

    }
}
