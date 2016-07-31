package org.js.denisvieira.conferenceorganizer;

import junit.framework.Assert;

import org.js.denisvieira.conferenceorganizer.models.Lecture;
import org.js.denisvieira.conferenceorganizer.utils.LectureUtils;
import org.junit.Test;


import java.util.ArrayList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.startsWith;

/**
 * Created by denisvieira on 24/07/16.
 */
public class LectureTest{

    LectureUtils lectureUtils = new LectureUtils();

    @Test
    public void creatingLectureSuccess(){
        Lecture lecture = lectureUtils.createLecture("Desenvolvimento orientado a gambiarras 45min",1);

        assertNotNull("should not be null", lecture);
        assertTrue("Minutes value should be less than or equal to 60",lecture.getMinutes() <= 60);
        assertFalse("position should be greater than 0",lecture.getId() < 0);
        // TODO add if minutes format is wrong
    }

    @Test
    public void creatingLectureWithoutMinutesUnit(){
        Lecture lecture = lectureUtils.createLecture("Desenvolvimento orientado a gambiarras 30",2);
        assertNull("should be null", lecture);
    }

    @Test
    public void creatingLectureFailWhenGreaterThan60() {
        Lecture lecture = lectureUtils.createLecture("Desenvolvimento orientado a gambiarras 80min",2);
        assertNull("should be null", lecture);
    }
    @Test
    public void creatingLectureFailWhenIndexNegative() {
        Lecture lecture = lectureUtils.createLecture("Desenvolvimento orientado a gambiarras 60min",-1);
        assertNull("should be null", lecture);
    }

    @Test
    public void creatingLectureArrayListSuccess(){
        ArrayList<String> lecturesStrings = new ArrayList();

        lecturesStrings.add("Erros de Ruby oriundos de versões erradas de gems 45");
        lecturesStrings.add("Aplicações isomórficas: o futuro (que talvez nunca chegaremos) 80min");
        lecturesStrings.add("Diminuindo tempo de execução de testes em aplicações Rails enterprise 60min");
        lecturesStrings.add("Ruby on Rails: Por que devemos deixá-lo para trás 60min");
        lecturesStrings.add("Programação em par 45min");
        lecturesStrings.add("Ruby vs. Clojure para desenvolvimento backend 30min");

        ArrayList<Lecture> lectureArrayList = new ArrayList();
        lectureArrayList = lectureUtils.createLectureArrayList(lecturesStrings);

        assertNotNull("should not be null", lectureArrayList);
        assertTrue("should be greater than 0",lectureArrayList.size() > 0);
        assertTrue("Should Ignore String Lectures with format problems in minutes",lectureArrayList.size() == 4);
    }

    @Test
    public void creatingLectureArrayListFail(){
        ArrayList<String> lecturesStrings = new ArrayList<>();
        lecturesStrings.add("Diminuindo tempo de execução de testes em aplicações Rails enterprise");
        lecturesStrings.add("Ruby on Rails: Por que devemos deixá-lo para trás 60");
        lecturesStrings.add("Programação em par 80min");
        lecturesStrings.add("30min");

        ArrayList<Lecture> lectureArrayList = new ArrayList<>();
        lectureArrayList = lectureUtils.createLectureArrayList(lecturesStrings);

        assertNull("should be null", lectureArrayList);

    }



}
