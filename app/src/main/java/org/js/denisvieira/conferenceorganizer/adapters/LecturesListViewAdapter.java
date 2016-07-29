package org.js.denisvieira.conferenceorganizer.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.js.denisvieira.conferenceorganizer.R;
import org.js.denisvieira.conferenceorganizer.models.Lecture;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by denisvieira on 20/07/16.
 */
public class LecturesListViewAdapter extends ArrayAdapter<Lecture> {

    Context context;

    public LecturesListViewAdapter(Context context, ArrayList<Lecture> objects) {
        super(context, 0, 0, objects);
        this.context = context;
    }

    @SuppressLint({ "ViewHolder", "InflateParams" })
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Lecture lecture = getItem(position);

        view = LayoutInflater.from(getContext()).inflate(R.layout.item_lecture, null);

//        String hour = String.format("%02d:%02d",
//                TimeUnit.MILLISECONDS.toHours(lecture.getSchedule()),
//                TimeUnit.MILLISECONDS.toMinutes(lecture.getSchedule()) -
//                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(lecture.getSchedule())));

        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
        ((TextView)view.findViewById(R.id.hour_tv)).setText(dateFormatter.format(lecture.getSchedule().getTime()));
        ((TextView)view.findViewById(R.id.title_tv)).setText(lecture.getTitle());
        ((TextView)view.findViewById(R.id.time_tv)).setText(lecture.getMinutes().toString()+"min");

        return view;
    }
}
