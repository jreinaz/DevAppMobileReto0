package co.edu.unal.tictactoe;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {

    private String id;
    private int playerOneId;
    private int playerTwoId;
    private char mBoard[] = {OPEN_SPOT,OPEN_SPOT,OPEN_SPOT,OPEN_SPOT,OPEN_SPOT,OPEN_SPOT,OPEN_SPOT,OPEN_SPOT,OPEN_SPOT};
    public static final int BOARD_SIZE = 9;
    public static final char PLAYER_ONE = 'X';
    public static final char PLAYER_TWO = 'O';
    public static final char OPEN_SPOT = ' ';
/*
    public char getBoardOccupant(int i) {
        char ans = ' ';
        if(mBoard[i] == PLAYER_ONE){
            ans = PLAYER_ONE;
        }else if(mBoard[i] == PLAYER_TWO){
            ans = PLAYER_TWO;
        }
        return ans;
    }

    // The computer's difficulty levels
    public enum DifficultyLevel {Easy, Harder, Expert};
    // Current difficulty level
    private DifficultyLevel mDifficultyLevel = DifficultyLevel.Expert;

    private Random mRand;*/

    public TicTacToeGame(String id, int playerOneId) {
        // Seed the random number generator
        this.id = id;
        this.playerOneId = playerOneId;
    }
/*
    public DifficultyLevel getDifficultyLevel() {
        return mDifficultyLevel;
    }
    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        mDifficultyLevel = difficultyLevel;
    }

    public void clearBoard(){
        for(int i = 0; i < BOARD_SIZE; i++){
            mBoard[i] = OPEN_SPOT;
        }
        return;
    }

    public boolean setMove(char player, int location){
        mBoard[location] = player;
        return true;
    }

    // Check for a winner.  Return
    //  0 if no winner or tie yet
    //  1 if it's a tie
    //  2 if X won
    //  3 if O won
    public int checkForWinner() {

        // Check horizontal wins
        for (int i = 0; i <= 6; i += 3)	{
            if (mBoard[i] == PLAYER_ONE &&
                    mBoard[i+1] == PLAYER_ONE &&
                    mBoard[i+2]== PLAYER_ONE)
                return 2;
            if (mBoard[i] == PLAYER_TWO &&
                    mBoard[i+1]== PLAYER_TWO &&
                    mBoard[i+2] == PLAYER_TWO)
                return 3;
        }

        // Check vertical wins
        for (int i = 0; i <= 2; i++) {
            if (mBoard[i] == PLAYER_ONE &&
                    mBoard[i+3] == PLAYER_ONE &&
                    mBoard[i+6]== PLAYER_ONE)
                return 2;
            if (mBoard[i] == PLAYER_TWO &&
                    mBoard[i+3] == PLAYER_TWO &&
                    mBoard[i+6]== PLAYER_TWO)
                return 3;
        }

        // Check for diagonal wins
        if ((mBoard[0] == PLAYER_ONE &&
                mBoard[4] == PLAYER_ONE &&
                mBoard[8] == PLAYER_ONE) ||
                (mBoard[2] == PLAYER_ONE &&
                        mBoard[4] == PLAYER_ONE &&
                        mBoard[6] == PLAYER_ONE))
            return 2;
        if ((mBoard[0] == PLAYER_TWO &&
                mBoard[4] == PLAYER_TWO &&
                mBoard[8] == PLAYER_TWO) ||
                (mBoard[2] == PLAYER_TWO &&
                        mBoard[4] == PLAYER_TWO &&
                        mBoard[6] == PLAYER_TWO))
            return 3;

        // Check for tie
        for (int i = 0; i < BOARD_SIZE; i++) {
            // If we find a number, then no one has won yet
            if (mBoard[i] != PLAYER_ONE && mBoard[i] != PLAYER_TWO)
                return 0;
        }

        // If we make it through the previous loop, all places are taken, so it's a tie
        return 1;
    }

    public int getWinningMove(){
        // First see if there's a move O can make to win
        int move = -1;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] != PLAYER_ONE && mBoard[i] != PLAYER_TWO) {
                char curr = mBoard[i];
                //mBoard[i] = PLAYER_TWO;
                setMove(PLAYER_TWO, i);
                if (checkForWinner() == 3) {
                    //System.out.println("Computer is moving to " + (i + 1));
                    move = i;
                }else{
                    //mBoard[i] = curr;
                    setMove(curr, i);
                }
            }
        }
        return move;
    }

    public int getBlockingMove(){
        int move = -1;
        // See if there's a move O can make to block X from winning
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] != PLAYER_ONE && mBoard[i] != PLAYER_TWO) {
                char curr = mBoard[i];   // Save the current number
                //mBoard[i] = PLAYER_ONE;
                setMove(PLAYER_ONE, i);
                if (checkForWinner() == 2) {
                    //mBoard[i] = PLAYER_TWO;
                    setMove(PLAYER_TWO, i);
                    //System.out.println("Computer is moving to " + (i + 1));
                    move = i;
                }else{
                    //mBoard[i] = curr;
                    setMove(curr, i);
                }
            }
        }
        return move;
    }

    public int getRandomMove(){
        int move;
        // Generate random move
        do
        {
            move = mRand.nextInt(BOARD_SIZE);
        } while (mBoard[move] == PLAYER_ONE || mBoard[move] == PLAYER_TWO);

        //System.out.println("Computer is moving to " + (move + 1));

        //mBoard[move] = PLAYER_TWO;
        setMove(PLAYER_TWO, move);
        return move;
    }

    public int getComputerMove() {
        int move = -1;
        if (mDifficultyLevel == DifficultyLevel.Easy)
            move = getRandomMove();
        else if (mDifficultyLevel == DifficultyLevel.Harder) {
            move = getWinningMove();
            if (move == -1)
                move = getRandomMove();
        }
        else if (mDifficultyLevel == DifficultyLevel.Expert) {
// Try to win, but if that's not possible, block.
// If that's not possible, move anywhere.
            move = getWinningMove();
            if (move == -1)
                move = getBlockingMove();
            if (move == -1)
                move = getRandomMove();
        }
        return move;
    }

    public char[] getBoardState() {
        return mBoard;
    }

    public void setBoardState(char[] board){
        this.mBoard = board;
    }*/
}