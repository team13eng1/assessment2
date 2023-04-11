package com.mygdx.game.player.PowerUps;

import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.special_stations.CustomerCounter;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;

public class AutoCompleteDish extends PowerUpBase{

    public AutoCompleteDish(float xPos, float yPos) {
        super(xPos, yPos, "powerup_speed2.png", 0.5f, PlayerEngine.getMasterTime());
    }

    @Override
    public void startInteraction(){
        if (CustomerEngine.getRecentCustomer() == null){
            return;
        }
        Customer mostRecentCustomer = CustomerEngine.getRecentCustomer();
        CustomerCounter mostRecentCounter = mostRecentCustomer.getCounter();
        Player activeChef = PlayerEngine.getActiveChef();
        activeChef.getIngredientStack().push(mostRecentCounter.getRequiredIngredient());
    }

    @Override
    public void endInteraction(){
    }
}
