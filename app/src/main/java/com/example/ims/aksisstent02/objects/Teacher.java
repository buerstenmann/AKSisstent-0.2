package com.example.ims.aksisstent02.objects;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Noah on 22.01.2018.
 */

public class Teacher {
    @Getter
    @Setter
    String teacherName;

    @Getter
    @Setter
    String teacherForename;

    @Getter
    @Setter
    String teacherTtUrl;

    @Setter
    @Getter
    Timetable teacherTimetable;

    @Getter
    @Setter
    String teacherEmail;

    @Getter
    @Setter
    int teacherPictureId;
}
