package com.example.ims.aksisstent02.services;

import com.example.ims.aksisstent02.objects.Lesson;
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

        ttObjectList[0] = tt.getLessonMon();
        ttObjectList[1] = tt.getLessonTue();
        ttObjectList[2] = tt.getLessonWen();
        ttObjectList[3] = tt.getLessonThu();
        ttObjectList[4] = tt.getLessonFri();
        ttObjectList[5] = tt.getKlassenname();

        XStream xstream = new XStream();
        xstream.alias("Lesson", Lesson.class);
        String xml = xstream.toXML(ttObjectList);
        return xml;
    }

    public String toXmlUser(User user) {
        XStream xstream = new XStream();
        xstream.alias("Lesson", Lesson.class);
        String xml = xstream.toXML(user);
        return xml;
    }

    public Timetable fromXmlTt(String xml) {
        Object[] tt;
        Timetable fromXml = new Timetable();
        XStream xstream = new XStream();

        xstream.alias("Lesson", Lesson.class);
        tt = (Object[]) xstream.fromXML(xml);
        fromXml.setLessonMon((List<Lesson>) tt[0]);
        fromXml.setLessonTue((List<Lesson>) tt[1]);
        fromXml.setLessonWen((List<Lesson>) tt[2]);
        fromXml.setLessonThu((List<Lesson>) tt[3]);
        fromXml.setLessonFri((List<Lesson>) tt[4]);
        fromXml.setKlassenname((String) tt[5]);
        return fromXml;
    }

    public User fromXmlUser(String xml) {
        XStream xstream = new XStream();
        xstream.alias("User", User.class);
        return (User) xstream.fromXML(xml);

    }
}
