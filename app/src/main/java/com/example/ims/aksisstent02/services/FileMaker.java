package com.example.ims.aksisstent02.services;

import android.content.Context;
import android.util.Log;

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

        try {
            outputStream = context.openFileOutput(name, Context.MODE_PRIVATE);
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
            System.out.println(file + " file");
            System.out.println("\nFile Direct000000000ory.... ");

            FileInputStream fin = context.openFileInput(file);
            StringBuilder sb = new StringBuilder();

            InputStreamReader inputStream = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(inputStream);

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }

            System.out.println("\nZurück Aus dem File:\n" + sb.toString());
            returnString = sb.toString();

        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
            System.out.println("file not found");
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return returnString;
    }
}

