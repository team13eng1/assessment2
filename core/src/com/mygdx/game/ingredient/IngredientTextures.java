package com.mygdx.game.ingredient;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * @author Thomas McCarthy
 *
 * A class storing a hashmap that maps IngredientNames to their corresponding textures
 */
public final class IngredientTextures {

    private static final HashMap<IngredientName, Texture> textures = new HashMap<IngredientName, Texture>()
    {{
        // Texture for the NULL_INGREDIENT. Do not remove, do not use in stations or recipes!
        put(IngredientName.NULL_INGREDIENT, new Texture("ingredient_null.png"));


        // Other ingredients
        put(IngredientName.LETTUCE_UNCUT, new Texture("ingredient_lettuce_uncut.png"));
        put(IngredientName.LETTUCE_CUT, new Texture("ingredient_lettuce_cut.png"));

        put(IngredientName.TOMATO_UNCUT, new Texture("ingredient_tomato_uncut.png"));
        put(IngredientName.TOMATO_CUT, new Texture("ingredient_tomato_cut.png"));

        put(IngredientName.ONION_UNCUT, new Texture("ingredient_onion_uncut.png"));
        put(IngredientName.ONION_CUT, new Texture("ingredient_onion_cut.png"));

        put(IngredientName.PATTY_RAW, new Texture("ingredient_patty_raw.png"));
        put(IngredientName.PATTY_COOKED, new Texture("ingredient_patty_cooked.png"));

        put(IngredientName.BUNS_UNTOASTED, new Texture("ingredient_bun_untoasted.png"));
        put(IngredientName.BUNS_TOASTED, new Texture("ingredient_bun_toasted.png"));

        put(IngredientName.POTATO_UNCOOKED, new Texture("ingredient_potato_raw.png"));
        put(IngredientName.POTATO_COOKED, new Texture("ingredient_potato_cooked.png"));
        put(IngredientName.TUNA, new Texture("ingredient_tuna.png"));

        put(IngredientName.PIZZA_BASE, new Texture("ingredient_pizza_base.png"));
        put(IngredientName.CHEESE, new Texture("ingredient_cheese.png"));

        put(IngredientName.PIZZA_RAW, new Texture("ingredient_pizza_raw.png"));

        // Assembled foods

        put(IngredientName.BURGER, new Texture("ingredient_burger.png"));
        put(IngredientName.SALAD, new Texture("ingredient_salad.png"));

        put(IngredientName.PIZZA, new Texture("ingredient_cooked_pizza.png"));
        put(IngredientName.JACKET_POTATO_FINISHED, new Texture("ingredient_jacket_potato.png"));

        // Burnt Foods

        put(IngredientName.BUNS_BURNT, new Texture("ingredient_bun_burnt.png"));
        put(IngredientName.PATTY_BURNT, new Texture("ingredient_patty_burnt.png"));

        put(IngredientName.POTATO_BURNT, new Texture("ingredient_potato_burnt.png"));
        put(IngredientName.PIZZA_BURNT, new Texture("ingredient_pizza_burnt.png"));
    }};


    //==========================================================\\
    //                         GETTERS                          \\
    //==========================================================\\

    public static Texture getTexture(IngredientName ingredientName)
    {
        return textures.get(ingredientName);
    }

}
