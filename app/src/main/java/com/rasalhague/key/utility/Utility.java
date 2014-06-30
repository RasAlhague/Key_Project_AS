package com.rasalhague.key.utility;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import com.rasalhague.key.R;

public class Utility
{
    public static void cleanBackStack(FragmentManager fragmentManager)
    {
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Replace fragment.
     *
     * @param fragmentManager
     *         the fragment manager
     * @param fragment
     *         the fragment
     * @param addToBackStack
     *         the add to back stack
     */
    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack)
    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.container, fragment);

        if (addToBackStack)
        {
            Log.wtf("", fragment.getClass().getName());
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }

        fragmentTransaction.commit();
    }
}
