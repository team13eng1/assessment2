package com.mygdx.tests.BasicConstructorTests.CustomerPlayerTests;

import static org.junit.Assert.*;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.ingredient.IngredientMap;
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
public class PlayerTest {

    @Test
    public void testConstructor() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        Player player = new Player(5, 10, 10, "temp_chef_1.png");

        Assert.assertEquals(5, player.getID());
        Assert.assertEquals(10, player.getXPos(), 0);
        Assert.assertEquals(10, player.getYPos(), 0);


        // rule of thumb ; no testing private attributes

    }

    @Test
    public void testMovement() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        // test positive x

        float actual = PlayerEngine.getActiveChef().getXPos();
        System.out.println(PlayerEngine.getActiveChef().getXPos());

        PlayerEngine.getActiveChef().moveX(1);

        System.out.println(PlayerEngine.getActiveChef().getXPos());
        Assert.assertNotEquals(actual, PlayerEngine.getActiveChef().getXPos(), 0);

        // test negative x

        actual = PlayerEngine.getActiveChef().getXPos();

        PlayerEngine.getActiveChef().moveX(-1);

        Assert.assertNotEquals(actual, PlayerEngine.getActiveChef().getXPos(), 0);

        // test positive y

        actual = PlayerEngine.getActiveChef().getYPos();

        PlayerEngine.getActiveChef().moveY(1);

        Assert.assertNotEquals(actual, PlayerEngine.getActiveChef().getYPos(), 0);

        // test negative y

        actual = PlayerEngine.getActiveChef().getYPos();

        PlayerEngine.getActiveChef().moveY(-1);

        Assert.assertNotEquals(actual, PlayerEngine.getActiveChef().getYPos(), 0);

    }



}