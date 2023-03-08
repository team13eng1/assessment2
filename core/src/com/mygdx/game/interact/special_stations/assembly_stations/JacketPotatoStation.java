// TODO: 08/03/2023 skins here aswell

package com.mygdx.game.interact.special_stations.assembly_stations;

import com.mygdx.game.ingredient.IngredientName;

/**
 * @author Thomas McCarthy
 *
 * Assembly station for a Jacket Potato
 */
public class JacketPotatoStation extends AssemblyStation {

    // Jacket potato recipe
    IngredientName[] recipe = new IngredientName[] {
            IngredientName.JACKET_POTATO_COOKED,
            IngredientName.TUNA
    };

    IngredientName outputIngredient = IngredientName.JACKET_POTATO_FINISHED;

    public JacketPotatoStation(float xPos, float yPos) {

        super(xPos, yPos, "station_assembly_jacket_potato.png");
        setRecipe(recipe, outputIngredient);

    }
}
