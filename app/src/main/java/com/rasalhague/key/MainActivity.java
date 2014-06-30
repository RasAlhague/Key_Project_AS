package com.rasalhague.key;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import com.facebook.Session;
import com.rasalhague.key.fragments.LoginFragment;
import com.rasalhague.key.fragments.MainFragment;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;

public class MainActivity extends Activity
{
    private static final String TAG = "MainActivity";

    SimpleFacebook mSimpleFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.w(TAG, "onCreate");

        //SetUp Default properties
//        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        Permission[] permissions = new Permission[]{Permission.USER_PHOTOS,
                                                    Permission.EMAIL,
                                                    Permission.PUBLISH_ACTION};
        initSimpleFacebook(permissions);

        if (mSimpleFacebook.isLogin())
        {
            Log.w(TAG, "Session isOpened");

            if (savedInstanceState == null)
            {
                getFragmentManager().beginTransaction()
                                    .replace(R.id.container, new MainFragment())
//                                    .addToBackStack(null)
                                    .commit();
            }
        }
        else
        {
            Log.w(TAG, String.format("You didn't accept %s permissions", "Session is Closed"));

            if (savedInstanceState == null)
            {
                getFragmentManager().beginTransaction()
                                    .replace(R.id.container, new LoginFragment())
//                                    .addToBackStack(null)
                                    .commit();
            }
        }
    }

    /**
     * Do init like here https://github.com/sromku/android-simple-facebook#configuration
     *
     * @param permissions
     */
    private void initSimpleFacebook(Permission[] permissions)
    {
        SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder().setAppId("504683099631966")
                                                                                             .setPermissions(permissions)
                                                                                             .build();

        SimpleFacebook.setConfiguration(configuration);
        mSimpleFacebook = SimpleFacebook.getInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        ActionBarBehavior.getInstance().initActionBar(this);

        // Inflate the menu; this adds items to the action bar if it is present.
        //        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        mSimpleFacebook.onActivityResult(this, requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        Session.getActiveSession()
               .onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.w(TAG, "onStart");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.w(TAG, "onResume");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.w(TAG, "onPause");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.w(TAG, "onStop");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.w(TAG, "onDestroy");
    }
}
