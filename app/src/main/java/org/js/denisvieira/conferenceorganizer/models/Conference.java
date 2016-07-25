package org.js.denisvieira.conferenceorganizer.models;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by denisvieira on 20/07/16.
 */
public class Conference {

    ArrayList<Track> conference;

    public Conference() {
    }

    public ArrayList<Track> getConference() {
        return conference;
    }

    public void setConference(ArrayList<Track> conference) {
        this.conference = conference;
    }

    public ArrayList<Track> organizeConference(ArrayList<Lecture> lecturesToBeAdded){

        conference = new ArrayList<>();

        Integer beginPosition = 0;

        while (beginPosition < lecturesToBeAdded.size()){
            Track track = new Track();
            track = track.createTrack(lecturesToBeAdded, beginPosition);
            beginPosition += track.getTrackLecturesSize(track);
            conference.add(track);
        }

        return conference;

    }


}
