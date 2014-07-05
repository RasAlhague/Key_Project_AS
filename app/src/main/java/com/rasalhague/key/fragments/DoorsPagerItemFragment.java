package com.rasalhague.key.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ImageView;
import com.rasalhague.key.R;
import com.rasalhague.key.fragments.Dialogs.DoorDialogFragmentLvl1;

import java.util.Random;

public class DoorsPagerItemFragment extends Fragment
{
    private static final String TAG                   = "DoorsPagerItemFragment";
    static final         String ARGUMENT_PAGE_NUMBER  = "arg_page_number";
    static final         String SAVE_PAGE_NUMBER      = "save_page_number";
    static final         int    REQUEST_IMAGE_CAPTURE = 1;

    int pageNumber;
    int backColor;

    ImageView doorImageView;

    /**
     * New instance of new DoorsPagerItemFragment
     *
     * @param page
     *         That number will associate with each DoorsPagerItemFragment to generate specific content
     *
     * @return the doors pager item fragment
     */
    public static DoorsPagerItemFragment newInstance(int page)
    {
        DoorsPagerItemFragment doorsPagerItemFragment = new DoorsPagerItemFragment();

        //TODO set door from HTTP request

        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        doorsPagerItemFragment.setArguments(arguments);

        return doorsPagerItemFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + pageNumber);

        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

        //for testing
        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_doors_pager_item, null);

        doorImageView = (ImageView) view.findViewById(R.id.doorImageView);
        doorImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Log.d(TAG, "doorImageView onClick");

            }
        });

        /**
         * Dialog menu
         */
        doorImageView.setLongClickable(true);
        doorImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v)
            {

                new DoorDialogFragmentLvl1().show(getFragmentManager(), null);

                return true;
            }
        });


        //useless context menu
        //prefer dialog
//        registerForContextMenu(doorImageView);

        view.setBackgroundColor(backColor);

        return view;
    }

    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null)
        {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        Log.w(TAG, v.toString());

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.fragment_doors_pager_item_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        if (!getUserVisibleHint())
        {
            return false;
        }

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId())
        {
            case R.id.menu_change_name:

                Log.w(TAG, item.toString());
                return true;

            case R.id.menu_change_view:

                Log.w(TAG, item.toString());
                dispatchTakePictureIntent();

                return true;

            case R.id.menu_default_configs:

                Log.w(TAG, item.toString());
                return true;

            default:
                return true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null)
        {
            Log.w(TAG, data.toString());
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE && data != null/*resultCode == RESULT_OK*/)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            doorImageView.setImageBitmap(imageBitmap);
        }
    }
}
