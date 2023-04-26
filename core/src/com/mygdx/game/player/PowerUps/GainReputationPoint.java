package com.mygdx.game.player.PowerUps;

import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.player.PlayerEngine;

/**
 A class representing a power-up that gives the user a reputation point
 Inherits from the PowerUpBase class.
 */

public class GainReputationPoint extends PowerUpBase{

    public GainReputationPoint(float xPos, float yPos) {
        super(xPos, yPos, "powerup_extra_rep.png", 0.5f, PlayerEngine.getMasterTime());
    }

    @Override
    public void startInteraction(){
        CustomerEngine.gainRepPoint();
    }

}
