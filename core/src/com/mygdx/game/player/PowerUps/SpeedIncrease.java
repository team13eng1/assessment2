package com.mygdx.game.player.PowerUps;

import com.mygdx.game.GameScreen;
import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.interact.InteractableBase;
import com.mygdx.game.interact.cooking_stations.CookingStation;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;

import java.util.ArrayList;

public class SpeedIncrease extends PowerUpBase {
    private float baseSpeed = 150f;
    private float newSpeed = 250f;

    public SpeedIncrease(float xPos, float yPos) {
        super(xPos, yPos, "powerup_speed.png", 10f, PlayerEngine.getMasterTime());
    }

    @Override
    public void startInteraction() {
        ArrayList<Player> allChefs = PlayerEngine.getAllChefs();
        for (Player chef : allChefs) {
            chef.setSpeed(newSpeed);
        }
    }

    @Override
    public void endInteraction() {
        ArrayList<Player> allChefs = PlayerEngine.getAllChefs();
        for (Player chef : allChefs) {
            chef.setSpeed(baseSpeed);
        }
    }
}
