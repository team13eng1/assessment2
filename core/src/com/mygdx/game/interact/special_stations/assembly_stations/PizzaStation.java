// TODO: 08/03/2023 Skins here aswell


package com.mygdx.game.interact.special_stations.assembly_stations;

import com.mygdx.game.ingredient.IngredientName;

/**
 * @author Thomas McCarthy
 *
 * Assembly station for a Pizza
 */
public class PizzaStation extends AssemblyStation {

    // Pizza Recipe
    IngredientName[] recipe = new IngredientName[] {
            IngredientName.PIZZA_BASE,
            IngredientName.TOMATO_CUT,
            IngredientName.CHEESE
    };

    IngredientName outputIngredient = IngredientName.PIZZA;

    public PizzaStation(float xPos, float yPos) {

        super(xPos, yPos, "station_assembly_pizza.png");
        setRecipe(recipe, outputIngredient);

    }
}
