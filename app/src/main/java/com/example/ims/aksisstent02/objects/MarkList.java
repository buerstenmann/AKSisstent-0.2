package com.example.ims.aksisstent02.objects;

import android.app.ListActivity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Gerry on 04.01.2018.
 */

public class MarkList extends ListActivity {
    @Getter
    @Setter
    private String mark;
    @Getter
    @Setter
    private String subject;
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private String desc;

    public MarkList(String mark, String Subject, long id, String desc) {
        this.mark = mark;
        this.subject = subject;
        this.id = id;
        this.desc = desc;
    }

    @Override
    public String toString() {
        String output = mark + " " + desc + " " + subject;

        return output;
    }
}
