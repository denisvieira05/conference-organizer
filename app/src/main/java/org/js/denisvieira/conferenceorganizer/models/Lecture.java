package org.js.denisvieira.conferenceorganizer.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by denisvieira on 20/07/16.
 */
public class Lecture implements Serializable {


    private Integer id;
    private String title;
    private Integer minutes;
    private long schedule;

    public Lecture(Integer id, String title, Integer minutes) {

        if(minutes <= 0)
            throw new IllegalArgumentException("Minutes of lecture can't be negative");

        this.id = id;
        this.title = title;
        this.minutes = minutes;
    }

    public long getSchedule() {
        return schedule;
    }

    public void setSchedule(long schedule) {
        this.schedule = schedule;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

}
