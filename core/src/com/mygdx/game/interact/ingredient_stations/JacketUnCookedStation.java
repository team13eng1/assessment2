// TODO: 08/03/2023 change image of JacketUnCookedStation to the actual station

package com.mygdx.game.interact.ingredient_stations;

import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.InteractableBase;

/**
 * @author Thomas McCarthy
 *
 * An ingredient station that gives an uncooked jacket potato to the player
 */
public class JacketUnCookedStation extends InteractableBase {

    //==========================================================\\
    //                      CONSTRUCTOR                         \\
    //==========================================================\\

    public JacketUnCookedStation(float xPos, float yPos) {
        super(xPos, yPos, "station_.png", IngredientName.JACKET_POTATO_UNCOOKED);
    }
}
