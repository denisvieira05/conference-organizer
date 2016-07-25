package org.js.denisvieira.conferenceorganizer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.js.denisvieira.conferenceorganizer.R;
import org.js.denisvieira.conferenceorganizer.adapters.LecturesListViewAdapter;
import org.js.denisvieira.conferenceorganizer.models.Conference;
import org.js.denisvieira.conferenceorganizer.models.Lecture;
import org.js.denisvieira.conferenceorganizer.models.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by denisvieira on 20/07/16.
 */
public class ListContentFragment extends Fragment {

    private ListView listView;
    private ArrayList<Lecture> lecturesArrayList;
    LecturesListViewAdapter mAdapter;


    private static final String ARG_SECTION_NUMBER = "section_number";

    public ListContentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);

    }

    public static ListContentFragment newInstance(int sectionNumber) {
        ListContentFragment fragment = new ListContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_schedule_content, container, false);

        listView = (ListView) rootView.findViewById(R.id.lectures_lv);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText("view :"+getArguments().getInt(ARG_SECTION_NUMBER));
        Integer trackId = getArguments().getInt(ARG_SECTION_NUMBER);
        getLecturesList(trackId);

        return rootView;
    }

    public void getLecturesList(Integer trackId) {

        lecturesArrayList = new ArrayList<>();
        Bundle bundle = getActivity().getIntent().getExtras();
        ArrayList<String> lecturesString = bundle.getStringArrayList("lectures");

        for (int i = 0; i < lecturesString.size(); i++) {
            lecturesArrayList.add(createLecture(lecturesString.get(i),i));
        }

        listView.setAdapter(null);

        ArrayList<Track> tracksOfConference = new ArrayList<>();
        Conference conference = new Conference();
        tracksOfConference.addAll(conference.organizeConference(lecturesArrayList));

        ArrayList<Lecture> track = new ArrayList<>();

        for (int b = 0; b < tracksOfConference.get(trackId).getMorningSession().size(); b++) {
            track.add(tracksOfConference.get(trackId).getMorningSession().get(b));
        }
        for (int b = 0; b < tracksOfConference.get(trackId).getMorningSession().size(); b++) {
            track.add(tracksOfConference.get(trackId).getAfternoonSession().get(b));
        }

        mAdapter = new LecturesListViewAdapter(getActivity(), track);
        listView.setAdapter(mAdapter);

    }

    private Lecture createLecture(String lectureString,int position) {

        String title;
        String minutes;
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
            minutes = strTimes.get(0).trim().replace("min", "");
            return new Lecture(position, title, Integer.parseInt(minutes));

        } else {

            if (lightningMatcher.find()) {
                title = lectureString.replace("lightning".trim(), "").trim();
                minutes = "5";
                return new Lecture(position, title,Integer.parseInt(minutes));
            }
        }

        return null;
    }
}
