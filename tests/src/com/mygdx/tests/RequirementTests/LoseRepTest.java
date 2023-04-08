package com.mygdx.tests.RequirementTests;

import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.player.PlayerEngine;
import com.mygdx.tests.GdxTestRunner;
import com.mygdx.tests.Utility;
import org.junit.Assert;
import org.junit.Test;

import com.mygdx.game.ingredient.IngredientName;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class LoseRepTest {

    @Test
    public void testRepLoss() throws InterruptedException {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        // essentially just initialise the game and then wait 20f,
        // customers are made and a rep point should be lost

        Assert.assertEquals(3, CustomerEngine.getReputationPointsRemaining());

        // simulate wait
        CustomerEngine.removeReputationPoint();

        Assert.assertEquals(2, CustomerEngine.getReputationPointsRemaining());


        // rule of thumb ; no testing private attributes

    }
}