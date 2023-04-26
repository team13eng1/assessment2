package com.mygdx.game.ingredient;

/**
 * An enum storing the constant names of all ingredients in the game
 */
public enum IngredientName {

    /*
        The null ingredient. Used to represent an empty carryStack (see Player > peekStack(), popStack()).
        NULL_INGREDIENT isn't and shouldn't be used for any recipes or stations.
     */
    NULL_INGREDIENT,

    // Real ingredients
    BUNS_TOASTED,
    BUNS_UNTOASTED,
    LETTUCE_CUT,
    LETTUCE_UNCUT,
    ONION_CUT,
    ONION_UNCUT,
    PATTY_COOKED,
    PATTY_RAW,
    TOMATO_CUT,
    TOMATO_UNCUT,
    POTATO_UNCOOKED,
    POTATO_COOKED,
    TUNA,
    CHEESE,
    PIZZA_BASE,

    PIZZA_RAW,

    // Assembled Foods
    BURGER,
    SALAD,
    JACKET_POTATO_FINISHED,
    PIZZA,


    // Burnt Foods

    PIZZA_BURNT,

    POTATO_BURNT,

    PATTY_BURNT,

    BUNS_BURNT



}
