package org.js.denisvieira.conferenceorganizer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.js.denisvieira.conferenceorganizer.R;
import org.js.denisvieira.conferenceorganizer.adapters.LecturesListViewAdapter;
import org.js.denisvieira.conferenceorganizer.models.Conference;
import org.js.denisvieira.conferenceorganizer.models.Lecture;
import org.js.denisvieira.conferenceorganizer.models.Track;
import org.js.denisvieira.conferenceorganizer.utils.LectureUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by denisvieira on 20/07/16.
 */
public class ListContentFragment extends Fragment {

    private static final String ARG_TRACK_NUMBER = "track_number";

    private ListView listView;
    private ArrayList<Lecture> lecturesArrayList;
    LecturesListViewAdapter mAdapter;

    public ListContentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    public static ListContentFragment newInstance(int sectionNumber) {
        ListContentFragment fragment = new ListContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TRACK_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_schedule_content, container, false);
        listView = (ListView) rootView.findViewById(R.id.lectures_lv);
        Integer trackId = getArguments().getInt(ARG_TRACK_NUMBER);
        getLecturesList(trackId);

        return rootView;
    }


    public void getLecturesList(Integer trackId) {
        listView.setAdapter(null);
        lecturesArrayList = new ArrayList<>();
        ArrayList<Track> tracksOfConference = new ArrayList<>();
        LectureUtils lectureUtils = new LectureUtils();
        Conference conference = new Conference();
        ArrayList<Lecture> track = new ArrayList<>();

        Bundle bundle = getActivity().getIntent().getExtras();
        ArrayList<String> lecturesString = bundle.getStringArrayList("lectures");

        lecturesArrayList = lectureUtils.createLectureArrayList(lecturesString);
        tracksOfConference.addAll(conference.organizeConference(lecturesArrayList));

        for (int b = 0; b < tracksOfConference.get(trackId).getMorningSession().size(); b++) {
            track.add(tracksOfConference.get(trackId).getMorningSession().get(b));
        }
        for (int c = 0; c < tracksOfConference.get(trackId).getAfternoonSession().size(); c++) {
            track.add(tracksOfConference.get(trackId).getAfternoonSession().get(c));
        }

        mAdapter = new LecturesListViewAdapter(getActivity(), track);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lecture lecture = (Lecture) parent.getAdapter().getItem(position);
                Integer trackId = getArguments().getInt(ARG_TRACK_NUMBER)+1;
                SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

                sharingIntent.setType("text/plain");
                String shareBody = "TODAY at "+dateFormatter.format(lecture.getSchedule().getTime())+" : Lecture about ' "+lecture.getTitle()+" ' in TRACK "+trackId+" of MY CONFERENCE .";
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

    }

}
