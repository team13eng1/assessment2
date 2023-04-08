package com.mygdx.game.interact.ingredient_stations;

import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.InteractableBase;

/**
 * @author Thomas McCarthy
 *
 * An ingredient station that gives an uncooked potato to the player
 */
public class PotatoRawStation extends InteractableBase {

    //==========================================================\\
    //                      CONSTRUCTOR                         \\
    //==========================================================\\

    public PotatoRawStation(float xPos, float yPos) {
        super(xPos, yPos, "station_potato.png", IngredientName.POTATO_UNCOOKED);
    }
}
