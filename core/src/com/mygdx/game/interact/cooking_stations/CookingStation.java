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

    static public IngredientMap ingredientMap = new IngredientMap() {{

        put(IngredientName.PATTY_RAW, IngredientName.PATTY_COOKED);
        put(IngredientName.PATTY_COOKED, IngredientName.PATTY_BURNT);

        put(IngredientName.BUNS_UNTOASTED, IngredientName.BUNS_TOASTED);
        put(IngredientName.BUNS_TOASTED, IngredientName.BUNS_BURNT);

        put(IngredientName.POTATO_UNCOOKED, IngredientName.POTATO_COOKED);
        put(IngredientName.POTATO_COOKED, IngredientName.POTATO_BURNT);

        put(IngredientName.PIZZA_RAW, IngredientName.PIZZA);
        put(IngredientName.PIZZA, IngredientName.PIZZA_BURNT);


    }};


    //==========================================================\\
    //                      CONSTRUCTOR                         \\
    //==========================================================\\

    public CookingStation(float xPos, float yPos) {

        super(xPos, yPos, "station_cooking.png", ingredientMap, 4.0f,4.0f, false);

    }
}
