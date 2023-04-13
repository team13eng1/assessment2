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
public class BurnTest {

    @Test
    public void testBurnPatty() throws InterruptedException {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        Assert.assertEquals(0, PlayerEngine.getActiveChef().getID());
        Assert.assertEquals(175, PlayerEngine.getActiveChef().getXPos(), 0);
        Assert.assertEquals(350, PlayerEngine.getActiveChef().getYPos(), 0);

        PlayerEngine.getActiveChef().getIngredientStack().push(IngredientName.PATTY_RAW);

        PlayerEngine.getActiveChef().setXPos(135);
        PlayerEngine.getActiveChef().setYPos(415);



        // simulate player pressing "E"

        InteractEngine.interact(); // should call interactablebase.handleinteraction()


        InteractEngine.getClosestInteractable().incrementTime(InteractEngine.getClosestInteractable().getPreparationTime() + 6f); // simulate waiting 10f

        // as we spawned the player by a cooking station, and he has a raw patty,
        // he should be able to cook it and burn it by cooking for >8f

        InteractEngine.interact();

        Assert.assertEquals(IngredientName.PATTY_BURNT, PlayerEngine.getActiveChef().getIngredientStack().peek());



        // rule of thumb ; no testing private attributes

    }

    @Test
    public void testBurnBun() throws InterruptedException {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        Assert.assertEquals(0, PlayerEngine.getActiveChef().getID());
        Assert.assertEquals(175, PlayerEngine.getActiveChef().getXPos(), 0);
        Assert.assertEquals(350, PlayerEngine.getActiveChef().getYPos(), 0);

        PlayerEngine.getActiveChef().getIngredientStack().push(IngredientName.BUNS_UNTOASTED);

        PlayerEngine.getActiveChef().setXPos(135);
        PlayerEngine.getActiveChef().setYPos(415);



        // simulate player pressing "E"

        InteractEngine.interact(); // should call interactablebase.handleinteraction()


        InteractEngine.getClosestInteractable().incrementTime(InteractEngine.getClosestInteractable().getPreparationTime() + 6f); // simulate waiting 10f

        // as we spawned the player by a cooking station, and he has a raw patty,
        // he should be able to cook it and burn it by cooking for >8f

        InteractEngine.interact();

        Assert.assertEquals(IngredientName.BUNS_BURNT, PlayerEngine.getActiveChef().getIngredientStack().peek());



        // rule of thumb ; no testing private attributes

    }
}