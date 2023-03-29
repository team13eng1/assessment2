package com.mygdx.tests.IngredientTests;

import static org.junit.Assert.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game._convenience.IngredientStack;
import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
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
public class IngredientStationsTest {

    @Test
    public void testAllConstructors() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests


        BunStation bunStation = new BunStation(1, 1);

        Assert.assertEquals(1, bunStation.getXPos(), 0);
        Assert.assertEquals(1, bunStation.getYPos(), 0);


        LettuceStation lettuceStation = new LettuceStation(4, 4);

        Assert.assertEquals(4, lettuceStation.getXPos(), 0);
        Assert.assertEquals(4, lettuceStation.getYPos(), 0);

        OnionStation onionStation = new OnionStation(5, 5);

        Assert.assertEquals(5, onionStation.getXPos(), 0);
        Assert.assertEquals(5, onionStation.getYPos(), 0);

        PattyStation pattyStation = new PattyStation(6, 6);

        Assert.assertEquals(6, pattyStation.getXPos(), 0);
        Assert.assertEquals(6, pattyStation.getYPos(), 0);

        TomatoStation tomatoStation = new TomatoStation(8, 8);

        Assert.assertEquals(8, tomatoStation.getXPos(), 0);
        Assert.assertEquals(8, tomatoStation.getYPos(), 0);



        // rule of thumb: no testing private attributes / methods
    }

}