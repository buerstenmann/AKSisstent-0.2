package com.example.ims.aksisstent02;


import android.text.TextUtils;

/**
 * Created by Noah on 23.11.2017.
 */

public class Search {

    private final String raum = "raum";

    String[] teachers = {"a", "b", "c", "schoohf", "fiechter", "enz", "meier", "flick", "grünwald", "peter lustig"};
    String[] rooms = {"015", "212", "521", "518", "001", "039", "262", "236", "502"};

    public String doSearch(String searchQuery) {
        String output = null;
        String roomQuery;
        String teacherQuery;
        if (searchQuery.toLowerCase().indexOf(raum) != -1) {
            String segments[] = searchQuery.split(" ");
            roomQuery = segments[1];
            if (TextUtils.isDigitsOnly(searchQuery)) {
                output = findRoom(roomQuery);
            }
        } else if (TextUtils.isDigitsOnly(searchQuery)) {
            output = findRoom(searchQuery);
        } else {
            if (searchQuery.contains(" ")) {
                String segments1[] = searchQuery.split(" ");
                teacherQuery = segments1[1];
                output = findTeacher(teacherQuery);
            } else {
                output = findTeacher(searchQuery);
            }

        }
        return output;
    }

    public String findRoom(String roomQuery) {
        String output = null;
        for (int i = 0; i < 9; i++) {
            if (roomQuery.equals(rooms[i])) {
                output = rooms[i];
            }
        }
        return output;
    }

    public String findTeacher(String searchQuery) {
        String output = null;
        for (int i = 0; i < 9; i++) {
            if (searchQuery.toLowerCase().equals(teachers[i]) & searchQuery.contains("}") == false) {
                output = teachers[i];
            }
        }
        return output;
    }


}
