package com.example.ims.aksisstent02.services;


import com.example.ims.aksisstent02.objects.Room;
import com.example.ims.aksisstent02.objects.Teacher;

import java.util.List;

/**
 * Created by Noah on 23.11.2017.
 */

public class TeacherSearch { //implements SearchInterface

    public Teacher findTeacher(String searchQuery, List<Teacher> teacherList) {
        Teacher output = null;
        String comTemp;

        if (!teacherList.isEmpty()) {
            for (int i = 0; i < teacherList.size(); i++) {
                comTemp = teacherList.get(i).getTeacherName().toLowerCase();
                System.out.println("comTemp = " + comTemp + " ----- searchQuery = " + searchQuery);
                if (searchQuery.equals(comTemp)) {
                    output = teacherList.get(i);
                    System.out.println("output= " + output);
                }
            }
        } else {
            System.out.println("Error, TeacherList is null");
        }
        return output;
    }

    public Room findRoom(String searchQuery, List<Room> roomList) {
        Room output = null;
        String comTemp;

        if (!roomList.isEmpty()) {
            for (int i = 0; i < roomList.size(); i++) {
                comTemp = roomList.get(i).getRoomNumber().toLowerCase();
                System.out.println("comTemp = " + comTemp + " ----- searchQuery = " + searchQuery);
                if (searchQuery.equals(comTemp)) {
                    output = roomList.get(i);
                    System.out.println("output= " + output);
                }
            }
        } else {
            System.out.println("Error, RoomList is null");
        }
        return output;
    }
}
