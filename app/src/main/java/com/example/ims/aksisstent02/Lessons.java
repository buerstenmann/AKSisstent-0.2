package com.example.ims.aksisstent02;

/**
 * Created by Noah on 29.12.2017.
 */

public class Lessons {
    private String subject;
    private int room;
    private String teacher;
    private String time;

    Lessons() {
        subject = null;
        room = 0;
        teacher = null;
        time = null;
    }

    Lessons(String s_subject, int s_room, String s_teacher, String s_time) {
        subject = s_subject;
        room = s_room;
        teacher = s_teacher;
        time = s_time;
    }

    void setSubject(String n) {
        subject = n;
    }

    void setRoom(int n) {
        room = n;
    }

    void setTime(String n) {
        time = n;
    }

    void setTeacher(String n) {
        teacher = n;
    }

    String getSubject() {
        return subject;
    }

    int getRoom() {
        return room;
    }

    String getTeacher() {
        return teacher;
    }

    String getTime() {
        return time;
    }

}
