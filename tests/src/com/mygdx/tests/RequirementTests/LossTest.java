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
public class LossTest {

    @Test
    public void testLoseGame() throws InterruptedException {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        Assert.assertEquals(3, CustomerEngine.getReputationPointsRemaining());

        CustomerEngine.removeReputationPoint();
        CustomerEngine.removeReputationPoint();
        CustomerEngine.removeReputationPoint();

        Assert.assertEquals(0, CustomerEngine.getReputationPointsRemaining());
        // rule of thumb ; no testing private attributes

    }
}