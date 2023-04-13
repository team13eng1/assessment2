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
public class InvestmentTest {

    @Test
    public void testBuyChef() throws InterruptedException {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        PlayerEngine.setCoins(0);

        Assert.assertEquals(0, PlayerEngine.getCoins());
        Assert.assertEquals(2, PlayerEngine.getNumChefs());

        PlayerEngine.setCoins(20);
        Assert.assertEquals(20, PlayerEngine.getCoins());

        PlayerEngine.getActiveChef().setXPos(65);
        PlayerEngine.getActiveChef().setYPos(345);

        InteractEngine.interact();

        Assert.assertEquals(0, PlayerEngine.getCoins());
        Assert.assertEquals(3, PlayerEngine.getNumChefs());

        // rule of thumb ; no testing private attributes

    }
}