package ch.rechner.aksistent.notenrechner_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gerry on 05.02.2018.
 */

public class MarkListDataSource {

    private static final String LOG_TAG = MarkListDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private MarkListDbHelper dbHelper;

    private String[] columns = {
            MarkListDbHelper.COLUMN_ID,
            MarkListDbHelper.COLUMN_DESC,
            MarkListDbHelper.COLUMN_SUBJECT,
            MarkListDbHelper.COLUMN_MARK
    };

    public MarkListDataSource(Context context) {
        Log.d(LOG_TAG, "DataSource erzeugt dbHelper.");
        dbHelper = new MarkListDbHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    public MarkList createMarkList(String subject, String mark, String desc) {
        ContentValues values = new ContentValues();
        values.put(MarkListDbHelper.COLUMN_DESC, desc);
        values.put(MarkListDbHelper.COLUMN_SUBJECT, subject);
        values.put(MarkListDbHelper.COLUMN_MARK, mark);

        long insertId = database.insert(MarkListDbHelper.TABLE_MARK_LIST, null, values);

        Cursor cursor = database.query(MarkListDbHelper.TABLE_MARK_LIST,
                columns, MarkListDbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        MarkList markList = cursorToMarkList(cursor);
        cursor.close();

        return markList;
    }

    public void deleteMarkList(MarkList markList) {
        long id = markList.getId();

        database.delete(MarkListDbHelper.TABLE_MARK_LIST,
                MarkListDbHelper.COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + markList.toString());
    }

    public MarkList updateMarkList(long id, String newDesc, String newSubject, String newMark) {
        ContentValues values = new ContentValues();
        values.put(MarkListDbHelper.COLUMN_DESC, newDesc);
        values.put(MarkListDbHelper.COLUMN_SUBJECT, newSubject);
        values.put(MarkListDbHelper.COLUMN_MARK, newMark);

        database.update(MarkListDbHelper.TABLE_MARK_LIST,
                values,
                MarkListDbHelper.COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MarkListDbHelper.TABLE_MARK_LIST,
                columns, MarkListDbHelper.COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        MarkList markList = cursorToMarkList(cursor);
        cursor.close();

        return markList;
    }



    private MarkList cursorToMarkList(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(MarkListDbHelper.COLUMN_ID);
        int idSubject = cursor.getColumnIndex(MarkListDbHelper.COLUMN_SUBJECT);
        int idMark = cursor.getColumnIndex(MarkListDbHelper.COLUMN_MARK);
        int idDesc = cursor.getColumnIndex(MarkListDbHelper.COLUMN_DESC);

        String subject = cursor.getString(idSubject);
        String mark = cursor.getString(idMark);
        String desc = cursor.getString(idDesc);
        long id = cursor.getLong(idIndex);

        MarkList markList = new MarkList(mark, subject, id, desc);

        return markList;
    }

    public List<MarkList> getAllMarkLists() {
        List<MarkList> markListList = new ArrayList<>();

        Cursor cursor = database.query(MarkListDbHelper.TABLE_MARK_LIST,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        MarkList markList;

        while(!cursor.isAfterLast()) {
            markList = cursorToMarkList(cursor);
            markListList.add(markList);
            Log.d(LOG_TAG, "ID: " + markList.getId() + ", Inhalt: " + markList.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return markListList;
    }

}
