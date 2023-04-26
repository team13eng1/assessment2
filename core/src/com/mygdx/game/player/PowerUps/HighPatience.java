package com.mygdx.game.player.PowerUps;

import com.mygdx.game.GameScreen;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.interact.InteractableBase;
import com.mygdx.game.interact.cooking_stations.CookingStation;
import com.mygdx.game.player.PlayerEngine;

/**
 A class representing a power-up that makes the customers wait at the counter longer
 Inherits from the PowerUpBase class.
 */
public class HighPatience extends PowerUpBase{
    private float patienceBonus = 10f;

    public HighPatience(float xPos, float yPos) {
        super(xPos, yPos, "powerup_patience.png", 10f, PlayerEngine.getMasterTime());
    }

    @Override
    public void startInteraction(){
        CustomerEngine.increasePatience(patienceBonus);
    }

    @Override
    public void endInteraction(){
    }
}
