package com.mygdx.game.player.PowerUps;

import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.player.PlayerEngine;

public class GainReputationPoint extends PowerUpBase{

    public GainReputationPoint(float xPos, float yPos) {
        super(xPos, yPos, "powerup_extra_rep", 0.5f, PlayerEngine.getMasterTime());
    }

    @Override
    public void startInteraction(){
        CustomerEngine.gainRepPoint();
    }

    @Override
    public void endInteraction(){
    }
}
