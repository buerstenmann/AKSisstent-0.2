package com.example.ims.aksisstent02.services;

import android.content.Context;

import com.example.ims.aksisstent02.R;
import com.example.ims.aksisstent02.objects.Room;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Noah on 13.12.2017.
 */

public class RoomDAO {
    List<Room> roomList = new ArrayList<>();
    private Context context;

    public RoomDAO(Context current) {
        this.context = current;
    }

    public List<Room> doXML() {

        try {
            // XmlResourceParser is = context.getXml(R.xml.teachers);

            InputStream fXmlFile = context.getResources().openRawResource(R.raw.rooms);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            System.out.println("\ndBuilder.parse");
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            //   System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            System.out.println("\ngetElemetsByTagName");
            NodeList nList = doc.getElementsByTagName("ROOM");

            System.out.println("\n# of elements found :" + nList.getLength());
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;

                    Room tempLoopRoom = new Room();
                    tempLoopRoom.setRoomNumber(eElement.getElementsByTagName("NUMBER").item(0).getTextContent());
                    tempLoopRoom.setRoomBuilding(eElement.getElementsByTagName("BUILDING").item(0).getTextContent());
                    tempLoopRoom.setRoomUrl(eElement.getElementsByTagName("URL").item(0).getTextContent());
                    roomList.add(tempLoopRoom);
                }
            }
            System.out.println(roomList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomList;
    }
}
