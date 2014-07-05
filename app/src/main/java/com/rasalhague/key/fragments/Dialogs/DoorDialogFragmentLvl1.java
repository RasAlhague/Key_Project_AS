package com.rasalhague.key.fragments.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import com.rasalhague.key.R;
import com.rasalhague.key.utility.Utility;

public class DoorDialogFragmentLvl1 extends DialogFragment
{
    private static final String TAG = "DoorDialogFragmentLvl1";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setItems(new CharSequence[]{getString(R.string.context_menu_change_name),
                                            getString(R.string.context_menu_change_view),
                                            getString(R.string.context_menu_default_configs)},
                         new DialogInterface.OnClickListener()
                         {
                             @Override
                             public void onClick(DialogInterface dialog, int which)
                             {
                                 switch (which)
                                 {
                                     case 0:
                                     {
                                         new ChangeNameDialogFragment().show(getFragmentManager(), null);

                                         Utility.showKeyboard(getActivity());

                                         break;
                                     }
                                     case 1:
                                     {
                                         Log.d(TAG, "case 1:");

                                         break;
                                     }
                                     case 2:
                                     {
                                         Log.d(TAG, "case 2:");

                                         break;
                                     }
                                     default:
                                     {
                                         Log.w(TAG, "default:");

                                         break;
                                     }
                                 }
                             }
                         }
        );

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
