package com.mygdx.game.interact.special_stations;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.interact.InteractableBase;
import com.mygdx.game.player.PlayerEngine;


/**
 * A station where the 3rd chef can be bought for coins
 */
public class BuyChefStation extends InteractableBase {

    public Texture chefLockedTexture;
    public int chefCost;


    //==========================================================\\
    //                      CONSTRUCTOR                         \\
    //==========================================================\\

    public BuyChefStation(float xPos, float yPos) {
        super(xPos, yPos, "counter.png");
        chefLockedTexture = new Texture("locked_chef.png");
        chefCost = 20;
    }


    //==========================================================\\
    //                      INTERACTION                         \\
    //==========================================================\\

    @Override
    public void handleInteraction() {
        if (PlayerEngine.getCoins() >= chefCost) {
            PlayerEngine.loseCoins(chefCost);
            InteractEngine.replaceWithCounter(this);
            PlayerEngine.addNewChef();
        }
    }
}
