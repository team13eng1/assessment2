package com.mygdx.game.interact.special_stations.assembly_stations;

import com.mygdx.game.ingredient.IngredientName;

/**
 *
 * Assembly station for a Raw Pizza
 */
public class PizzaRawStation extends AssemblyStation {

    // Pizza Recipe
    IngredientName[] recipe = new IngredientName[] {
            IngredientName.PIZZA_BASE,
            IngredientName.TOMATO_CUT,
            IngredientName.CHEESE,
    };

    IngredientName outputIngredient = IngredientName.PIZZA_RAW;

    public PizzaRawStation(float xPos, float yPos) {

        super(xPos, yPos, "station_assembly_pizza.png");
        setRecipe(recipe, outputIngredient);

    }
}
