package com.mygdx.game.interact.ingredient_stations;

import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.InteractableBase;

/**
 * An ingredient station that gives cheese to the player
 */
public class CheeseStation extends InteractableBase {

    //==========================================================\\
    //                      CONSTRUCTOR                         \\
    //==========================================================\\

    public CheeseStation(float xPos, float yPos) {
        super(xPos, yPos, "station_cheese.png", IngredientName.CHEESE);
    }
}
