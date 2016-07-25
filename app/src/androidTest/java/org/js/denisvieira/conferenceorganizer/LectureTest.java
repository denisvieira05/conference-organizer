package org.js.denisvieira.conferenceorganizer;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.js.denisvieira.conferenceorganizer.models.Lecture;
import org.junit.Test;

/**
 * Created by denisvieira on 24/07/16.
 */
public class LectureTest {


    @Test
    public void minutesCantBeNegative(){
        Lecture lecture = new Lecture(1,"Palestra",-15);
        boolean minutesCondition = lecture.getMinutes() <= 0;
        Assert.assertFalse(minutesCondition);
    }



}
