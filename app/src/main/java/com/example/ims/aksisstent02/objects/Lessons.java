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
    private String room;
    @Setter
    @Getter
    private String teacher;


    public Lessons() {
        subject = null;
        room = null;
        teacher = null;

    }

    public Lessons(String s_subject, String s_room, String s_teacher) {
        subject = s_subject;
        room = s_room;
        teacher = s_teacher;

    }
}

