package com.mygdx.game.interact.cooking_stations;

import com.mygdx.game.ingredient.IngredientMap;
import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.InteractableBase;

/**
 * Station for cutting, inherits properties from the InteractableBase class
 */
public class CuttingStation extends InteractableBase {

    static public IngredientMap ingredientMap = new IngredientMap() {{

        put(IngredientName.LETTUCE_UNCUT, IngredientName.LETTUCE_CUT);
        put(IngredientName.TOMATO_UNCUT, IngredientName.TOMATO_CUT);
        put(IngredientName.ONION_UNCUT, IngredientName.ONION_CUT);

    }};


    //==========================================================\\
    //                      CONSTRUCTOR                         \\
    //==========================================================\\

    public CuttingStation(float xPos, float yPos, boolean isLocked) {
        super(xPos, yPos, "station_cutting.png", ingredientMap, 4.0f,0f, true, isLocked);
    }
}
