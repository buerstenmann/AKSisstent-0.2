package com.example.ims.aksisstent02.activities;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

        alphaTeacherDAO = new TeachersDAO(MenuActivity.menuContext);
        alphaRoomDAO = new RoomDAO(MenuActivity.menuContext);
        alphaKlassenDAO = new KlassenDAO(MenuActivity.menuContext);
        timetableDownloadTt = new TimetableDAO();
        fileMaker = new FileMaker();
        streamer = new XStreamer();

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        viewUpdate = (TextView) findViewById(R.id.viewUpdate);

        user = streamer.fromXmlUser(fileMaker.getTimetableFromFile(MainActivity.mainContext, "user"));
        viewUpdate.setText(user.getLastUpdate() + "kk");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43


                teacherList = alphaTeacherDAO.doXML();
                roomList = alphaRoomDAO.doXML();
                klasseList = alphaKlassenDAO.doXML();


                System.out.println("\nFile Directory.... MenuActivity");            //Test der Abspeicherung von Stunenpläne
                String[] files = MenuActivity.menuContext.getFilesDir().list();
                if (files == null) {
                    Log.e("Speicherfehler", "Stundenplan files nicht generiert");
                }
                for (int i = 0; i < files.length; i++) {
                    System.out.println("\nFile: " + files[i]);
                }
                timetableDownloadTt.downloadTtKlasse(klasseList, MenuActivity.menuContext);
                timetableDownloadTt.downloadTtTeacher(teacherList, MenuActivity.menuContext);
                timetableDownloadTt.downloadTtRoom(roomList, MenuActivity.menuContext);


                user.setLastUpdate(System.currentTimeMillis());
                viewUpdate.setText(System.currentTimeMillis() + " 2");
                try {
                    fileMaker.stringToDom(streamer.toXmlUser(user), "user", MainActivity.mainContext);
                } catch (Exception e) {

                }
            }
        });


    }
}
