package com.rasalhague.key;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.rasalhague.key.fragments.DoorsPagerItemFragment;

public class DoorsViewPagerBehavior
{
    private static DoorsViewPagerBehavior ourInstance = new DoorsViewPagerBehavior();
    private Doors doors = Doors.getInstance();

    public static DoorsViewPagerBehavior getInstance()
    {
        return ourInstance;
    }

    private DoorsViewPagerBehavior()
    {
    }

    /**
     * Entry point
     *  @param rootView
     * @param fragmentManager
     */
    public void initDoorsViewPager(View rootView, FragmentManager fragmentManager)
    {
        ViewPager doorsViewPager = (ViewPager) rootView.findViewById(R.id.doorsViewPager);
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) rootView.findViewById(R.id.pagerTitleStrip);

        pagerTitleStrip.setTextSize(0, 50);

        PagerAdapter pagerAdapter = new MyFragmentPagerAdapter(fragmentManager, 100);

        doorsViewPager.setAdapter(pagerAdapter);
        doorsViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter
    {
        private int itemCount;

        public MyFragmentPagerAdapter(FragmentManager fm, int itemCount)
        {
            super(fm);

            this.itemCount = itemCount;
        }

        @Override
        public Fragment getItem(int position)
        {
            return DoorsPagerItemFragment.newInstance(position);
        }

        @Override
        public int getCount()
        {
            return doors.getCount();
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            String titleForPosition = generatePageTitleForPosition(position);

            if (titleForPosition == null)
            {
                return "Title " + position;
            }

            return titleForPosition;
        }
    }

    private String generatePageTitleForPosition(int position)
    {
        Door doorById = doors.getDoorById(position);
        String titleForPosition = doorById.getName();

        return titleForPosition;
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener
    {
        @Override
        public void onPageScrolled(int i, float v, int i2)
        {

        }

        @Override
        public void onPageSelected(int i)
        {

        }

        @Override
        public void onPageScrollStateChanged(int i)
        {

        }
    }
}
