package com.rasalhague.key;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import com.rasalhague.key.fragments.LoginFragment;
import com.rasalhague.key.fragments.MainFragment;
import com.rasalhague.key.utility.Utility;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;

public class MainActivity extends Activity
{
    private static final String TAG = "MainActivity";
    private static Context CONTEXT;

    SimpleFacebook mSimpleFacebook;

    public static Context getCONTEXT()
    {
        return CONTEXT;
    }

    public static void setCONTEXT(Context CONTEXT)
    {
        MainActivity.CONTEXT = CONTEXT;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCONTEXT(this);

        Log.d(TAG, "onCreate");

        Permission[] permissions = new Permission[]{Permission.USER_PHOTOS,
                                                    Permission.EMAIL,
                                                    Permission.PUBLISH_ACTION};
        initSimpleFacebook(permissions);

        if (mSimpleFacebook.isLogin())
        {
            Log.d(TAG, "Session isOpened");

            if (savedInstanceState == null)
            {
                Utility.replaceFragment(getFragmentManager(), new MainFragment(), false);
            }
        }
        else
        {
            Log.d(TAG, "Session is Closed");

            if (savedInstanceState == null)
            {
                Utility.replaceFragment(getFragmentManager(), new LoginFragment(), false);
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
    }
}
