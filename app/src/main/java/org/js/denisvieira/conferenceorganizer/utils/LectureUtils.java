package org.js.denisvieira.conferenceorganizer.utils;

import org.js.denisvieira.conferenceorganizer.models.Lecture;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by denisvieira on 26/07/16.
 */
public class LectureUtils {

    public LectureUtils() {
    }

    public ArrayList<Lecture> createLectureArrayList(ArrayList<String> lecturesString){
        ArrayList<Lecture> lecturesArrayList = new ArrayList<>();

        for (int i = 0; i < lecturesString.size(); i++) {
            Lecture lecture = createLecture(lecturesString.get(i),i);
            if(lecture != null){
                lecturesArrayList.add(lecture);
            }
        }

        if(lecturesArrayList.size() <= 0){
            return null;
        }else{
            return lecturesArrayList;
        }
    }

    public Lecture createLecture(String lectureString, int position) {
        if(position < 0){
            return null;
        }

        String title;
        Integer minutes;
        List<String> timeString = new ArrayList();

        String expressionPattern = "([0-9][0-9])min";
        String lightningExpressionPattern = "(lightning)";

        Pattern pattern = Pattern.compile(expressionPattern);
        Matcher matcher = pattern.matcher(lectureString);

        Pattern patternForLightning = Pattern.compile(lightningExpressionPattern);
        Matcher lightningMatcher = patternForLightning.matcher(lectureString);

        while (matcher.find()) {
            timeString.add(matcher.group());
        }

        if (timeString.size() > 0) {
            title = lectureString.replace(timeString.get(0).trim(), "").trim();
            minutes = Integer.parseInt(timeString.get(0).trim().replace("min", ""));

            if(minutes <= 0 || minutes > 60 || title.equals("")) {
                return null;
            }else{
                return new Lecture(position, title, minutes);
            }

        } else {

            if (lightningMatcher.find()) {
                title = lectureString.replace("lightning".trim(), "").trim();
                minutes = 5;
                return new Lecture(position, title,minutes);
            }
        }

        return null;
    }


}
