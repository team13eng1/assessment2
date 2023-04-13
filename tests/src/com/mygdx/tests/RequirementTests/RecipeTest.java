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
        System.out.println(PlayerEngine.getActiveChef().getIngredientStack().peek());

        PlayerEngine.getActiveChef().setXPos(140); // move to cooking station

        InteractEngine.interact(); // cook
        InteractEngine.getClosestInteractable().incrementTime(InteractEngine.getClosestInteractable().getPreparationTime()); // simulate waiting 10f
        InteractEngine.interact(); // pickup

        PlayerEngine.getActiveChef().setXPos(70);
        PlayerEngine.getActiveChef().setYPos(70); // move to counter

        InteractEngine.interact(); // place cooked patty down at 70, 70

        PlayerEngine.getActiveChef().setXPos(490);
        PlayerEngine.getActiveChef().setYPos(420); // move to buns

        InteractEngine.interact(); // pickup bun

        PlayerEngine.getActiveChef().setXPos(140);
        PlayerEngine.getActiveChef().setYPos(420); // move to cooking station

        InteractEngine.interact(); // cook bun
        InteractEngine.getClosestInteractable().incrementTime(InteractEngine.getClosestInteractable().getPreparationTime()); // simulate waiting 10f
        InteractEngine.interact(); // pickup cooked bun

        PlayerEngine.getActiveChef().setXPos(350);
        PlayerEngine.getActiveChef().setYPos(0); // move to counter

        InteractEngine.interact(); // place cooked bun down at 350, 0

        PlayerEngine.getActiveChef().setXPos(490);
        PlayerEngine.getActiveChef().setYPos(420); // move to buns

        InteractEngine.interact(); // pickup bun

        PlayerEngine.getActiveChef().setXPos(140);
        PlayerEngine.getActiveChef().setYPos(420); // move to cooking station

        InteractEngine.interact(); // cook bun
        InteractEngine.getClosestInteractable().incrementTime(InteractEngine.getClosestInteractable().getPreparationTime()); // simulate waiting 10f
        InteractEngine.interact(); // pickup cooked bun

        PlayerEngine.getActiveChef().setXPos(70);
        PlayerEngine.getActiveChef().setYPos(70); // move to counter with cooked patty

        InteractEngine.interact(); // pickup cooked patty

        PlayerEngine.getActiveChef().setXPos(350);
        PlayerEngine.getActiveChef().setYPos(0); // move to counter with cooked bun

        InteractEngine.interact(); // pickup cooked bun 2

        PlayerEngine.getActiveChef().setXPos(270);
        PlayerEngine.getActiveChef().setYPos(170); // move to burger station

        InteractEngine.interact();
        InteractEngine.interact();
        InteractEngine.interact(); // add all three

        InteractEngine.interact(); // pickup burger

        Assert.assertEquals(IngredientName.BURGER, PlayerEngine.getActiveChef().getIngredientStack().peek());




        // rule of thumb ; no testing private attributes

    }
}