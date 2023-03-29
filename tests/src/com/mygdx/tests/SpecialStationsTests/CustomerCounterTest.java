package com.mygdx.tests.SpecialStationsTests;

import static org.junit.Assert.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game._convenience.IngredientStack;
import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.interact.special_stations.Bin;
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
public class CustomerCounterTest {

    @Test
    public void testConstructor() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        Assert.assertEquals(CustomerEngine.customerCounters.size(), 3);

        CustomerCounter customerCounter = new CustomerCounter(1, 1);

        Assert.assertEquals(1, customerCounter.getXPos(), 0);
        Assert.assertEquals(1, customerCounter.getYPos(), 0);
        Assert.assertEquals(null, customerCounter.storedIngredient);
        Assert.assertEquals(CustomerEngine.customerCounters.size(), 4);
    }

    /** Another issue with textures...
    @Test
    public void testGetIngredientSprite() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        CustomerCounter customerCounter = new CustomerCounter(1, 1);

        Sprite actual = customerCounter.getIngredientSprite();
        Texture blank = new Texture("_blank.png");

        Assert.assertEquals(actual, new Sprite(blank));

    }
    **/
}