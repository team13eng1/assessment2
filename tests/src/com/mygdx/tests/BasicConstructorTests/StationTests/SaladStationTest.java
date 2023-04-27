package com.mygdx.tests.BasicConstructorTests.StationTests;

import static org.junit.Assert.*;

import com.mygdx.game.interact.special_stations.assembly_stations.SaladStation;
import com.mygdx.tests.GdxTestRunner;
import com.mygdx.tests.Utility;
import org.junit.Assert;
import org.junit.Test;

import com.mygdx.game.ingredient.IngredientName;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class SaladStationTest {

    @Test
    public void testSaladStationCreation() throws InterruptedException {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        SaladStation saladStation = new SaladStation(1, 2);

        Assert.assertEquals(1, saladStation.getXPos(), 0.0f);
        Assert.assertEquals(2, saladStation.getYPos(), 0.0f);
    }

    @Test
    public void testSetRecipe() throws InterruptedException {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        SaladStation saladStation = new SaladStation(1, 2);

        IngredientName[] actual = new IngredientName[] {
                IngredientName.LETTUCE_CUT,
                IngredientName.ONION_CUT,
                IngredientName.TOMATO_CUT
        };

        Assert.assertArrayEquals(actual, saladStation.recipe);
    }

    @Test
    public void testOutputIngredient() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        SaladStation saladStation = new SaladStation(1, 2);

        IngredientName actual = IngredientName.SALAD;

        Assert.assertEquals(actual, saladStation.outputIngredient);
    }
}