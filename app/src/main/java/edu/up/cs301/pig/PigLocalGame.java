package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    PigGameState officialState;

    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        super();
        officialState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return officialState.getTurn()==playerIdx;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof PigHoldAction){
            officialState.setScore(officialState.getTempScore()+
                    officialState.getScore(officialState.getTurn()),
                    officialState.getTurn());
            officialState.setTempScore(0);
            officialState.setTurn((officialState.getTurn()+1)%players.length);
            return true;
        } else if(action instanceof PigRollAction){
            int dice = (int) (Math.random()*6)+1;
            Log.i("Roll", ""+dice);
            officialState.setDieVal(dice);
            if(dice!=1){
                officialState.incrementTempScore(dice);

            }else{
                officialState.setTempScore(0);
                officialState.setTurn((officialState.getTurn()+1)%players.length);
            }
            return true;
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new PigGameState(officialState));
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        for(int i=0; i<players.length; i++){
            if(officialState.getScore(i)>=50){
                return playerNames[i]+" has won with score of "+officialState.getScore(i);
            }
        }
        return null;
    }

}// class PigLocalGame
