package com.rasalhague.key.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rasalhague.key.ActionBarBehavior;
import com.rasalhague.key.R;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnProfileListener;

public class MainFragment extends Fragment
{
    private static final String TAG = "MainFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //Show ActionBar when in main fragment
        ActionBarBehavior.getInstance()
                         .showActionBar(getActivity());

        testFBConnection(rootView);

        ViewPager doorsViewPager = (ViewPager) rootView.findViewById(R.id.doorsViewPager);
        PagerAdapter pagerAdapter = new FragmentPagerAdapter(getActivity().getFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                return DoorsPagerItemFragment.newInstance(position);
            }

            @Override
            public int getCount()
            {
                return 10;
            }
        };

        doorsViewPager.setAdapter(pagerAdapter);
        doorsViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener());

        return rootView;
    }

    /**
     * Only for testing
     */
    private void testFBConnection(View rootView)
    {

        SimpleFacebook simpleFacebook = SimpleFacebook.getInstance();
        final TextView textView = (TextView) rootView.findViewById(R.id.textViewTest);
        textView.setText("");

        Profile.Properties properties = new Profile.Properties.Builder().add(Profile.Properties.NAME)
                                                                        .add(Profile.Properties.EMAIL)
                                                                        .build();
        simpleFacebook.getProfile(properties, new OnProfileListener()
        {
            @Override
            public void onComplete(Profile response)
            {
                super.onComplete(response);

                Log.w(TAG, response.getName());
                Log.w(TAG, response.getEmail());

                textView.append(response.getName());
                textView.append("\n" + response.getEmail());
            }

            @Override
            public void onFail(String reason)
            {
                super.onFail(reason);

                textView.append(reason);

            }
        });
    }
}
