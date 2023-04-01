package com.mygdx.tests.BasicConstructorTests.CustomerPlayerTests;

import static org.junit.Assert.*;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.ingredient.IngredientMap;
import com.mygdx.game.player.Player;
import com.mygdx.tests.GdxTestRunner;
import com.mygdx.tests.Utility;
import org.junit.Assert;
import org.junit.Test;

import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.special_stations.CustomerCounter;
import org.junit.runner.RunWith;

import java.util.LinkedList;

public class PlayerTest {

    @Test
    public void testConstructor() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        Player player = new Player(1, 10, 10, "temp_chef_1.png");

        Assert.assertEquals(1, player.getID());
        Assert.assertEquals(10, player.getXPos(), 0);
        Assert.assertEquals(10, player.getYPos(), 0);


        // rule of thumb ; no testing private attributes

    }

    @Test
    public void testMovement() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        Player player = new Player(1, 10, 10, "temp_chef_1.png");

        // test positive x

        float actual = player.getXPos();
        System.out.println(player.getXPos());

        player.moveX(10);

        System.out.println(player.getXPos());
        Assert.assertNotEquals(actual, player.getXPos(), 0);

        // test negative x

        actual = player.getXPos();

        player.moveX(-10);

        Assert.assertNotEquals(actual, player.getXPos(), 0);

        // test positive y

        actual = player.getYPos();

        player.moveY(10);

        Assert.assertNotEquals(actual, player.getYPos(), 0);

        // test negative y

        actual = player.getYPos();

        player.moveY(-10);

        Assert.assertNotEquals(actual, player.getYPos(), 0);

    }



}