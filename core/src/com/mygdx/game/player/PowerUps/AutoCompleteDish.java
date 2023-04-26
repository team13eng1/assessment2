package com.mygdx.game.player.PowerUps;

import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.special_stations.CustomerCounter;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;

/**
 A class representing a power-up that gives a chef a completed dish
 Inherits from the PowerUpBase class.
 */

public class AutoCompleteDish extends PowerUpBase{

    public AutoCompleteDish(float xPos, float yPos) {
        super(xPos, yPos, "powerup_speed2.png", 0.5f, PlayerEngine.getMasterTime());
    }

    @Override
    public void startInteraction(){
        Player activeChef = PlayerEngine.getActiveChef();

        if (!CustomerEngine.getRecentCustomer().atCounter){
            return;
        }
        Customer mostRecentCustomer = CustomerEngine.getRecentCustomer();
        CustomerCounter mostRecentCounter = mostRecentCustomer.getCounter();
        activeChef.getIngredientStack().push(mostRecentCounter.getRequiredIngredient());
    }

    @Override
    public void endInteraction(){
    }
}
