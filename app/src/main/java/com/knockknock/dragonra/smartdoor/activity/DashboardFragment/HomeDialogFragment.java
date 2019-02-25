package com.knockknock.dragonra.smartdoor.activity.DashboardFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;

public class HomeDialogFragment extends DialogFragment {

    // Use this instance of the interface to deliver action events
    NoticeDialogListener listener;

    public static HomeDialogFragment newInstance(int cardNumber) {
        Log.d("DIALOG_FRAGMENT", "newInstance");

        HomeDialogFragment frag = new HomeDialogFragment();
        Bundle args = new Bundle();
        args.putInt("cardNumber", cardNumber);
        frag.setArguments(args);
        return frag;
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        Log.d("DIALOG_FRAGMENT", "onAttach");
        super.onAttach(context);

        try {
            listener = (NoticeDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement NoticeDialogListener");
        }
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        Log.d("DIALOG_FRAGMENT", "onAttachFragment");

        super.onAttachFragment(childFragment);
        listener = (NoticeDialogListener) childFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("DIALOG_FRAGMENT", "onCreateDialog");

        final int cardNumber = getArguments().getInt("cardNumber");

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("What should I do ?")
                .setPositiveButton("Open", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        listener.onDialogPositiveClick(HomeDialogFragment.this, cardNumber);
                    }
                })
                .setNegativeButton("Lock", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        listener.onDialogNegativeClick(HomeDialogFragment.this, cardNumber);
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, int cardNumber);

        void onDialogNegativeClick(DialogFragment dialog, int cardNumber);
    }
}