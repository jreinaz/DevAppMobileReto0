package co.edu.unal.tictactoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class ExitDialog extends DialogFragment {
    private AndroidTicTacToeActivity activity;

    public ExitDialog(AndroidTicTacToeActivity activity){
        super();
        this.activity = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.quit_question)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        activity.finish();
                    }
                })
                .setNegativeButton(R.string.no, null);
        dialog = builder.create();
        return dialog;
    }
}
