package com.mygdx.tests.BasicConstructorTests.StationTests;

import com.mygdx.game.interact.special_stations.assembly_stations.BurgerStation;
import com.mygdx.tests.GdxTestRunner;
import com.mygdx.tests.Utility;
import org.junit.Assert;
import org.junit.Test;

import com.mygdx.game.ingredient.IngredientName;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class BurgerStationTest {

    @Test
    public void testBurgerStationCreation() throws InterruptedException {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        BurgerStation burgerStation = new BurgerStation(1, 2);

        Assert.assertEquals(1, burgerStation.getXPos(), 0.0f);
        Assert.assertEquals(2, burgerStation.getYPos(), 0.0f);
    }

    @Test
    public void testSetRecipe() throws InterruptedException {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        BurgerStation burgerStation = new BurgerStation(1, 2);

        IngredientName[] actual = new IngredientName[] {
                IngredientName.BUNS_TOASTED,
                IngredientName.PATTY_COOKED,
                IngredientName.BUNS_TOASTED
        };

        Assert.assertArrayEquals(actual, burgerStation.recipe);
    }

    @Test
    public void testOutputIngredient() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        BurgerStation burgerStation = new BurgerStation(1, 2);

        IngredientName actual = IngredientName.BURGER;

        Assert.assertEquals(actual, burgerStation.outputIngredient);
    }
}