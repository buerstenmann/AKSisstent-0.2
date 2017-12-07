package com.example.ims.aksisstent02;

import android.content.Context;
import android.content.res.XmlResourceParser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Noah on 29.11.2017.
 */

public class TeachersDAO {
    String Output1;
    List<String> Output2;
    Context context;

    public void doXML() {

        try {
            XmlResourceParser is = context.getXml(R.xml.teachers);

            File fXmlFile = new File("url");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            //   System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("teachers");


            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;

                    //Output1= "First Name : " + eElement.getElementsByTagName("forename").item(0).getTextContent();

                    Output2.add(eElement.getElementsByTagName("name").item(0).getTextContent());

                    DataActivity Alpha = new DataActivity();
                    Alpha.toast(Output2.get(temp));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
