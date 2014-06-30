package com.rasalhague.key.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;
import com.rasalhague.key.R;
import com.rasalhague.key.utility.Utility;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.listeners.OnLogoutListener;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private static final String TAG = "SettingsFragment";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        //pref_key_fb_logout OnClick
        Preference fbLogout = findPreference("pref_key_fb_logout");
        fbLogout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
        {
            @Override
            public boolean onPreferenceClick(Preference preference)
            {

                final boolean[] retB = new boolean[1];
                SimpleFacebook.getInstance()
                              .logout(new OnLogoutListener()
                              {
                                  @Override
                                  public void onLogout()
                                  {
                                      Log.w(TAG, "logout onLogout");
                                      retB[0] = true;

                                      FragmentManager fragmentManager = getFragmentManager();
                                      Utility.cleanBackStack(fragmentManager);

                                      FragmentTransaction transaction = fragmentManager.beginTransaction();
                                      transaction.replace(R.id.container, new LoginFragment());
                                      transaction.commit();
                                  }

                                  @Override
                                  public void onThinking()
                                  {
                                      Log.w(TAG, "logout onThinking");
                                      retB[0] = true;
                                  }

                                  @Override
                                  public void onException(Throwable throwable)
                                  {
                                      Log.w(TAG, "logout onException\n" + throwable.toString());
                                      retB[0] = false;
                                  }

                                  @Override
                                  public void onFail(String reason)
                                  {
                                      Log.w(TAG, "logout onFail\n" + reason);
                                      retB[0] = false;
                                  }
                              });

                return retB[0];
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        Log.w(TAG, "onSharedPreferenceChanged");
    }

    @Override
    public void onResume()
    {
        super.onResume();

        getPreferenceScreen().getSharedPreferences()
                             .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();

        getPreferenceScreen().getSharedPreferences()
                             .unregisterOnSharedPreferenceChangeListener(this);
    }
}
