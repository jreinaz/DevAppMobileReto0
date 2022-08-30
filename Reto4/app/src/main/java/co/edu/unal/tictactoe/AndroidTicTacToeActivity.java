package co.edu.unal.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class AndroidTicTacToeActivity extends AppCompatActivity {

    private boolean mGameOver;
    public TicTacToeGame mGame;
    // Buttons making up the board
    private Button mBoardButtons[];
    // Various text displayed
    private TextView mInfoTextView;
    private DialogFragment newDiffFragment;
    private DialogFragment newExitFragment;

    private void setMove(char player, int location) {
        mGame.setMove(player, location);
        mBoardButtons[location].setEnabled(false);
        mBoardButtons[location].setText(String.valueOf(player));
        if (player == TicTacToeGame.HUMAN_PLAYER)
            mBoardButtons[location].setTextColor(Color.rgb(0, 200, 0));
        else
            mBoardButtons[location].setTextColor(Color.rgb(200, 0, 0));
    }

    // Set up the game board.
    private void startNewGame() {
        mGameOver = false;
        mGame.clearBoard();
        // Reset all buttons
        for (int i = 0; i < mBoardButtons.length; i++) {
            mBoardButtons[i].setText("");
            mBoardButtons[i].setEnabled(true);
            mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
        }
        // Human goes first
        //mInfoTextView.setText("You go first.");
        mInfoTextView.setText(R.string.first_human);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBoardButtons = new Button[9];
        mBoardButtons[0] = (Button) findViewById(R.id.one);
        mBoardButtons[1] = (Button) findViewById(R.id.two);
        mBoardButtons[2] = (Button) findViewById(R.id.three);
        mBoardButtons[3] = (Button) findViewById(R.id.four);
        mBoardButtons[4] = (Button) findViewById(R.id.five);
        mBoardButtons[5] = (Button) findViewById(R.id.six);
        mBoardButtons[6] = (Button) findViewById(R.id.seven);
        mBoardButtons[7] = (Button) findViewById(R.id.eight);
        mBoardButtons[8] = (Button) findViewById(R.id.nine);
        mInfoTextView = (TextView) findViewById(R.id.information);
        mGame = new TicTacToeGame();
        startNewGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game:
                startNewGame();
                return true;
            case R.id.ai_difficulty:
                newDiffFragment = new DifficultyDialog(this);
                newDiffFragment.show(getSupportFragmentManager(), "dialog");
                return true;
            case R.id.quit:
                newExitFragment = new ExitDialog(this);
                newExitFragment.show(getSupportFragmentManager(), "dialog");
                return true;
        }
        return false;
    }

    private class ButtonClickListener implements View.OnClickListener {
        int location;
        public ButtonClickListener(int location) {
            this.location = location;
        }
        public void onClick(View view) {
            if (mBoardButtons[location].isEnabled() && mGameOver == false) {
                setMove(TicTacToeGame.HUMAN_PLAYER, location);
                // If no winner yet, let the computer make a move
                int winner = mGame.checkForWinner();
                if (winner == 0) {
                    //mInfoTextView.setText("It's Android's turn.");
                    mInfoTextView.setText(R.string.turn_computer);
                    int move = mGame.getComputerMove();
                    setMove(TicTacToeGame.COMPUTER_PLAYER, move);
                    winner = mGame.checkForWinner();
                }
                if (winner == 0){
                    //mInfoTextView.setText("It's your turn.");
                    mInfoTextView.setText(R.string.turn_human);
                }else if (winner == 1){
                    mGameOver = true;
                    //mInfoTextView.setText("It's a tie!");
                    mInfoTextView.setText(R.string.result_tie);
                }else if (winner == 2){
                    mGameOver = true;
                    //mInfoTextView.setText("You won!");
                    mInfoTextView.setText(R.string.result_human_wins);
                }else{
                    mGameOver = true;
                    //mInfoTextView.setText("Android won!");
                    mInfoTextView.setText(R.string.result_computer_wins);
                }
            }
        }
    }
}