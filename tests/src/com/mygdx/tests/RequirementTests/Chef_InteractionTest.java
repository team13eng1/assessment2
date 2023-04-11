package com.mygdx.tests.RequirementTests;

import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;
import com.mygdx.tests.GdxTestRunner;
import com.mygdx.tests.Utility;
import org.junit.Assert;
import org.junit.Test;

import com.mygdx.game.ingredient.IngredientName;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class Chef_InteractionTest {

    @Test
    public void testChefInteraction() throws InterruptedException {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        // player pos = chef[0]

        Assert.assertEquals(0, PlayerEngine.getActiveChef().getID());
        Assert.assertEquals(175, PlayerEngine.getActiveChef().getXPos(), 0);
        Assert.assertEquals(350, PlayerEngine.getActiveChef().getYPos(), 0);

        PlayerEngine.getActiveChef().getIngredientStack().push(IngredientName.PATTY_RAW);

        PlayerEngine.getActiveChef().setXPos(135);
        PlayerEngine.getActiveChef().setYPos(415);



        // simulate player pressing "E"

        InteractEngine.interact(); // should call interactablebase.handleinteraction()


        InteractEngine.getClosestInteractable().incrementTime(InteractEngine.getClosestInteractable().getPreparationTime()); // simulate waiting 10f

        // as we spawned the player by a cooking station, and he has a raw patty,
        // he should be able to cook it

        InteractEngine.interact(); // should call interactablebase.handleinteraction()

        // ^ simulate player pressing "E" again (picking it up)

        Assert.assertEquals(IngredientName.PATTY_COOKED, PlayerEngine.getActiveChef().getIngredientStack().peek());

        // swap to chef 2
        PlayerEngine.swapChef("2");

        Assert.assertEquals(1, PlayerEngine.getActiveChef().getID());
        Assert.assertEquals(455, PlayerEngine.getActiveChef().getXPos(), 0);
        Assert.assertEquals(350, PlayerEngine.getActiveChef().getYPos(), 0);

        PlayerEngine.getActiveChef().getIngredientStack().push(IngredientName.BUNS_UNTOASTED);

        // simulate player pressing "E"

        InteractEngine.interact(); // should call interactablebase.handleinteraction()

        System.out.println(InteractEngine.getClosestInteractable());

        // as we spawned the player by a patty station, and he has untoasted buns,
        // he should be able to push a raw patty to the stack

        InteractEngine.interact(); // should call interactablebase.handleinteraction()

        // ^ simulate player pressing "E" again (picking it up)

        Assert.assertEquals(IngredientName.PATTY_RAW, PlayerEngine.getActiveChef().getIngredientStack().peek());

        PlayerEngine.swapChef("1");

        PlayerEngine.getActiveChef().getIngredientStack().pop();

        System.out.println(PlayerEngine.getActiveChef().getIngredientStack().getSize());

        PlayerEngine.getActiveChef().setXPos(265);
        PlayerEngine.getActiveChef().setYPos(165);


        // simulate player pressing "E"
        PlayerEngine.getActiveChef().getIngredientStack().push(IngredientName.BUNS_TOASTED);

        PlayerEngine.getActiveChef().getIngredientStack().push(IngredientName.PATTY_COOKED);

        PlayerEngine.getActiveChef().getIngredientStack().push(IngredientName.BUNS_TOASTED);


        InteractEngine.interact(); // should call interactablebase.handleinteraction()
        System.out.println(InteractEngine.getClosestInteractable());
        InteractEngine.interact(); // should call interactablebase.handleinteraction()
        InteractEngine.interact(); // should call interactablebase.handleinteraction()


        // as we spawned the player by a burger station,
        // he should be able to pop the three ingredients, and push a burger

        InteractEngine.interact(); // should call interactablebase.handleinteraction()

        System.out.println(InteractEngine.getClosestInteractable());

        // ^ simulate player pressing "E" again (picking it up)

        Assert.assertEquals(IngredientName.BURGER, PlayerEngine.getActiveChef().getIngredientStack().peek());



        // rule of thumb ; no testing private attributes

    }
}