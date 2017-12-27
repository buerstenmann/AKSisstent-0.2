package com.example.ims.aksisstent02;

import android.text.TextUtils;

/**
 * Created by Noah on 14.12.2017.
 */

public class InputValidation {
    public String validateText(String searchQuery) {


        String output = null;
        String roomQuery;
        String teacherQuery;

        searchQuery = escapeString(searchQuery.toLowerCase());


        if (searchQuery.indexOf("raum") != -1) {
            String segments[] = searchQuery.split(" ");
            roomQuery = segments[1];
            if (TextUtils.isDigitsOnly(searchQuery)) {
                output = roomQuery;
            }
        } else if (TextUtils.isDigitsOnly(searchQuery)) {
            output = searchQuery;
        } else {
            if (searchQuery.contains(" ")) {
                String segments1[] = searchQuery.split(" ");
                teacherQuery = segments1[1];
                output = teacherQuery;
            } else {
                output = searchQuery;
            }

        }

        return output;
    }

    public String escapeString(String searchQuery) {
        String[] specialchars = new String[]{"<", ">", "/", "", "&", "%", "'", "*", "{", "}", "(", ")", ",", ".", "!", "[",};
        String output = null;
        for (int i = 0; i < specialchars.length; i++) {
            if (searchQuery.contains(specialchars[i])) {
                output = searchQuery;
            }
        }
        return output;
    }
}
