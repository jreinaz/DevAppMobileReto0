package co.edu.unal.tictactoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class DifficultyDialog extends DialogFragment {
    private AndroidTicTacToeActivity activity;

    public DifficultyDialog(AndroidTicTacToeActivity activity){
        super();
        this.activity = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.difficulty_choose);
        final CharSequence[] levels = {
                getResources().getString(R.string.difficulty_easy),
                getResources().getString(R.string.difficulty_harder),
                getResources().getString(R.string.difficulty_expert)};
        // TODO: Set selected, an integer (0 to n-1), for the Difficulty dialog.
        int selected = 2;
        // selected is the radio button that is selected when the Dialog Box appears.
        builder.setSingleChoiceItems(levels, selected,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // TODO: Set the diff level of mGame based on which item was selected.
                        if(item == 0){
                            activity.mGame.setDifficultyLevel(TicTacToeGame.DifficultyLevel.Easy);
                        }else if(item == 1){
                            activity.mGame.setDifficultyLevel(TicTacToeGame.DifficultyLevel.Harder);
                        }else if(item == 2){
                            activity.mGame.setDifficultyLevel(TicTacToeGame.DifficultyLevel.Expert);
                        }
                        dialog.dismiss(); // Close dialog
                    }
                });
        dialog = builder.create();
        return dialog;
    }
}
