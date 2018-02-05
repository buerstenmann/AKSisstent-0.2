package com.example.ims.aksisstent02.objects;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Noah on 24.01.2018.
 */

public class Room {
    @Setter
    @Getter
    String roomNumber;

    @Setter
    @Getter
    String roomBuilding;

    @Getter
    @Setter
    int roomPlanId;

    @Setter
    @Getter
    Timetable roomTimeTable;

    @Setter
    @Getter
    String roomUrl;

}
