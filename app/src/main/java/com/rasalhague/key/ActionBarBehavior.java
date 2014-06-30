package com.rasalhague.key;

import android.app.ActionBar;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.rasalhague.key.fragments.SettingsFragment;
import com.rasalhague.key.utility.Utility;

public class ActionBarBehavior
{
    private static final String            TAG         = "ActionBarBehavior";
    private static       ActionBarBehavior ourInstance = new ActionBarBehavior();

    public static ActionBarBehavior getInstance()
    {
        return ourInstance;
    }

    private ActionBarBehavior()
    {
    }

    public void initActionBar(Activity activity)
    {
        setUpActionBar(activity);
    }

    public void showActionBar(Activity activity)
    {
        try
        {
            activity.getActionBar()
                    .show();
        }
        catch (NullPointerException e)
        {
            Log.w(TAG, e.getMessage());
        }
    }

    public void hideActionBar(Activity activity)
    {
        try
        {
            activity.getActionBar()
                    .hide();
        }
        catch (NullPointerException e)
        {
            Log.w(TAG, e.getMessage());
        }
    }

    private void setUpActionBar(final Activity activity)
    {
        /**
         * SET UP ACTION BAR
         */
        ActionBar actionBar = activity.getActionBar();

        assert actionBar != null;
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar);

        /**
         * CLOSE BUTTON EVENT
         */
        actionBar.getCustomView()
                 .findViewById(R.id.buttonClose)
                 .setOnClickListener(new View.OnClickListener()
                 {
                     @Override
                     public void onClick(View v)
                     {
                         activity.finish();
                         System.exit(0);
                     }
                 });

        /**
         * CONFIG BUTTON EVENT
         */
        actionBar.getCustomView()
                 .findViewById(R.id.buttonConfig)
                 .setOnClickListener(new View.OnClickListener()
                 {
                     @Override
                     public void onClick(View v)
                     {

                         Utility.replaceFragment(activity.getFragmentManager(), new SettingsFragment(), true);
                     }
                 });
    }
}
