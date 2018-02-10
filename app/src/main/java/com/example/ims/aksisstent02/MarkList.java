package ch.rechner.aksistent.notenrechner_2;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Gerry on 04.01.2018.
 */

public class MarkList extends ListActivity {

    private String mark;
    private String subject;
    private long id;
    private String desc;

    public MarkList(String mark, String Subject, long id, String desc) {
        this.mark = mark;
        this.subject = subject;
        this.id = id;
        this.desc = desc;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        String output = subject + desc + mark;

        return output;
    }
}
