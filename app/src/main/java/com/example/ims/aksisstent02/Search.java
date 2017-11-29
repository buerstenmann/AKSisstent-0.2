package com.example.ims.aksisstent02;


import android.text.TextUtils;

/**
 * Created by Noah on 23.11.2017.
 */

public class Search {

    private final String raum = "raum";

    String[] teachers = {"a", "b", "c", "Schoohf", "Fiechter", "Enz", "Meier", "Flick", "Grünwald", "Peter Lustig"};
    String[] rooms = {"015", "212", "521", "518", "001", "039", "262", "236", "502"};

    public String openFile(String searchQuery) {
        String output = null;
        String roomQuery;
        String teacherQuery;
        if (searchQuery.toLowerCase().indexOf(raum) != -1) {
            String segments[] = searchQuery.split(" ");
            roomQuery = segments[1];
            if (TextUtils.isDigitsOnly(searchQuery)) {
                output = searchRooms(roomQuery);
            }
        } else if (TextUtils.isDigitsOnly(searchQuery)) {
            output = searchRooms(searchQuery);
        } else {
            String segments1[] = searchQuery.split(" ");
            teacherQuery = segments1[1];

            output = searchTeachers(teacherQuery);
        }
        return output;
    }

    public String searchRooms(String roomQuery) {
        String output = null;
        for (int i = 0; i < 9; i++) {
            if (roomQuery.equals(rooms[i])) {
                output = rooms[i];
            }
        }
        return output;
    }

    public String searchTeachers(String searchQuery) {
        String output = null;
        for (int i = 0; i < 9; i++) {
            if (searchQuery.equals(teachers[i])) {
                output = teachers[i];

            }
        }
        return output;
    }


}
