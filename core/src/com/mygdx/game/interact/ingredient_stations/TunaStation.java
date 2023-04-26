package com.mygdx.game.interact.ingredient_stations;

import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.InteractableBase;

/**
 * An ingredient station that gives tuna to the player
 */
public class TunaStation extends InteractableBase {

    //==========================================================\\
    //                      CONSTRUCTOR                         \\
    //==========================================================\\

    public TunaStation(float xPos, float yPos) {
        super(xPos, yPos, "station_tuna.png", IngredientName.TUNA);
    }
}
