package org.js.denisvieira.conferenceorganizer;

import org.js.denisvieira.conferenceorganizer.models.Lecture;
import org.js.denisvieira.conferenceorganizer.models.Track;
import org.js.denisvieira.conferenceorganizer.utils.LectureUtils;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by denisvieira on 31/07/16.
 */

public class TrackTest {


    @Test
    public void addLectureInTrackCorrectly(){
        Track track = new Track();

        LectureUtils lectureUtils = new LectureUtils();
        Lecture lecture = lectureUtils.createLecture("Desenvolvimento orientado a gambiarras 45min",1);

        track.addLecture(lecture,Track.PERIOD_MORNING_TYPE);
        track.addLecture(lecture,Track.PERIOD_AFTERNOON_TYPE);

        assertTrue(track.getMorningSession().size() != 0);
        assertTrue(track.getAfternoonSession().size() != 0);

    }

    @Test
    public void notAddWithIncorrectTypeTrack(){
        Track track = new Track();
        LectureUtils lectureUtils = new LectureUtils();

        Lecture lecture = lectureUtils.createLecture("Desenvolvimento orientado a gambiarras 45min",1);

        track.addLecture(lecture,3);
        track.addLecture(lecture,5);

        boolean morningSessionIsEmpty =  track.getMorningSession().size() == 0;
        boolean afternoonSessionIsEmpty =  track.getAfternoonSession().size() == 0;

        assertTrue(morningSessionIsEmpty);
        assertTrue(afternoonSessionIsEmpty);
    }
}
