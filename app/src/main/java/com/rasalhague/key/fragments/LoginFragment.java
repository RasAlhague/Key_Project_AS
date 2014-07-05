package com.rasalhague.key.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.gson.Gson;
import com.rasalhague.key.ActionBarBehavior;
import com.rasalhague.key.Door;
import com.rasalhague.key.MainActivity;
import com.rasalhague.key.R;
import com.rasalhague.key.utility.Utility;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnProfileListener;

import java.util.ArrayList;

public class LoginFragment extends Fragment implements OnLoginListener
{
    private static final String TAG = "LoginFragment";
    SimpleFacebook simpleFacebook;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        simpleFacebook = SimpleFacebook.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_login, null);

        //Hide ActionBar when in login fragment
        ActionBarBehavior.getInstance()
                         .hideActionBar(getActivity());

        Button loginButton = (Button) view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //TODO SHOW AWAITING ANIMATION

                simpleFacebook.login(LoginFragment.this);
            }
        });

        return view;
    }

    @Override
    public void onLogin()
    {
        getUserInfoFromFB();
    }

    private void getUserInfoFromFB()
    {
        final Profile.Properties properties = new Profile.Properties.Builder().add(Profile.Properties.NAME)
                                                                              .add(Profile.Properties.EMAIL)
                                                                              .build();

        simpleFacebook.getProfile(properties, new OnProfileListener()
        {
            @Override
            public void onComplete(Profile response)
            {
                super.onComplete(response);

                saveUserInfo(response);
                getUserInfoFromTriolan();

                Utility.replaceFragment(getFragmentManager(), new MainFragment(), false);
            }

            @Override
            public void onFail(String reason)
            {
                super.onFail(reason);

                Log.w(TAG, reason);
            }
        });
    }

    private void saveUserInfo(Profile profile)
    {
        getActivity().getSharedPreferences(getString(R.string.shared_preference_key), Context.MODE_PRIVATE)
                     .edit()
                     .putString(getString(R.string.shared_pref_key_user_email), profile.getEmail())
                     .putString(getString(R.string.shared_pref_key_user_name), profile.getName())
                     .commit();
    }

    private void getUserInfoFromTriolan()
    {
        //TODO HTTP REQUEST REALIZATION

        Gson gson = new Gson();
        Context context = MainActivity.getCONTEXT();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_preference_key),
                                                                           Context.MODE_PRIVATE);

        ArrayList<Door> doorAL = new ArrayList<Door>();
        doorAL.add(new Door("Door 1"));
        doorAL.add(new Door("Door 2"));
        doorAL.add(new Door("Door 3"));

        sharedPreferences.edit()
                         .putString(context.getString(R.string.shared_pref_key_doors), gson.toJson(doorAL))
                         .commit();
    }

    @Override
    public void onNotAcceptingPermissions(Permission.Type type)
    {
        Log.w(TAG, String.format("You didn't accept %s permissions", type.name()));
    }

    @Override
    public void onThinking()
    {

    }

    @Override
    public void onException(Throwable throwable)
    {

    }

    @Override
    public void onFail(String reason)
    {

    }
}
