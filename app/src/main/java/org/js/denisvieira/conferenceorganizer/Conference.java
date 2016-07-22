package org.js.denisvieira.conferenceorganizer;

import java.util.ArrayList;

/**
 * Created by denisvieira on 20/07/16.
 */
public class Conference {


    private ArrayList<Track> conference;

    public Conference(ArrayList<Track> conference) {
        this.conference = conference;
    }

    public ArrayList<Track> getConference() {
        return conference;
    }

    public void setConference(ArrayList<Track> conference) {
        this.conference = conference;
    }

    public void organizeConference(ArrayList<Lecture> lecturesToBeAdded){

        Integer beginPosition = 0;

        while (beginPosition != lecturesToBeAdded.lastIndexOf(lecturesToBeAdded)){
            createTrack(lecturesToBeAdded, beginPosition);
        }

    }

    public Integer createTrack(ArrayList<Lecture> lecturesToBeAdded, Integer beginPosition){

        boolean nextTrack = false;

        ArrayList<Lecture> morningSession = new ArrayList<>();
        ArrayList<Lecture> afternoonSession = new ArrayList<>();

        Track track = new Track(morningSession,afternoonSession);

        while(!nextTrack){

            for (int i = beginPosition; i < lecturesToBeAdded.size(); i++) {

                if(track.addLecture(lecturesToBeAdded.get(i))){
                    track.addLecture(lecturesToBeAdded.get(i));
                    nextTrack = false;
                }else{
                    nextTrack = true;
                }
            }
        }

        conference.add(track);

        beginPosition = track.getTrackLecturesSize();
        return beginPosition;
    }

}
