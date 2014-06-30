package com.rasalhague.key.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rasalhague.key.R;

import java.util.Random;

public class DoorsPagerItemFragment extends Fragment
{
    static DoorsPagerItemFragment newInstance(int page)
    {
        DoorsPagerItemFragment doorsPagerItemFragment = new DoorsPagerItemFragment();

        //TODO set door from HTTP request

        return doorsPagerItemFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_doors_pager_item, null);

        Random rnd = new Random();
        int backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        view.setBackgroundColor(backColor);

        return view;
    }
}
