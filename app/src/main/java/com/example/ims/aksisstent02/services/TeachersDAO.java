package com.example.ims.aksisstent02.services;

import android.content.Context;

import com.example.ims.aksisstent02.R;
import com.example.ims.aksisstent02.objects.Teacher;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Noah on 29.11.2017.
 */

public class TeachersDAO {
    List<Teacher> teacher;
    private Context context;

    public TeachersDAO(Context current) {
        this.context = current;
    }

    public List<Teacher> doXML() {
        teacher = new ArrayList<>();
        try {
            // XmlResourceParser is = context.getXml(R.xml.teachers);
            InputStream fXmlFile = context.getResources().openRawResource(R.raw.teachers);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            System.out.println("\ndBuilder.parse");
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            //   System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            System.out.println("\ngetElemetsByTagName");
            NodeList nList = doc.getElementsByTagName("teacher");

            System.out.println("\n# of elements found :" + nList.getLength());
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Teacher teacherLoop = new Teacher();
                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;

                    teacherLoop.setTeacherForename(eElement.getElementsByTagName("FORENAME").item(0).getTextContent());
                    teacherLoop.setTeacherName(eElement.getElementsByTagName("NAME").item(0).getTextContent());
                    teacherLoop.setTeacherEmail(eElement.getElementsByTagName("EMAIL").item(0).getTextContent());
                    teacherLoop.setTeacherTtUrl(eElement.getElementsByTagName("URL").item(0).getTextContent());
//                    teacherLoop.setTeacherPictureId(Integer.parseInt(eElement.getElementsByTagName("PATH").item(0).getTextContent()));
                    teacher.add(teacherLoop);
                    teacherLoop = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacher;
    }
}