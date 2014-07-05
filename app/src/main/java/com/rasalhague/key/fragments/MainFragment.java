package com.rasalhague.key.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rasalhague.key.ActionBarBehavior;
import com.rasalhague.key.DoorsViewPagerBehavior;
import com.rasalhague.key.R;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnProfileListener;

public class MainFragment extends Fragment
{
    private static final String TAG = "MainFragment";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.w(TAG, "onCreate");

        //Show ActionBar when in main fragment
        ActionBarBehavior.getInstance()
                         .showActionBar(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.w(TAG, "onCreateView");

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //TESTING
        //        testFBConnection(rootView);
        testSharedPreferenceUserInfo(rootView);

        DoorsViewPagerBehavior.getInstance()
                              .initDoorsViewPager(rootView, getFragmentManager());

        return rootView;
    }

    /**
     * Only for testing
     */
    private void testSharedPreferenceUserInfo(View rootView)
    {
        final TextView textView = (TextView) rootView.findViewById(R.id.textViewTest);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preference_key),
                                                                                 Context.MODE_PRIVATE);

        textView.setText("");
        textView.append(sharedPreferences.getString("user_name", "user_name"));
        textView.append("\n" + sharedPreferences.getString("user_email", "user_email"));
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
