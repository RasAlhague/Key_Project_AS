package com.rasalhague.key.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.rasalhague.key.ActionBarBehavior;
import com.rasalhague.key.R;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.listeners.OnLoginListener;

public class LoginFragment extends Fragment
{
    private static final String TAG = "LoginFragment";

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
                SimpleFacebook.getInstance()
                              .login(onLoginListener);
            }
        });

        return view;
    }

    OnLoginListener onLoginListener = new OnLoginListener()
    {
        @Override
        public void onLogin()
        {
            // change the state of the button or do whatever you want
            Log.i(TAG, "Logged in");

            getFragmentManager().beginTransaction()
                                .replace(R.id.container, new MainFragment())
                                .commit();
        }

        @Override
        public void onNotAcceptingPermissions(Permission.Type type)
        {
            // user didn't accept READ or WRITE permission
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
    };
}
