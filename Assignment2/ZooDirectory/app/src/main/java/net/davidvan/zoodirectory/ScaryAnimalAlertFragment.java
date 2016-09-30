package net.davidvan.zoodirectory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by David on 9/30/2016.
 */

public class ScaryAnimalAlertFragment extends DialogFragment {

    public interface ScaryAnimalAlertListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    ScaryAnimalAlertListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("This animal is VERY scary! Do you want to proceed?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Show!
                        listener.onDialogPositiveClick(ScaryAnimalAlertFragment.this);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Don't show!
                        listener.onDialogNegativeClick(ScaryAnimalAlertFragment.this);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (ScaryAnimalAlertListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ScaryAnimalAlertListener");
        }
    }

}
