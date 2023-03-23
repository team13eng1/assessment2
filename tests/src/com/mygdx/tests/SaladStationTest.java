package com.mygdx.tests;

import static org.junit.Assert.*;

import com.mygdx.game.interact.special_stations.assembly_stations.SaladStation;
import org.junit.Assert;
import org.junit.Test;

import com.mygdx.game.ingredient.IngredientName;

public class SaladStationTest {

    @Test
    public void testSaladStationCreation() {
        float xPos = 1.0f;
        float yPos = 2.0f;

        SaladStation saladStation = new SaladStation(xPos, yPos);

        Assert.assertEquals(xPos, saladStation.getXPos(), 0.0f);
        Assert.assertEquals(yPos, saladStation.getYPos(), 0.0f);
        //assertArrayEquals(new IngredientName[]{IngredientName.LETTUCE_CUT, IngredientName.ONION_CUT, IngredientName.TOMATO_CUT}, saladStation.getRecipe());
        //assertEquals(IngredientName.SALAD, saladStation.getOutputIngredient());
    }

    @Test
    public void testSetRecipe() {
        SaladStation saladStation = new SaladStation(0.0f, 0.0f);

        IngredientName[] recipe = new IngredientName[]{IngredientName.TOMATO_CUT, IngredientName.LETTUCE_CUT};
        //IngredientName outputIngredient = IngredientName.TOMATO_SALAD;

        

        //assertArrayEquals(recipe, saladStation.getRecipe());
        //assertEquals(outputIngredient, saladStation.getOutputIngredient());
    }
}