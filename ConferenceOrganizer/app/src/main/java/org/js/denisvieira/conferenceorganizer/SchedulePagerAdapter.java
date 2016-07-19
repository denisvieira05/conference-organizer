package org.js.denisvieira.conferenceorganizer;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by denisvieira on 19/07/16.
 */
public class SchedulePagerAdapter extends FragmentPagerAdapter {

    public Context mContext;

    public SchedulePagerAdapter(Context context,FragmentManager fm) {

        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        return SchedulesActivity.PlaceholderFragment.newInstance(position + 1);

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Track A";
            case 1:
                return "Track B";
            case 2:
                return "Track C";
        }
        return null;
    }

}
