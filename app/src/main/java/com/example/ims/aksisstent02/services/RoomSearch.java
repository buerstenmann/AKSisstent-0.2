package com.example.ims.aksisstent02.services;

import java.util.List;

/**
 * Created by Noah on 14.12.2017.
 */

public class RoomSearch {
    public String doSearch(String roomQuery, List<String> roomList) {
        String output = null;
        String comTemp;

        for (int i = 0; i < roomList.size(); i++) {
            if (!roomList.isEmpty()) {
                comTemp = roomList.get(i).toLowerCase();
                System.out.println("comTemp = " + comTemp + " ----- searchQuery = " + roomQuery);

                if (roomQuery.equals(comTemp)) {
                    output = comTemp;
                }
            }
        }
        return output;
    }


}
