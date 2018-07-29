package be.belgiplast.dashboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

    private List<NamedFragment> fragments = new ArrayList<>();

    public DemoCollectionPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new DemoObjectFragment());
        fragments.add(new ScrumBoardFragment());
        fragments.add(new ScrumOverviewFragment());
        fragments.add(new ScrumDetailsFragment());
        fragments.add(new ScrumProjectsFragment());
    }



    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getName();
    }

}
