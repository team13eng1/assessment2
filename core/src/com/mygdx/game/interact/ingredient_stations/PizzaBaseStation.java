package com.mygdx.game.interact.ingredient_stations;

import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.InteractableBase;

/**
 * An ingredient station that gives a pizza base to the player
 */
public class PizzaBaseStation extends InteractableBase {

    //==========================================================\\
    //                      CONSTRUCTOR                         \\
    //==========================================================\\

    public PizzaBaseStation(float xPos, float yPos) {
        super(xPos, yPos, "station_pizza_base.png", IngredientName.PIZZA_BASE);
    }
}
