package org.js.denisvieira.conferenceorganizer.models;

import org.js.denisvieira.conferenceorganizer.utils.LectureUtils;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

        }else if((sumTimeAfternoon + lecture.getMinutes()) <= 420 && (sumTimeAfternoon + lecture.getMinutes()) > 180) {
            sumTimeAfternoon = sumTimeAfternoon + lecture.getMinutes();

            return PERIOD_AFTERNOON_TYPE;
        }else{
            return 999;
        }

    }

    public Track createTrack(ArrayList<Lecture> lecturesToBeAdded, Integer beginPosition){
        final long MORNING_BEGIN_TIME_SCHEDULE= 32400000+10800000;
        final long AFTERNOON_BEGIN_TIME_SCHEDULE= 46800000+10800000;
        final long ONE_MINUTE_IN_MILLIS=60000;

        boolean nextTrack = false;
        Track track = new Track();

        while(!nextTrack){

            for (int i = beginPosition; i < lecturesToBeAdded.size(); i++) {

                switch (track.typeToAddLecture(lecturesToBeAdded.get(i))){
                    case 0:
                        Lecture lecture = lecturesToBeAdded.get(i);
                        final Integer lastMorningSize = track.getMorningSession().size();

                        if(lastMorningSize==0){
                            Time afterAddingMins=new Time(MORNING_BEGIN_TIME_SCHEDULE);
                            lecture.setSchedule(afterAddingMins);
                        }else{
                            Lecture lastMorningLecture= track.getMorningSession().get(lastMorningSize-1);
                            Time afterAddingMins=new Time(lastMorningLecture.getSchedule().getTime() + ( lastMorningLecture.getMinutes() * ONE_MINUTE_IN_MILLIS));
                            lecture.setSchedule(afterAddingMins);
                        }

                        track.addLecture(lecture,Track.PERIOD_MORNING_TYPE);
                        nextTrack = false;
                        break;

                    case 1:
                        Lecture lectureAffternoon = lecturesToBeAdded.get(i);
                        final Integer lastAffternoonSize = track.getAfternoonSession().size();

                        if(lastAffternoonSize == 0){

                            LectureUtils lectureUtils = new LectureUtils();
                            Lecture lunch = lectureUtils.createLecture("ALMOÇO 60min",50);

                            final Integer lastMorningSizeToLunch = track.getMorningSession().size();
                            Lecture lastMorningLectureToLunch= track.getMorningSession().get(lastMorningSizeToLunch-1);
                            Time afterAddingMinsLunch=new Time(lastMorningLectureToLunch.getSchedule().getTime() + ( lastMorningLectureToLunch.getMinutes() * ONE_MINUTE_IN_MILLIS));
                            lunch.setSchedule(afterAddingMinsLunch);
                            track.addLecture(lunch,Track.PERIOD_MORNING_TYPE);

                            Time afterAddingMinsAfternoon=new Time(AFTERNOON_BEGIN_TIME_SCHEDULE);
                            lectureAffternoon.setSchedule(afterAddingMinsAfternoon);

                        }else{
                            Lecture lastAfternoonLecture= track.getAfternoonSession().get(lastAffternoonSize-1);
                            Time afterAddingMinsAfternoon=new Time(lastAfternoonLecture.getSchedule().getTime() + ( lastAfternoonLecture.getMinutes() * ONE_MINUTE_IN_MILLIS));
                            lectureAffternoon.setSchedule(afterAddingMinsAfternoon);
                        }

                        track.addLecture(lectureAffternoon,Track.PERIOD_AFTERNOON_TYPE);
                        nextTrack = false;
                        break;
                    default:
                        nextTrack = true;
                        break;
                }
            }

            if(nextTrack){
                LectureUtils lectureUtils = new LectureUtils();
                Lecture networking = lectureUtils.createLecture("Evento de Networking 30min",60);

                final Integer lastAfternoonSizeToNetworking = track.getAfternoonSession().size();
                Lecture lastAfternoonLectureToNetworking = track.getAfternoonSession().get(lastAfternoonSizeToNetworking-1);
                Time afterAddingMinsNetworking = new Time(lastAfternoonLectureToNetworking.getSchedule().getTime() + ( lastAfternoonLectureToNetworking.getMinutes() * ONE_MINUTE_IN_MILLIS));
                networking.setSchedule(afterAddingMinsNetworking);
                track.addLecture(networking,Track.PERIOD_AFTERNOON_TYPE);
            }
        }

        return track;
    }


}
