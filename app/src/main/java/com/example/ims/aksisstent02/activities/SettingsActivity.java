package com.example.ims.aksisstent02.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ims.aksisstent02.R;
import com.example.ims.aksisstent02.objects.Klasse;
import com.example.ims.aksisstent02.objects.Room;
import com.example.ims.aksisstent02.objects.Teacher;
import com.example.ims.aksisstent02.objects.User;
import com.example.ims.aksisstent02.services.FileMaker;
import com.example.ims.aksisstent02.services.KlassenDAO;
import com.example.ims.aksisstent02.services.RoomDAO;
import com.example.ims.aksisstent02.services.TeachersDAO;
import com.example.ims.aksisstent02.services.TimetableDAO;
import com.example.ims.aksisstent02.services.XStreamer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.ims.aksisstent02.activities.MainActivity.mainContext;

public class SettingsActivity extends AppCompatActivity {

    Button btnUpdate;
    TextView viewUpdate;

    TeachersDAO alphaTeacherDAO;
    RoomDAO alphaRoomDAO;
    KlassenDAO alphaKlassenDAO;
    TimetableDAO timetableDownloadTt;
    XStreamer streamer;
    FileMaker fileMaker;
    User user;

    private List<Teacher> teacherList;
    private List<Room> roomList;
    private List<Klasse> klasseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Date date = Calendar.getInstance().getTime();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        alphaTeacherDAO = new TeachersDAO(MainActivity.mainContext);
        alphaRoomDAO = new RoomDAO(MainActivity.mainContext);
        alphaKlassenDAO = new KlassenDAO(MainActivity.mainContext);
        timetableDownloadTt = new TimetableDAO();
        fileMaker = new FileMaker();
        streamer = new XStreamer();

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        viewUpdate = (TextView) findViewById(R.id.viewUpdate);

        user = streamer.fromXmlUser(fileMaker.getTimetableFromFile(MainActivity.mainContext, "user"));
        viewUpdate.setText(sdf.format(date));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String updateDate = sdf.format(date);

                teacherList = alphaTeacherDAO.doXML();
                roomList = alphaRoomDAO.doXML();
                klasseList = alphaKlassenDAO.doXML();

                System.out.println("\nFile Directory.... MenuActivity");            //Test der Abspeicherung von Stunenpläne
                String[] files = mainContext.getFilesDir().list();
                if (files == null) {
                    Log.e("Speicherfehler", "Stundenplan files nicht generiert");
                }
                for (int i = 0; i < files.length; i++) {
                    System.out.println("\nFile: " + files[i]);
                }
                timetableDownloadTt.downloadTtKlasse(klasseList, mainContext);
                timetableDownloadTt.downloadTtTeacher(teacherList, mainContext);
                timetableDownloadTt.downloadTtRoom(roomList, mainContext);


                user.setLastUpdate(date);
                viewUpdate.setText(updateDate);
                try {
                    fileMaker.stringToDom(streamer.toXmlUser(user), "user", MainActivity.mainContext);
                } catch (Exception e) {

                }
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });


    }
}
