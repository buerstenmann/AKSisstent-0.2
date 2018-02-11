package com.example.ims.aksisstent02.services;

import android.content.Context;
import android.util.Log;

import com.example.ims.aksisstent02.activities.MainActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Noah on 05.02.2018.
 */

public class FileMaker {
    public void stringToDom(String xmlSource, String name, Context context) throws IOException {
        FileOutputStream outputStream;

        System.out.println(name + "    -------------  ------------------------------------------------" + xmlSource);
        if (context == null) {
            System.out.println("Context ist leer");
            context = MainActivity.mainContext;
        }
        try {
            outputStream = context.openFileOutput(name.toLowerCase(), Context.MODE_PRIVATE);
            outputStream.write(xmlSource.getBytes("UTF-8"));
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTimetableFromFile(Context context, String file) {
        String returnString = "";
        String line;

        try {
            FileInputStream fin = context.openFileInput(file.toLowerCase());
            StringBuilder sb = new StringBuilder();
            InputStreamReader inputStream = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(inputStream);

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
            returnString = sb.toString();

        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return returnString;
    }
}