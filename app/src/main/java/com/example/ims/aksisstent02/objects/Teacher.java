package com.example.ims.aksisstent02.objects;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Noah on 22.01.2018.
 */

public class Teacher {
    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    String forename;

    @Getter
    @Setter
    String url;

    @Setter
    @Getter
    Timetable teacherTt;

    @Getter
    @Setter
    String email;

    @Getter
    @Setter
    String pathPicture;
}
