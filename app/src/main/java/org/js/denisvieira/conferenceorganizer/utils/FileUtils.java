package org.js.denisvieira.conferenceorganizer.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.widget.Toast;

import org.js.denisvieira.conferenceorganizer.activitys.MainActivity;
import org.js.denisvieira.conferenceorganizer.models.Lecture;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by denisvieira on 26/07/16.
 */
public class FileUtils {

    public static final Integer CORRECT_STRING_STATUS = 1;
    public static final Integer INVALID_STRING_STATUS = 2;
    public static final Integer EMPTY_STRING_STATUS = 3;

    public FileUtils() {
    }

    public boolean fileIsCorrect(Uri uri,String format){
        File file = new File(uri.getPath());
        String ext = file.getName().substring(file.getName().lastIndexOf('.'));
        if (uri.getLastPathSegment().contains(".") && ext.equals("."+format)){
            return true;
        }else {
            return false;
        }
    }


    public ArrayList<String> getLecturesStrings(Uri uri, Context context){
        File file = new File(uri.getPath());
        ArrayList<String> lectures = new ArrayList<String>();

        try {
            AssetManager assetManager = context.getResources().getAssets();
            InputStream inputStream = assetManager.open(file.getName());
//          InputStream inputStream = assetManager.open("proposals.txt");

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String linha;

            while ((linha = bufferedReader.readLine()) != null) {
                    lectures.add(linha);
            }
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return lectures;



    }

    public Integer checkLecturesStrings(ArrayList<String> lectures){
        Integer finalStatus = 0;

        for(int i=0; i <= lectures.size(); i++){
            LectureUtils lectureUtils = new LectureUtils();
            Lecture lecture = lectureUtils.createLecture(lectures.get(i), i);

            if(lecture == null){
                finalStatus = INVALID_STRING_STATUS;
            }else{
                finalStatus = CORRECT_STRING_STATUS;
            }
        }

        return finalStatus;
    }

}
