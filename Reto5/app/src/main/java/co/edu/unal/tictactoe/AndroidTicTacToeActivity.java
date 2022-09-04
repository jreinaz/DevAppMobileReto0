package co.edu.unal.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class AndroidTicTacToeActivity extends AppCompatActivity {

    MediaPlayer mHumanMediaPlayer;
    MediaPlayer mComputerMediaPlayer;
    private boolean mGameOver;
    public TicTacToeGame mGame;
    // Buttons making up the board
    private BoardView mBoardView;
    // Various text displayed
    private TextView mInfoTextView;
    private DialogFragment newDiffFragment;
    private DialogFragment newExitFragment;

    // Listen for touches on the board
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
// Determine which cell was touched
            int col = (int) event.getX() / mBoardView.getBoardCellWidth();
            int row = (int) event.getY() / mBoardView.getBoardCellHeight();
            int pos = row * 3 + col;
            Log.i("posTouched: ",Integer.toString(pos));
            if (!mGameOver && setMove(TicTacToeGame.HUMAN_PLAYER, pos)){
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
// So we aren't notified of continued events when finger is moved
            return false;
        }
    };

    private boolean setMove(char player, int location) {
        if(player == mGame.HUMAN_PLAYER){
            if (mGame.setMove(player, location)) {
                mHumanMediaPlayer.start();
                mBoardView.invalidate(); // Redraw the board
                return true;
            }
        }else if(player == mGame.COMPUTER_PLAYER){
            if (mGame.setMove(player, location)) {
                mComputerMediaPlayer.start();
                mBoardView.invalidate(); // Redraw the board
                return true;
            }
        }
        return false;
    }

    // Set up the game board.
    private void startNewGame() {
        mGameOver = false;
        mGame.clearBoard();
        mBoardView.invalidate();
        // Human goes first
        //mInfoTextView.setText("You go first.");
        mInfoTextView.setText(R.string.first_human);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mInfoTextView = (TextView) findViewById(R.id.information);
        mGame = new TicTacToeGame();
        mBoardView = (BoardView) findViewById(R.id.board);
        mBoardView.setGame(mGame);
        mBoardView.setOnTouchListener(mTouchListener);
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

    @Override
    protected void onResume() {
        super.onResume();
        mHumanMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sword);
        mComputerMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.swish);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mHumanMediaPlayer.release();
        mComputerMediaPlayer.release();
    }

    /*private class ButtonClickListener implements View.OnClickListener {
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
    }*/
}