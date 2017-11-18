package videotransition.linksu.com.videotransition;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by suful on 2017/11/18.
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    private String[] titles;

    public FragmentPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return HomePage.newInstance();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
