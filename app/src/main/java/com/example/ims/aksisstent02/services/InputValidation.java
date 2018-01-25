package com.example.ims.aksisstent02.services;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Noah on 14.12.2017.
 */

public class InputValidation {
    public String validateText(String searchQuery) {        //Methode um den gesuchten Begriff zu evaluieren und zu bereinigen,
        // damit er weiter verwendet werden kann
        String output = null;
        String roomQuery;
        String teacherQuery;

        searchQuery = escapeString(searchQuery.toLowerCase());

        if (searchQuery.indexOf("raum") != -1) {                //Suchebegriffe die Raum enthalten werden aufgeteilt und nur die Raumnummer wird zurückgegeben
            String segments[] = searchQuery.split(" ");
            roomQuery = segments[1];
            if (TextUtils.isDigitsOnly(searchQuery)) {
                output = roomQuery;
            }
        } else if (TextUtils.isDigitsOnly(searchQuery)) {
            output = searchQuery;
        } else {
            if (searchQuery.contains(" ")) {
                System.out.println(searchQuery.contains(" "));
                String segments1[] = searchQuery.split(" ");
                teacherQuery = segments1[1];
                output = teacherQuery;
            } else {
                output = searchQuery;
            }
        }
        return output;
    }

    public String escapeString(String searchQuery) {                //Suchbegriff wird bereinigt
        String[] specialchars = new String[]{"<", ">", "/", "", "&", "%", "'", "*", "{", "}", "(", ")", ",", ".", "!", "[",};
        String output = null;
        for (int i = 0; i < specialchars.length; i++) {
            if (searchQuery.contains(specialchars[i])) {
                output = "";
                Log.i("Inputfehler", "Ein Sonderzeichen wurde entdeckt");
            }
            output = searchQuery;
        }
        return output;
    }
}