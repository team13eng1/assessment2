package com.mygdx.tests.BasicConstructorTests.StationTests;

import static org.junit.Assert.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game._convenience.IngredientStack;
import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.ingredient.IngredientMap;
import com.mygdx.game.interact.cooking_stations.BakingStation;
import com.mygdx.game.interact.cooking_stations.CookingStation;
import com.mygdx.game.interact.cooking_stations.CuttingStation;
import com.mygdx.game.interact.ingredient_stations.*;
import com.mygdx.game.interact.special_stations.Bin;
import com.mygdx.game.interact.special_stations.assembly_stations.PizzaStation;
import com.mygdx.game.player.PlayerEngine;
import com.mygdx.tests.GdxTestRunner;
import com.mygdx.tests.Utility;
import org.junit.Assert;
import org.junit.Test;

import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.special_stations.CustomerCounter;
import org.junit.runner.RunWith;

import java.util.EmptyStackException;
import java.util.LinkedList;

@RunWith(GdxTestRunner.class)
public class CookingStationsTest {

    @Test
    public void testAllConstructors() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests


        BakingStation bakingStation = new BakingStation(1, 1);
        IngredientMap bakingMap = new IngredientMap() {{

            put(IngredientName.BUNS_UNTOASTED, IngredientName.BUNS_TOASTED);

        }};

        Assert.assertEquals(1, bakingStation.getXPos(), 0);
        Assert.assertEquals(1, bakingStation.getYPos(), 0);
        Assert.assertEquals(bakingMap, bakingStation.ingredientMap);


        CookingStation cookingStation = new CookingStation(4, 4, false);

        IngredientMap cookingMap = new IngredientMap() {{

            put(IngredientName.PATTY_RAW, IngredientName.PATTY_COOKED);

        }};

        Assert.assertEquals(4, cookingStation.getXPos(), 0);
        Assert.assertEquals(4, cookingStation.getYPos(), 0);
        Assert.assertEquals(cookingMap, cookingStation.ingredientMap);

        CuttingStation cuttingStation = new CuttingStation(5, 5, false);

        IngredientMap cuttingMap = new IngredientMap() {{

            put(IngredientName.LETTUCE_UNCUT, IngredientName.LETTUCE_CUT);
            put(IngredientName.TOMATO_UNCUT, IngredientName.TOMATO_CUT);
            put(IngredientName.ONION_UNCUT, IngredientName.ONION_CUT);

        }};

        Assert.assertEquals(5, cuttingStation.getXPos(), 0);
        Assert.assertEquals(5, cuttingStation.getYPos(), 0);
        Assert.assertEquals(cuttingMap, cuttingStation.ingredientMap);


        // rule of thumb: no testing private attributes / methods
    }

}