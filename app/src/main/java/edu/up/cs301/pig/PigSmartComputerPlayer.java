package edu.up.cs301.pig;

import android.util.Log;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigSmartComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigSmartComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if(!(info instanceof PigGameState) || ((PigGameState)info).getTurn() != playerNum)
        {
            return;
        }
        else
        {
            try{
                Thread.sleep(2000);
            } catch ( InterruptedException e){
                //do nothing
            }
            Log.i("Turn", "Computer");
            Log.i("Score", ""+((PigGameState)info).getTempScore());
            if (((PigGameState)info).getTempScore()>=20)
            {
                Log.i("Turn", "Hold");
                ((PigLocalGame)game).sendAction(new PigHoldAction(this));
            }
            else{
                Log.i("Turn", "Roll");
                ((PigLocalGame)game).sendAction(new PigRollAction(this));
            }
        }
    }//receiveInfo

}
