package com.example.ims.aksisstent02.services;

import com.example.ims.aksisstent02.objects.Room;
import com.example.ims.aksisstent02.objects.Teacher;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Noah on 24.01.2018.
 */

public class DataHolder {
    @Setter
    @Getter
    private Teacher teacher;
    @Setter
    @Getter
    private Room room;

    private static final DataHolder holder = new DataHolder();

    public static DataHolder getInstance() {
        return holder;
    }
}
