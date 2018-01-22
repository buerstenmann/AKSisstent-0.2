package com.example.ims.aksisstent02.services;


import com.example.ims.aksisstent02.objects.Teacher;

import java.util.List;

/**
 * Created by Noah on 23.11.2017.
 */

public class TeacherSearch { //implements SearchInterface

    public Teacher doSearch(String searchQuery, List<Teacher> teacherList) {
        Teacher output = null;
        String comTemp;

        for (int i = 0; i < teacherList.size(); i++) {
            if (!teacherList.isEmpty()) {
                comTemp = teacherList.get(i).getName().toLowerCase();
                System.out.println("comTemp = " + comTemp + " ----- searchQuery = " + searchQuery);

                if (searchQuery.equals(comTemp)) {
                    output = teacherList.get(i);
                }
            }
        }
        return output;
    }

}
