package com.example.ims.aksisstent02.services;

import android.os.AsyncTask;

import com.example.ims.aksisstent02.objects.Lessons;
import com.example.ims.aksisstent02.objects.Timetable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noah on 03.02.2018.
 */

public class TimetableParser extends AsyncTask<String, Void, Timetable> {


    private Exception exception;

    protected Timetable doInBackground(String[] timetable) {
        Timetable tt = new Timetable();
        try {
            String URL = timetable[0];
            Document document = document = Jsoup.connect(URL).get(); //get the HTML page
            Elements rows = document.select("table[border=\"3\"] > tbody > tr:has(td)"); //select all rows
            int[] offsets = new int[rows.size()];

            List<Lessons> monLessonList = new ArrayList<>();
            List<Lessons> tueLessonList = new ArrayList<>();
            List<Lessons> wenLessonList = new ArrayList<>();
            List<Lessons> thuLessonList = new ArrayList<>();
            List<Lessons> friLessonList = new ArrayList<>();

            List<List<Lessons>> ttArray = new ArrayList<>();

            ttArray.add(monLessonList);
            ttArray.add(tueLessonList);
            ttArray.add(wenLessonList);
            ttArray.add(thuLessonList);
            ttArray.add(friLessonList);


            for (int i = 0; i < rows.get(0).children().size(); i++) //unless colspans are used, this should return the number of columns
            {
                for (int j = 0; j < 13 /*rows.size() */; j++)  // loops through the rows of each column
                {
                    if (rows.get(j).children().size() > 0) {
                        Element cell = rows.get(j).child(i + offsets[j]); //get an individual cell
//                      Element cell = rows.get(j).child(i); //get an individual cell

                        Lessons tempLesson = new Lessons();
                        String[] lessonInfos = interpretInfo(cell.text());
                        tempLesson.setSubject(lessonInfos[0]);
                        tempLesson.setTeacher(lessonInfos[1]);
                        tempLesson.setRoom(lessonInfos[2]);

                        ttArray.get(i).add(tempLesson);


                        if (cell.hasAttr("rowspan")) //if that cell has a rowspan
                        {
                            int rowspan = Integer.parseInt(cell.attr("rowspan")) / 2;
                            if (rowspan > 1) {
                            }

                            for (int k = 1; k < rowspan; k++) {
                                offsets[j + k]--; //add offsets to rows that now have a cell "missing"
                                Lessons temp2Lesson = new Lessons();
                                String[] lesson2Infos = interpretInfo(cell.text());
                                temp2Lesson.setSubject(lesson2Infos[0]);
                                temp2Lesson.setTeacher(lesson2Infos[1]);
                                temp2Lesson.setRoom(lesson2Infos[2]);

                                ttArray.get(i).add(temp2Lesson);

                            }
                            j += rowspan - 1; //add rowspan to index, to skip the "missing" cells
                        }
                    }
                }
            }
            tt.setLessonsMon(monLessonList);
            tt.setLessonsTue(tueLessonList);
            tt.setLessonsWen(wenLessonList);
            tt.setLessonsThu(thuLessonList);
            tt.setLessonsFri(friLessonList);
            System.out.println("DONE");

            return tt;
        } catch (Exception e) {
            this.exception = e;
            e.printStackTrace();
            return null;
        } finally {
        }
    }

    private String[] interpretInfo(String input) {
//        System.out.println("interpretinfo input  " + input);
        String[] output = new String[3];
        String[] segments = input.split(" ");
//        System.out.println(segments.length + "         " + output.length);
        if (input != "") {
            if (segments.length < 3) {
                output[0] = segments[0];
                output[1] = "";
                output[2] = "";
            } else if (segments.length == 5) {
                output[0] = segments[0] + " / " + segments[3];
                output[1] = segments[1] + " / " + segments[4];
                output[2] = segments[5] + " / " + segments[6];
            } else {
                output[0] = segments[0];
                output[1] = segments[1];
                output[2] = segments[2];
            }
        }
        return output;
    }

}