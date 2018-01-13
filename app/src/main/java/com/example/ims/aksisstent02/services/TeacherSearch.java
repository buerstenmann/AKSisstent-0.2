package com.example.ims.aksisstent02.services;


import java.util.List;

/**
 * Created by Noah on 23.11.2017.
 */

public class TeacherSearch implements SearchInterface {

    public String doSearch(String searchQuery, List<String> teacherList) {
        String output = null;
        String comTemp;

        for (int i = 0; i < teacherList.size(); i++) {
            if (!teacherList.isEmpty()) {
                comTemp = teacherList.get(i).toLowerCase();
                System.out.println("comTemp = " + comTemp + " ----- searchQuery = " + searchQuery);

                if (searchQuery.equals(comTemp)) {
                    output = comTemp;
                }
            }
        }
        return output;
    }

}
