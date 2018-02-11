package com.example.ims.aksisstent02.services;


import com.example.ims.aksisstent02.objects.Room;
import com.example.ims.aksisstent02.objects.Teacher;

import java.util.List;

import static com.example.ims.aksisstent02.activities.MainActivity.mainContext;

/**
 * Created by Noah on 23.11.2017.
 */

public class Search {
    public Teacher findTeacher(String searchQuery) {
        Teacher outputTeacher = null;
        String teacherToCompare;

        TeachersDAO alphaTeacherDAO = new TeachersDAO(mainContext);
        List<Teacher> teacherList = alphaTeacherDAO.doXML();

        if (!teacherList.isEmpty()) {
            for (int i = 0; i < teacherList.size(); i++) {
                teacherToCompare = teacherList.get(i).getTeacherName().toLowerCase();
                if (searchQuery.equals(teacherToCompare)) {
                    outputTeacher = teacherList.get(i);
                }
            }
        } else {
            //TODO Noah Logger
        }
        return outputTeacher;
    }

    public Room findRoom(String searchQuery) {
        Room output = null;
        String roomToCompare;

        RoomDAO alphaRoomDAO = new RoomDAO(mainContext);
        List<Room> roomList = alphaRoomDAO.doXML();

        if (!roomList.isEmpty()) {
            for (int i = 0; i < roomList.size(); i++) {
                roomToCompare = roomList.get(i).getRoomNumber().toLowerCase();
                if (searchQuery.equals(roomToCompare)) {
                    output = roomList.get(i);
                }
            }
        } else {
        }
        return output;
    }
}