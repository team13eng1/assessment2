package com.mygdx.tests.RequirementTests;

import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.interact.ingredient_stations.BunStation;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;
import com.mygdx.tests.GdxTestRunner;
import com.mygdx.tests.Utility;
import org.junit.Assert;
import org.junit.Test;

import com.mygdx.game.ingredient.IngredientName;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class RecipeTest {

    @Test
    public void testBurgerRecipe() throws InterruptedException {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        PlayerEngine.getActiveChef().setXPos(420);
        PlayerEngine.getActiveChef().setYPos(420);

        InteractEngine.interact();

        System.out.println(InteractEngine.getClosestInteractable());


        // rule of thumb ; no testing private attributes

    }
}