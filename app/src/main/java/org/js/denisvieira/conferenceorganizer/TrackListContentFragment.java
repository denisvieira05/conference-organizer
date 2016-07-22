package org.js.denisvieira.conferenceorganizer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by denisvieira on 20/07/16.
 */
public class TrackListContentFragment extends Fragment {

    ListView listView;
    ArrayList<Lecture> lecturesArrayList;
    LecturesListViewAdapter mAdapter;


    private static final String ARG_SECTION_NUMBER = "section_number";

    public TrackListContentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);

    }

    public static TrackListContentFragment newInstance(int sectionNumber) {
        TrackListContentFragment fragment = new TrackListContentFragment();
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


        getLecturesList();
        return rootView;
    }

    private void getLecturesList() {
        listView.setAdapter(null);

        lecturesArrayList = new ArrayList<>();

        Bundle b = getActivity().getIntent().getExtras();
        ArrayList<String> lecturesString = b.getStringArrayList("lectures");



        for (int i = 0; i < lecturesString.size(); i++) {

            lecturesArrayList.add(createLecture(lecturesString.get(i),i));
        }

//        for (int a = 0; a < lecturesArrayList.size(); a++) {
//            Log.i("lecturesArrayList : "+lecturesArrayList.get(a).getMinutes(),lecturesArrayList.get(a).getTitle());
//        }
        ArrayList<Track> tracks = new ArrayList<>();
        Conference conference = new Conference(tracks);
        conference.organizeConference(lecturesArrayList);

        for (int a = 0; a < conference.getConference().size(); a++) {
            Log.i("getConference",conference.getConference().get(a).toString());
        }

        mAdapter = new LecturesListViewAdapter(getActivity(), lecturesArrayList);
        listView.setAdapter(mAdapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Mensagem conversa = (Mensagem) parent.getAdapter().getItem(position);
//                Intent intent = new Intent(getActivity(), ConversaActivity.class);
//
//                if(conversa.getIdUserDestinatario()==selfUserId){
//                    intent.putExtra("destinatarioId", conversa.getIdUserRemetente());
//                }else{
//                    intent.putExtra("destinatarioId", conversa.getIdUserDestinatario());
//                }
//                intent.putExtra("name", conversa.getDsMensagem());
//                startActivity(intent);
//            }
//        });

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
