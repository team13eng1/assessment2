package com.mygdx.game.interact.special_stations.assembly_stations;

import com.mygdx.game.ingredient.IngredientName;

/**
 * Assembly station for a Burger
 */
public class BurgerStation extends AssemblyStation {

    // Burger recipe
    public IngredientName[] recipe = new IngredientName[] {
            IngredientName.BUNS_TOASTED,
            IngredientName.PATTY_COOKED,
            IngredientName.BUNS_TOASTED
    };

    public IngredientName outputIngredient = IngredientName.BURGER;

    public BurgerStation(float xPos, float yPos) {

        super(xPos, yPos, "station_assembly_burger.png");
        setRecipe(recipe, outputIngredient);

    }
}
