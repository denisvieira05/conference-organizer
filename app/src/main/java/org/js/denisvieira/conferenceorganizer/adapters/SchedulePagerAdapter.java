package org.js.denisvieira.conferenceorganizer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.js.denisvieira.conferenceorganizer.fragments.ListContentFragment;

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
        // Return a ListContentFragment (defined as a static inner class below).

        return ListContentFragment.newInstance(position);

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

       return "Track "+(position+1);
    }

}
