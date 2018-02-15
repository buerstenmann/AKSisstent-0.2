package com.example.ims.aksisstent02.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Gerry on 31.01.2018.
 */

public class MarkListDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = MarkListDbHelper.class.getSimpleName();

    public static final String DB_NAME = "mark_list.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_MARK_LIST = "mark_list";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SUBJECT = "subject";
    public static final String COLUMN_MARK = "mark";
    public static final String COLUMN_DESC = "description";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_MARK_LIST +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SUBJECT + " TEXT NOT NULL, " +
                    COLUMN_DESC + " TEXT NOT NULL, " +
                    COLUMN_MARK + " TEXT NOT NULL);";



    public MarkListDbHelper(Context context) {
        // super(context, "PLACEHOLDER", null, 1, null);
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        } catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
