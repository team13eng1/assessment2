package com.mygdx.tests.FunctionalityTests;

import static org.junit.Assert.*;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.ingredient.IngredientMap;
import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.interact.InteractableBase;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;
import com.mygdx.tests.GdxTestRunner;
import com.mygdx.tests.Utility;
import org.junit.Assert;
import org.junit.Test;

import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.special_stations.CustomerCounter;
import org.junit.runner.RunWith;

import java.util.LinkedList;

@RunWith(GdxTestRunner.class)
public class CollisionTest {

    @Test
    public void testPlayerCollision() throws InterruptedException {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        // player pos = chef[0]

        Assert.assertEquals(0, PlayerEngine.getActiveChef().getID());
        Assert.assertEquals(175, PlayerEngine.getActiveChef().getXPos(), 0);
        Assert.assertEquals(350, PlayerEngine.getActiveChef().getYPos(), 0);

        PlayerEngine.getActiveChef().getIngredientStack().push(IngredientName.PATTY_RAW);

        // simulate player pressing "E"

        InteractEngine.interact(); // should call interactablebase.handleinteraction()

        System.out.println(InteractEngine.getInteractables()[9].isPreparing());
        InteractEngine.getInteractables()[9].incrementTime(10); // simulate waiting 10f
        System.out.println(InteractEngine.getInteractables()[9].isPreparing());

        // as we spawned the player by a cooking station, and he has a raw patty,
        // he should be able to cook it

        InteractEngine.interact(); // should call interactablebase.handleinteraction()

        // ^ simulate player pressing "E" again (picking it up)

        Assert.assertEquals(IngredientName.PATTY_COOKED, PlayerEngine.getActiveChef().getIngredientStack().peek());



        // rule of thumb ; no testing private attributes

    }
}