package com.example.ims.aksisstent02.services;

import android.content.Context;

import com.example.ims.aksisstent02.objects.Klasse;
import com.example.ims.aksisstent02.objects.Room;
import com.example.ims.aksisstent02.objects.Teacher;
import com.example.ims.aksisstent02.objects.Timetable;

import java.util.List;

/**
 * Created by Noah on 18.01.2018.
 */

public class TimetableDAO {

    XStreamer streamer;
    FileMaker fileMaker;

    public void downloadTtRoom(List<Room> roomList, Context context) {
        System.out.println("Start RoomTimetable Loop");
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
        System.out.println("Start KlassenTimetable loop");
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
}


