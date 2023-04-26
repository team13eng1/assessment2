package com.mygdx.game.player.PowerUps;

import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.interact.InteractableBase;
import com.mygdx.game.interact.cooking_stations.CookingStation;
import com.mygdx.game.player.PlayerEngine;

/**
 A class representing a power-up that prevents cooking stations from burning food.
 Inherits from the PowerUpBase class.
 */


public class NoBurning extends PowerUpBase{
    private float baseBurnTime = 0;

    public NoBurning(float xPos, float yPos) {
        super(xPos, yPos, "powerup_burning.png", 10f, PlayerEngine.getMasterTime());
    }

    @Override
    public void startInteraction(){
        InteractableBase[] stations = InteractEngine.getStations();
            for (InteractableBase station : stations){
                if (station instanceof CookingStation){
                    baseBurnTime = station.getBurnTime();
                    station.setBurntTime(1000f);
                }
            }
    }

    @Override
    public void endInteraction(){
        InteractableBase[] stations = InteractEngine.getStations();
        for (InteractableBase station : stations){
            if (station instanceof CookingStation){
                station.setBurntTime(baseBurnTime);
            }
        }

    }
}
