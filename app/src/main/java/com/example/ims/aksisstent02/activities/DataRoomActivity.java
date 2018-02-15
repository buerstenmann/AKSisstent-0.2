package com.example.ims.aksisstent02.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ims.aksisstent02.R;
import com.example.ims.aksisstent02.objects.Lesson;
import com.example.ims.aksisstent02.objects.Room;
import com.example.ims.aksisstent02.objects.Timetable;
import com.example.ims.aksisstent02.services.DataHolder;
import com.example.ims.aksisstent02.services.TimetableDAO;

import java.util.List;

import static com.example.ims.aksisstent02.activities.MainActivity.mainContext;

public class DataRoomActivity extends AppCompatActivity {
    Room room;
    Timetable timetable;
    TimetableDAO timetableDAO;
    TextView viewNumber;
    TextView viewBuilding;
    ImageView imgViewBild;
    TextView[] monTextView;
    TextView[] tueTextView;
    TextView[] wenTextView;
    TextView[] thuTextView;
    TextView[] friTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_room);

        room = DataHolder.getInstance().getRoom();

        monTextView = new TextView[12];
        tueTextView = new TextView[12];
        wenTextView = new TextView[12];
        thuTextView = new TextView[12];
        friTextView = new TextView[12];

        int[] monId = {R.id.mon1, R.id.mon2, R.id.mon3, R.id.mon4, R.id.mon5, R.id.mon6, R.id.mon7, R.id.mon8, R.id.mon9, R.id.mon10, R.id.mon11, R.id.mon12};
        int[] tueId = {R.id.tue1, R.id.tue2, R.id.tue3, R.id.tue4, R.id.tue5, R.id.tue6, R.id.tue7, R.id.tue8, R.id.tue9, R.id.tue10, R.id.tue11, R.id.tue12};
        int[] wenId = {R.id.wen1, R.id.wen2, R.id.wen3, R.id.wen4, R.id.wen5, R.id.wen6, R.id.wen7, R.id.wen8, R.id.wen9, R.id.wen10, R.id.wen11, R.id.wen12};
        int[] thuId = {R.id.thu1, R.id.thu2, R.id.thu3, R.id.thu4, R.id.thu5, R.id.thu6, R.id.thu7, R.id.thu8, R.id.thu9, R.id.thu10, R.id.thu11, R.id.thu12};
        int[] friId = {R.id.fri1, R.id.fri2, R.id.fri3, R.id.fri4, R.id.fri5, R.id.fri6, R.id.fri7, R.id.fri8, R.id.fri9, R.id.fri10, R.id.fri11, R.id.fri12};

        timetableDAO = new TimetableDAO();
        timetable = new Timetable();

        viewNumber = (TextView) findViewById(R.id.viewNumber);
        viewBuilding = (TextView) findViewById(R.id.viewBuilding);
//        imgViewBild = (ImageView) findViewById(R.id.imgViewBild);
        System.out.println(room.getRoomNumber() + " get RoomNumber");
        System.out.println(room.getRoomBuilding() + " get RoomBuilding");

        viewNumber.setText(room.getRoomNumber());
        viewBuilding.setText(room.getRoomBuilding());
        // imgViewBild.setImageResource(R.drawable.ic_action_name);

        for (int i = 0; i < 12; i++) {
            monTextView[i] = (TextView) findViewById(monId[i]);
            tueTextView[i] = (TextView) findViewById(tueId[i]);
            wenTextView[i] = (TextView) findViewById(wenId[i]);
            thuTextView[i] = (TextView) findViewById(thuId[i]);
            friTextView[i] = (TextView) findViewById(friId[i]);
        }
        loadTt(room, mainContext);
        //TODO Noah Detailansicht
    }

    private String getSafeSubject(List<Lesson> lesson, int index) {
        if (lesson.size() > index) {
            if (lesson.get(index) != null) {
                if (lesson.get(index).getSubject() != null) {
                    return lesson.get(index).getSubject();
                }
            }
        }
        return "";
    }

    public void loadTt(Room room, Context context) {

        if (timetableDAO != null) {
            timetable = timetableDAO.getTimetable(room.getRoomNumber(), context);
        } else {
        }

        List<Lesson> lessonMon = timetable.getLessonMon();
        List<Lesson> lessonTue = timetable.getLessonTue();
        List<Lesson> lessonWen = timetable.getLessonWen();
        List<Lesson> lessonThu = timetable.getLessonThu();
        List<Lesson> lessonFri = timetable.getLessonFri();

        for (int i = 0; i < 12; i++) {
            monTextView[i].setText(getSafeSubject(lessonMon, i));
            tueTextView[i].setText(getSafeSubject(lessonTue, i));
            wenTextView[i].setText(getSafeSubject(lessonWen, i));
            thuTextView[i].setText(getSafeSubject(lessonThu, i));
            friTextView[i].setText(getSafeSubject(lessonFri, i));

        }
    }


    public String getNextLesson() {
        String returnString = "Fehler, String nicht definiert";
        //TODO Noah nächste Lektion anzeigen. Date type untersuchen
        return returnString;
    }

    public void toast(String output) {
        Toast toast = Toast.makeText(this, output, Toast.LENGTH_SHORT);
        toast.show();
    }
}
