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
            System.out.println("URL = " + URL);
            Document document = document = Jsoup.connect(URL).get(); //get the HTML page
            System.out.println("got Document");
            Elements rows = document.select("table[border=\"3\"] > tbody > tr:has(td)"); //select all rows
            System.out.println("Got Rows: " + rows.size());
//            rows = rows.select("tr"); //select all rows
//            System.out.println("Got elements: " + rows.size());
            int[] offsets = new int[rows.size()];

            String stundenplan[][] = new String[13][6];
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 6; j++) {
                    stundenplan[i][j] = "";
                }
            }

            for (int i = 0; i < rows.get(0).children().size(); i++) //unless colspans are used, this should return the number of columns
            {
                System.out.println("Assuming Number of Cols: " + rows.get(0).children().size());
                for (int j = 0; j < 13 /*rows.size() */; j++)  // loops through the rows of each column
                {
                    if (rows.get(j).children().size() > 0) {
                        Element cell = rows.get(j).child(i + offsets[j]); //get an individual cell
//                      Element cell = rows.get(j).child(i); //get an individual cell
                        System.out.printf("Cell (%d/%d) : %s %n", i, j, cell.text());
                        stundenplan[j][i] = cell.text();
                        if (cell.hasAttr("rowspan")) //if that cell has a rowspan
                        {
                            int rowspan = Integer.parseInt(cell.attr("rowspan")) / 2;
                            if (rowspan > 1) {
                                System.out.printf("Rowspan %d found at (%d/%d)%n", rowspan, i, j);
                            }

                            for (int k = 1; k < rowspan; k++) {
                                offsets[j + k]--; //add offsets to rows that now have a cell "missing"
                                stundenplan[j + k][i] = cell.text();
                            }
                            j += rowspan - 1; //add rowspan to index, to skip the "missing" cells
                        }
                    }
                }
            }
            System.out.println("DONE");

            List<Lessons> monLessonList = new ArrayList<>();
            List<Lessons> tueLessonList = new ArrayList<>();
            List<Lessons> wenLessonList = new ArrayList<>();
            List<Lessons> thuLessonList = new ArrayList<>();
            List<Lessons> friLessonList = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
//                System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-15s %-35s %-35s %-35s %-35s %-35s %n", stundenplan[i][0], stundenplan[i][1], stundenplan[i][2], stundenplan[i][3], stundenplan[i][4], stundenplan[i][5]);
//                System.out.println("--------------------------------------------------------------------------------------------------");


                for (int j = 1; j < 6; j++) {

                    Lessons tempLesson = new Lessons();

                    String[] lessonInfos = interpretInfo(stundenplan[i][j]);

                    tempLesson.setSubject(lessonInfos[0]);
                    tempLesson.setTeacher(lessonInfos[1]);
                    tempLesson.setRoom(lessonInfos[2]);

                    if (j == 1) {
                        monLessonList.add(tempLesson);
                    } else if (j == 2) {
                        tueLessonList.add(tempLesson);
                    } else if (j == 3) {
                        wenLessonList.add(tempLesson);
                    } else if (j == 4) {
                        thuLessonList.add(tempLesson);
                    } else if (j == 5) {
                        friLessonList.add(tempLesson);
                    }
                }
                tt.setLessonsMon(monLessonList);
                tt.setLessonsTue(tueLessonList);
                tt.setLessonsWen(wenLessonList);
                tt.setLessonsThu(thuLessonList);
                tt.setLessonsFri(friLessonList);
            }
            return tt;
        } catch (Exception e) {
            this.exception = e;
            e.printStackTrace();
            return null;
        } finally {
        }
    }

    private String[] interpretInfo(String input) {
        System.out.println("interpretinfo input  " + input);
        String[] output = new String[3];
        String[] segments = input.split(" ");
        System.out.println(segments.length + "         " + output.length);
        if (input != "") {
            if (segments.length < 3) {
                output[0] = segments[0];
                output[1] = "";
                output[2] = "";
            } else {
                output[0] = segments[0];
                output[1] = segments[1];
                output[2] = segments[2];
            }
        }
//        for (int i = 0; i > 2; i++) {
//            if (segments[i] == null) {
//                segments[i] = "";
//                System.out.println(segments[i]);
//            }
//        }

        return output;
    }

}