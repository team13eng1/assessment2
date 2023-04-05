package com.mygdx.game.interact.special_stations;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.interact.InteractableBase;
import com.mygdx.game.player.PlayerEngine;


/**
 * @author Thomas McCarthy
 *
 * A counter is used for placing ingredients. It has no processes.
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
