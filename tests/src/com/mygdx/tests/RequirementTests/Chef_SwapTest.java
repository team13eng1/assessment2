package com.mygdx.tests.RequirementTests;

import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.player.PlayerEngine;
import com.mygdx.tests.GdxTestRunner;
import com.mygdx.tests.Utility;
import org.junit.Assert;
import org.junit.Test;

import com.mygdx.game.ingredient.IngredientName;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class Chef_SwapTest {

    @Test
    public void testPlayerCollision() throws InterruptedException {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        // player pos = chef[0]

        Assert.assertEquals(0, PlayerEngine.getActiveChef().getID());
        Assert.assertEquals(175, PlayerEngine.getActiveChef().getXPos(), 0);
        Assert.assertEquals(320, PlayerEngine.getActiveChef().getYPos(), 0);

        PlayerEngine.getActiveChef().setXPos(135);
        PlayerEngine.getActiveChef().setYPos(415);

        // simulate swapping id+1
        PlayerEngine.swapChef("Q");
        Assert.assertEquals(1, PlayerEngine.getActiveChef().getID());

        PlayerEngine.addNewChef();
        // simulate swapping id = 3
        PlayerEngine.swapChef("3");
        Assert.assertEquals(2, PlayerEngine.getActiveChef().getID());

        // swap back to chef[0] for testing
        PlayerEngine.swapChef("1");

        PlayerEngine.getActiveChef().getIngredientStack().push(IngredientName.PATTY_RAW);

        // simulate player pressing "E"

        InteractEngine.interact(); // should call interactablebase.handleinteraction()

        System.out.println(InteractEngine.getInteractables()[6].isPreparing());

        // simulate swapping id = 3 whilst id = 1 is completing a task

        PlayerEngine.swapChef("3");
        Assert.assertEquals(2, PlayerEngine.getActiveChef().getID());

        InteractEngine.getInteractables()[6].incrementTime(InteractEngine.getInteractables()[6].getPreparationTime()); // simulate waiting 10f

        // simulate swapping back to id=1
        PlayerEngine.swapChef("1");
        Assert.assertEquals(0, PlayerEngine.getActiveChef().getID());

        PlayerEngine.getActiveChef().setXPos(135);
        PlayerEngine.getActiveChef().setYPos(415);

        System.out.println(InteractEngine.getClosestInteractable().isPreparing());

        // as we spawned the player by a cooking station, and he has a raw patty,
        // he should be able to cook it

        InteractEngine.interact(); // should call interactablebase.handleinteraction()

        // ^ simulate player pressing "E" again (picking it up)

        Assert.assertEquals(IngredientName.PATTY_COOKED, PlayerEngine.getActiveChef().getIngredientStack().peek());



        // rule of thumb ; no testing private attributes

    }
}