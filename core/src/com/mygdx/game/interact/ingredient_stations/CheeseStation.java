// TODO: 08/03/2023 change image of  to the actual station

package com.mygdx.game.interact.ingredient_stations;

import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.InteractableBase;

/**
 * @author Thomas McCarthy
 *
 * An ingredient station that gives cheese to the player
 */
public class CheeseStation extends InteractableBase {

    //==========================================================\\
    //                      CONSTRUCTOR                         \\
    //==========================================================\\

    public CheeseStation(float xPos, float yPos) {
        super(xPos, yPos, "station_.png", IngredientName.CHEESE);
    }
}
