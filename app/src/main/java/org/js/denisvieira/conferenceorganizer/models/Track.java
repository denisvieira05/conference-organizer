package org.js.denisvieira.conferenceorganizer.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by denisvieira on 22/07/16.
 */
public class Track implements Serializable{

    public final static Integer PERIOD_MORNING_TYPE = 0;
    public final static Integer PERIOD_AFTERNOON_TYPE = 1;

    private Integer sumTimeMorning = 0;
    private Integer sumTimeAfternoon = 180;

    private ArrayList<Lecture> morningSession = new ArrayList<>();
    private ArrayList<Lecture> afternoonSession = new ArrayList<>();

    public Track() {
    }

    public ArrayList<Lecture> getMorningSession() {
        return morningSession;
    }

    public void setMorningSession(ArrayList<Lecture> morningSession) {
        this.morningSession = morningSession;
    }

    public ArrayList<Lecture> getAfternoonSession() {
        return afternoonSession;
    }

    public void setAfternoonSession(ArrayList<Lecture> afternoonSession) {
        this.afternoonSession = afternoonSession;
    }

    public Integer getTrackLecturesSize(Track track){
        Integer totalTrackLecturesSize = 0;
        totalTrackLecturesSize += track.getMorningSession().size();
        totalTrackLecturesSize += track.getAfternoonSession().size();

        return totalTrackLecturesSize;
    }

    public void addLecture(Lecture lecture,Integer type){
        if(type == PERIOD_MORNING_TYPE){
            morningSession.add(lecture);
        }else if(type == PERIOD_AFTERNOON_TYPE) {
            afternoonSession.add(lecture);
        }
    }

    public Integer typeToAddLecture(Lecture lecture) {

        if( (sumTimeMorning + lecture.getMinutes()) <= 180){
            sumTimeMorning = sumTimeMorning + lecture.getMinutes();

            return PERIOD_MORNING_TYPE;

        }else if((sumTimeAfternoon + lecture.getMinutes()) <= 420 && (sumTimeAfternoon + lecture.getMinutes()) > 180){
            sumTimeAfternoon = sumTimeAfternoon + lecture.getMinutes();

            return PERIOD_AFTERNOON_TYPE;
        }else{
            return 999;
        }

    }


    public Track createTrack(ArrayList<Lecture> lecturesToBeAdded, Integer beginPosition){

        boolean nextTrack = false;

        Track track = new Track();

        while(!nextTrack){

            for (int i = beginPosition; i < lecturesToBeAdded.size(); i++) {

                switch (track.typeToAddLecture(lecturesToBeAdded.get(i))){
                    case 0: track.addLecture(lecturesToBeAdded.get(i),Track.PERIOD_MORNING_TYPE);
                        nextTrack = false;
                        break;
                    case 1: track.addLecture(lecturesToBeAdded.get(i),Track.PERIOD_AFTERNOON_TYPE);
                        nextTrack = false;
                        break;
                    default:
                        nextTrack = true;
                        break;
                }
            }
        }

        return track;
    }


}
