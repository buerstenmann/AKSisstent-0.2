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
    private List<Lesson> lessonMon = new ArrayList<Lesson>();

    @Setter
    @Getter
    private List<Lesson> lessonTue = new ArrayList<Lesson>();

    @Setter
    @Getter
    private List<Lesson> lessonWen = new ArrayList<Lesson>();

    @Setter
    @Getter
    private List<Lesson> lessonThu = new ArrayList<Lesson>();

    @Getter
    @Setter
    private List<Lesson> lessonFri = new ArrayList<Lesson>();

    @Setter
    @Getter
    private String klassenname;




    private String xml = "<object-array>  " +
            "<list>" +      //mon
            " <Lesson> " +
            " <room>2</room>" +
            "   <subject>Fach 0</subject>" +
            "  </Lesson>" +
            "        <Lesson>" +
            "         <room>3</room>" +
            "         <subject>Fach 1</subject>" +
            "       </Lesson>" +
            "       <Lesson>" +
            "          <room>4</room>" +
            "          <subject>Fach 2</subject>" +
            "        </Lesson>" +
            "        <Lesson>" +
            "          <room>5</room>" +
            "          <subject>Fach 3</subject>" +
            "       </Lesson>" +
            "       <Lesson>" +
            "          <room>6</room>" +
            "         <subject>Fach 4</subject>" +
            "       </Lesson>" +
            "      </list>" +
            " <list>" +         //tue
            "   <Lesson>" +
            "     <room>2</room>" +
            "      <subject>Fach 0</subject>" +
            "    </Lesson>" +
            "    <Lesson>" +
            "      <room>3</room>" +
            "      <subject>Fach 1</subject>" +
            "      </Lesson>" +
            "      <Lesson>" +
            "         <room>4</room>" +
            "        <subject>Fach 2</subject>" +
            "      </Lesson>" +
            "      <Lesson>" +
            "       <room>5</room>" +
            "        <subject>Fach 3</subject>" +
            "      </Lesson>" +
            "      <Lesson>" +
            "       <room>6</room>" +
            "        <subject>Fach 4</subject>" +
            "      </Lesson>" +
            "     </list>" +
            "    <list>" +      //wed
            "     <Lesson>" +
            "         <room>2</room>" +
            "         <subject>Fach 0</subject>" +
            "       </Lesson>" +
            "       <Lesson>" +
            "         <room>3</room>" +
            "         <subject>Fach 1</subject>" +
            "       </Lesson>" +
            "       <Lesson>" +
            "         <room>4</room>" +
            "        <subject>Fach 2</subject>" +
            "      </Lesson>" +
            "     <Lesson>" +
            "       <room>5</room>" +
            "        <subject>Fach 3</subject>" +
            "     </Lesson>" +
            "      <Lesson>" +
            "         <room>6</room>" +
            "         <subject>Fach 4</subject>" +
            "      </Lesson>" +
            "    </list>" +
            "     <list>" +     //thu
            "      <Lesson>" +
            "        <room>2</room>" +
            "        <subject>Fach 0</subject>" +
            "      </Lesson>" +
            "      <Lesson>" +
            "        <room>3</room>" +
            "       <subject>Fach 1</subject>" +
            "      </Lesson>" +
            "     <Lesson>" +
            "       <room>4</room>" +
            "       <subject>Fach 2</subject>" +
            "     </Lesson>" +
            "     <Lesson>" +
            "      <room>5</room>" +
            "       <subject>Fach 3</subject>" +
            "    </Lesson>" +
            "     <Lesson>" +
            "       <room>6</room>" +
            "       <subject>Fach 4</subject>" +
            "     </Lesson>" +
            "    </list>" +
            "<list>" +          //fri
            " <Lesson>" +
            "        <room>2</room>" +
            "        <subject>Fach 0</subject>" +
            "      </Lesson>" +
            "      <Lesson>" +
            "        <room>3</room>" +
            "       <subject>Fach 1</subject>" +
            "      </Lesson>" +
            "     <Lesson>" +
            "       <room>4</room>" +
            "       <subject>Fach 2</subject>" +
            "     </Lesson>" +
            "     <Lesson>" +
            "      <room>5</room>" +
            "       <subject>Fach 3</subject>" +
            "    </Lesson>" +
            "     <Lesson>" +
            "       <room>6</room>" +
            "       <subject>Fach 4</subject>" +
            "     </Lesson>" +
            "    </list>" +
            "<string>I3a</string>" +

            "  </object-array>";


}
