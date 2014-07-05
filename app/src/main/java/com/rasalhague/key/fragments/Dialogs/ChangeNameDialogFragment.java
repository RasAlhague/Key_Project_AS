package com.rasalhague.key.fragments.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.rasalhague.key.R;
import com.rasalhague.key.utility.Utility;

public class ChangeNameDialogFragment extends DialogFragment
{
    private static final String TAG = "ChangeNameDialogFragment";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_dialog_change_name, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
               .setPositiveButton(getString(R.string.fragment_dialog_positive_button),
                                  new DialogInterface.OnClickListener()
                                  {
                                      @Override
                                      public void onClick(DialogInterface dialog, int which)
                                      {
                                          /**
                                           * door name change logic
                                           * likely, will use listener
                                           */

                                      }
                                  }
               );

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        super.onDismiss(dialog);

        Utility.hideKeyboard(getActivity());

        //return to the previous dialog
        new DoorDialogFragmentLvl1().show(getFragmentManager(), null);
    }
}
