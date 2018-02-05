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

            for (int i = 0; i < 13; i++) {
                System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-15s %-35s %-35s %-35s %-35s %-35s %n", stundenplan[i][0], stundenplan[i][1], stundenplan[i][2], stundenplan[i][3], stundenplan[i][4], stundenplan[i][5]);
                System.out.println("--------------------------------------------------------------------------------------------------");
                for (int j = 0; j < 13; j++) {
                    List<Lessons> tempLessonList = new ArrayList<>();
                    Lessons tempLesson = new Lessons();
//                    String[] lessonInfos = interpretInfo(stundenplan[i][j]);

                    System.out.println(stundenplan[i][j]);
                    tempLesson.setSubject(stundenplan[i][j]);
//                    tempLesson.setTeacher(lessonInfos[1]);
//                    tempLesson.setRoom(lessonInfos[2]);
                    tempLessonList.add(tempLesson);
                    if (i == 0) {
                        tt.setLessonsMon(tempLessonList);
                    } else if (i == 1) {
                        tt.setLessonsTue(tempLessonList);
                    } else if (i == 2) {
                        tt.setLessonsWen(tempLessonList);
                    } else if (i == 3) {
                        tt.setLessonsThu(tempLessonList);
                    } else if (i == 4) {
                        tt.setLessonsFri(tempLessonList);
                    }

                }
            }
            return tt;
        } catch (Exception e) {
            this.exception = e;
            e.printStackTrace();
            return null;
        } finally {
        }
    }

//    private String[] interpretInfo(String input) {
//        String[] output = new String[2];
//        String segments[] = input.split(" ");
//
//
//        return segments;
//    }

}