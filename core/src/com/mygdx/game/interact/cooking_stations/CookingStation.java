package com.mygdx.game.interact.cooking_stations;

import com.mygdx.game.ingredient.IngredientMap;
import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.InteractableBase;

/**
 * @author Thomas McCarthy
 *
 * Station for cooking, inherits properties from the InteractableBase class
 */
public class CookingStation extends InteractableBase {

    static IngredientMap ingredientMap = new IngredientMap() {{

        put(IngredientName.PATTY_RAW, IngredientName.PATTY_COOKED);
        put(IngredientName.BUNS_UNTOASTED, IngredientName.BUNS_TOASTED);
        put(IngredientName.POTATO_UNCOOKED, IngredientName.POTATO_COOKED);

    }};


    //==========================================================\\
    //                      CONSTRUCTOR                         \\
    //==========================================================\\

    public CookingStation(float xPos, float yPos) {

        super(xPos, yPos, "station_cooking.png", ingredientMap, 8.0f, false);

    }
}
