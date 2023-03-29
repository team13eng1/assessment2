package com.mygdx.tests.SpecialStationsTests;

import static org.junit.Assert.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
public class BinTest {

    @Test
    public void testConstructor() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        Bin bin = new Bin(1, 1);

        Assert.assertEquals(1, bin.getXPos(), 0);
        Assert.assertEquals(1, bin.getYPos(), 0);
        // Assert.assertEquals(new Texture("bin.png"), bin.getSprite().getTexture()); // can't seem to compare textures?
    }

    @Test
    public void testHandleInteraction() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        Bin bin = new Bin(1, 1);

        IngredientStack actual = new IngredientStack();
        actual.push(IngredientName.BUNS_TOASTED);

        PlayerEngine.getActiveChef().getIngredientStack().push(IngredientName.BURGER);

        Assert.assertNotEquals(actual.peek(), PlayerEngine.getActiveChef().getIngredientStack().peek());

        bin.handleInteraction();
        actual.pop();

        Assert.assertEquals(actual.peek(), PlayerEngine.getActiveChef().getIngredientStack().peek());
    }

}