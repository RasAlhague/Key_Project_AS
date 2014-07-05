package com.rasalhague.key;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Doors
{
    private static final String          TAG    = "Doors";
    private              ArrayList<Door> doorAl = new ArrayList<Door>();

    public static Doors getInstance()
    {
        Gson gson = new Gson();
        Context context = MainActivity.getCONTEXT();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_preference_key),
                                                                           Context.MODE_PRIVATE);
        String doorALDeserialized = sharedPreferences.getString(context.getString(R.string.shared_pref_key_doors),
                                                                null);
        if (doorALDeserialized == null)
        {
            Log.e(TAG, "doorALSerialized = null, !!!");
        }

        Type type = new TypeToken<ArrayList<Door>>() {}.getType();
        ArrayList<Door> doorAL = gson.fromJson(doorALDeserialized, type);

        Doors doors = new Doors();
        doors.setDoorAl(doorAL);

        return doors;
    }

    public ArrayList<Door> getDoorAl()
    {
        return doorAl;
    }

    public void setDoorAl(ArrayList<Door> doorAl)
    {
        this.doorAl = doorAl;
    }

    public Door getDoorById(int position)
    {
        return doorAl.get(position);
    }

    public int getCount()
    {
        return doorAl.size();
    }

    private void addDoor(Door door)
    {
        doorAl.add(door);
    }
}
