package com.mygdx.tests.SpecialStationsTests;

import static org.junit.Assert.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game._convenience.IngredientStack;
import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.interact.special_stations.Bin;
import com.mygdx.game.interact.special_stations.Counter;
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
public class CounterTest {

    @Test
    public void testConstructor() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        Counter counter = new Counter(1, 1);

        Assert.assertEquals(1, counter.getXPos(), 0);
        Assert.assertEquals(1, counter.getYPos(), 0);
        Assert.assertEquals(null, counter.storedIngredient);
        // Assert.assertEquals(new Texture("counter.png"), counter.getSprite().getTexture()); // can't seem to compare textures?
    }

    @Test
    public void testHandleInteraction() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        Counter counter = new Counter(1, 1);
        PlayerEngine.getActiveChef().getIngredientStack().push(IngredientName.CHEESE);

        // condition A
        counter.handleInteraction();
        Assert.assertEquals(IngredientName.CHEESE, counter.storedIngredient);

        // condition B
        counter.storedIngredient = IngredientName.LETTUCE_CUT;
        counter.handleInteraction();
        Assert.assertEquals(PlayerEngine.getActiveChef().getIngredientStack().peek(), IngredientName.LETTUCE_CUT);
        Assert.assertEquals(null, counter.storedIngredient);
    }


}