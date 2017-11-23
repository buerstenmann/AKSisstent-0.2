package com.example.ims.aksisstent02;


import android.text.TextUtils;

import static android.text.TextUtils.isDigitsOnly;

/**
 * Created by Noah on 23.11.2017.
 */

public class Search {
    private String roomQuery;
    private String searchQuery;
    private final String raum = "raum";
    private String output = "Kein Suchresultat gefunden";
    String[] teachers = {"a", "b", "c", "Schoohf", "Fiechter", "Enz", "Meier", "Flick", "Grünwald"};
    String[] rooms = {"015", "212", "521", "518", "001", "039", "262", "236", "502"};

    public String openFile(String searchQuery) {

        if (searchQuery.toLowerCase().indexOf(raum) != -1) {
            String segments[] = searchQuery.split(" ");
            roomQuery = segments[1];
            searchRooms(roomQuery);
        }

        boolean digitsOnly = TextUtils.isDigitsOnly(searchQuery);
        if (digitsOnly == true) {
            searchRooms(searchQuery);
        }

        searchTeachers(searchQuery);

        return output;
    }

    public void searchRooms(String roomQuery) {
        for (int i = 0; i < 9; i++) {
            if (roomQuery.equals(rooms[i])) {
                output = rooms[i];
            }
        }
    }

    public void searchTeachers(String searchQuery) {
        for (int i = 0; i < 9; i++) {
            if (searchQuery.equals(teachers[i])) {
                output = teachers[i];
            }
        }

    }

}
