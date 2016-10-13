package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by liang19 on 10/12/2016.
 */
public class PigGameState extends GameState {
    private int turn;
    private int score[];
    private int tempScore;
    private int dieVal;


    public PigGameState(){
        super();
        turn=0;
        score = new int[2];
        score[0]=0;
        score[1]=0;
        tempScore=0;
        dieVal=1;

    }

    public  PigGameState(PigGameState original){
        super();
        turn = original.turn;
        score = new int[2];
        score[0]=original.score[0];
        score[1]=original.score[1];
        tempScore=original.tempScore;
        dieVal=original.dieVal;
    }

    public int getScore(int playerIndex) {
        return score[playerIndex];
    }

    public int getTempScore() {
        return tempScore;
    }

    public int getDieVal() {
        return dieVal;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setScore(int score, int playerIndex) {
        this.score[playerIndex] = score;
    }

    public void incrementTempScore(int score) {
        this.tempScore += score;
    }

    public void setTempScore(int tempScore) {
        this.tempScore = tempScore;
    }

    public void setDieVal(int dieVal) {
        this.dieVal = dieVal;
    }
}
