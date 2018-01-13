package com.example.ims.aksisstent02.objects;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Noah on 29.12.2017.
 */

public class Lessons {
    @Setter
    @Getter
    private String subject;
    @Setter
    @Getter
    private int room;
    @Setter
    @Getter
    private String teacher;
    @Setter
    @Getter
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

