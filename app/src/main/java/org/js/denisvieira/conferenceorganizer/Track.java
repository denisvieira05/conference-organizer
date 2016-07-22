package org.js.denisvieira.conferenceorganizer;

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

    private ArrayList<Lecture> morningSession;
    private ArrayList<Lecture> afternoonSession;

    public Track(ArrayList<Lecture> morningSession, ArrayList<Lecture> afternoonSession) {
        this.morningSession = morningSession;
        this.afternoonSession = afternoonSession;
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

    public Integer getTrackLecturesSize(){
        Integer totalTrackLecturesSize = 0;
        totalTrackLecturesSize += morningSession.size();
        totalTrackLecturesSize += afternoonSession.size();

        return totalTrackLecturesSize;
    }

    public boolean addLecture(Lecture lecture){
        if(typeToAddLecture(lecture) == PERIOD_MORNING_TYPE){
            morningSession.add(lecture);
            return true;
        }else if(typeToAddLecture(lecture) == PERIOD_AFTERNOON_TYPE){
            afternoonSession.add(lecture);
            return true;
        }else{
            return false;
        }
    }

    public Integer typeToAddLecture(Lecture lecture) {

        Integer lectureTimeMorningSum = sumTimeMorning + lecture.getMinutes();
        Integer lectureTimeAfternoonSum = sumTimeAfternoon + lecture.getMinutes();

        if( lectureTimeMorningSum <= 180){
            sumTimeMorning = sumTimeMorning + lecture.getMinutes();

            return PERIOD_MORNING_TYPE;

        }else if(lectureTimeAfternoonSum <= 420 && lectureTimeAfternoonSum > 180){
            sumTimeAfternoon = sumTimeAfternoon + lecture.getMinutes();

            return PERIOD_AFTERNOON_TYPE;
        }else{
            return 999;
        }

    }

}
