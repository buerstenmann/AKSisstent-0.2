package com.example.ims.aksisstent02.objects;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


/**
 * Created by Noah on 29.12.2017.
 */

public class Timetable {
    @Setter
    @Getter
    private List<Lessons> lessonsMon = new ArrayList<Lessons>();

    @Setter
    @Getter
    private List<Lessons> lessonsTue = new ArrayList<Lessons>();

    @Setter
    @Getter
    private List<Lessons> lessonsWen = new ArrayList<Lessons>();

    @Setter
    @Getter
    private List<Lessons> lessonsThu = new ArrayList<Lessons>();

    @Getter
    @Setter
    private List<Lessons> lessonsFri = new ArrayList<Lessons>();

    @Setter
    @Getter
    private String klassenname;




    private String xml = "<object-array>  " +
            "<list>" +      //mon
            " <Lessons> " +
            " <room>2</room>" +
            "   <subject>Fach 0</subject>" +
            "  </Lessons>" +
            "        <Lessons>" +
            "         <room>3</room>" +
            "         <subject>Fach 1</subject>" +
            "       </Lessons>" +
            "       <Lessons>" +
            "          <room>4</room>" +
            "          <subject>Fach 2</subject>" +
            "        </Lessons>" +
            "        <Lessons>" +
            "          <room>5</room>" +
            "          <subject>Fach 3</subject>" +
            "       </Lessons>" +
            "       <Lessons>" +
            "          <room>6</room>" +
            "         <subject>Fach 4</subject>" +
            "       </Lessons>" +
            "      </list>" +
            " <list>" +         //tue
            "   <Lessons>" +
            "     <room>2</room>" +
            "      <subject>Fach 0</subject>" +
            "    </Lessons>" +
            "    <Lessons>" +
            "      <room>3</room>" +
            "      <subject>Fach 1</subject>" +
            "      </Lessons>" +
            "      <Lessons>" +
            "         <room>4</room>" +
            "        <subject>Fach 2</subject>" +
            "      </Lessons>" +
            "      <Lessons>" +
            "       <room>5</room>" +
            "        <subject>Fach 3</subject>" +
            "      </Lessons>" +
            "      <Lessons>" +
            "       <room>6</room>" +
            "        <subject>Fach 4</subject>" +
            "      </Lessons>" +
            "     </list>" +
            "    <list>" +      //wed
            "     <Lessons>" +
            "         <room>2</room>" +
            "         <subject>Fach 0</subject>" +
            "       </Lessons>" +
            "       <Lessons>" +
            "         <room>3</room>" +
            "         <subject>Fach 1</subject>" +
            "       </Lessons>" +
            "       <Lessons>" +
            "         <room>4</room>" +
            "        <subject>Fach 2</subject>" +
            "      </Lessons>" +
            "     <Lessons>" +
            "       <room>5</room>" +
            "        <subject>Fach 3</subject>" +
            "     </Lessons>" +
            "      <Lessons>" +
            "         <room>6</room>" +
            "         <subject>Fach 4</subject>" +
            "      </Lessons>" +
            "    </list>" +
            "     <list>" +     //thu
            "      <Lessons>" +
            "        <room>2</room>" +
            "        <subject>Fach 0</subject>" +
            "      </Lessons>" +
            "      <Lessons>" +
            "        <room>3</room>" +
            "       <subject>Fach 1</subject>" +
            "      </Lessons>" +
            "     <Lessons>" +
            "       <room>4</room>" +
            "       <subject>Fach 2</subject>" +
            "     </Lessons>" +
            "     <Lessons>" +
            "      <room>5</room>" +
            "       <subject>Fach 3</subject>" +
            "    </Lessons>" +
            "     <Lessons>" +
            "       <room>6</room>" +
            "       <subject>Fach 4</subject>" +
            "     </Lessons>" +
            "    </list>" +
            "<list>" +          //fri
            " <Lessons>" +
            "        <room>2</room>" +
            "        <subject>Fach 0</subject>" +
            "      </Lessons>" +
            "      <Lessons>" +
            "        <room>3</room>" +
            "       <subject>Fach 1</subject>" +
            "      </Lessons>" +
            "     <Lessons>" +
            "       <room>4</room>" +
            "       <subject>Fach 2</subject>" +
            "     </Lessons>" +
            "     <Lessons>" +
            "      <room>5</room>" +
            "       <subject>Fach 3</subject>" +
            "    </Lessons>" +
            "     <Lessons>" +
            "       <room>6</room>" +
            "       <subject>Fach 4</subject>" +
            "     </Lessons>" +
            "    </list>" +
            "<string>I3a</string>" +

            "  </object-array>";


}
