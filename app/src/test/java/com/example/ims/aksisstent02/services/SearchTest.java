package com.example.ims.aksisstent02.services;

import com.example.ims.aksisstent02.objects.Teacher;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Noah on 15.02.2018.
 */
public class SearchTest {
    Search search = new Search();

    @Test
    public void findTeacher() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setTeacherName("Test");

        assertEquals(search.findTeacher("Test"), teacher);
    }

    @Test
    public void findRoom() throws Exception {
    }

}