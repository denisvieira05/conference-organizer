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
        List<String> strTimes = new ArrayList();

        //Tentar colocar em uma só.
        String expressionPattern = "([0-9][0-9])min"; // padrão para os minutos
        String lightningExpressionPattern = "(lightning)";

        //Patern e Matcher normal
        Pattern pattern = Pattern.compile(expressionPattern);
        Matcher matcher = pattern.matcher(lectureString);

        //Pattern e Matcher do lightning
        Pattern lightningPatern = Pattern.compile(lightningExpressionPattern);
        Matcher lightningMatcher = lightningPatern.matcher(lectureString);

        while (matcher.find()) {
            strTimes.add(matcher.group());
        }

        if (strTimes.size() > 0) {
            title = lectureString.replace(strTimes.get(0).trim(), "").trim();
            minutes = Integer.parseInt(strTimes.get(0).trim().replace("min", ""));

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
