package com.example.ims.aksisstent02.services;

import android.content.Context;

import com.example.ims.aksisstent02.R;
import com.example.ims.aksisstent02.objects.Klasse;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Noah on 25.01.2018.
 */

public class KlassenDAO {
    List<Klasse> klasse;
    private Context context;

    public KlassenDAO(Context current) {
        this.context = current;
    }

    public List<Klasse> doXML() {
        klasse = new ArrayList<>();
        try {
            InputStream fXmlFile = context.getResources().openRawResource(R.raw.klasse);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            System.out.println("\ndBuilder.parse");
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("klasse");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Klasse klasseLoop = new Klasse();
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
                    klasseLoop.setKlasseName(eElement.getElementsByTagName("NAME").item(0).getTextContent());
                    klasseLoop.setKlassenURL(eElement.getElementsByTagName("URL").item(0).getTextContent());
                    klasse.add(klasseLoop);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return klasse;
    }
}