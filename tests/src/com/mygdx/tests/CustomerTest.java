package com.mygdx.tests;

import static org.junit.Assert.*;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import org.junit.Assert;
import org.junit.Test;

import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.special_stations.CustomerCounter;
import org.junit.runner.RunWith;

import java.util.LinkedList;

@RunWith(GdxTestRunner.class)
public class CustomerTest {

    @Test
    public void testConstructor() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        CustomerCounter counter = new CustomerCounter(50, 50);
        IngredientName requiredIngredient = IngredientName.TOMATO_CUT;
        Customer customer = new Customer(counter, requiredIngredient);
        Assert.assertEquals(-150f, customer.posX, 0);
        Assert.assertEquals(50f, customer.posY, 0);
        Assert.assertEquals(IngredientName.TOMATO_CUT, customer.requiredIngredient);
        Assert.assertEquals(counter, customer.counter);
    }

    /** Not sure about testing with delta time updates in the mix...
    @Test
    public void testUpdate() {
        Utility.initialiseGame(); // initialise all engines & fresh start for tests

        CustomerCounter counterA = new CustomerCounter(50, 50);
        IngredientName requiredIngredientA = IngredientName.TOMATO_CUT;
        Customer customerA = new Customer(counterA, requiredIngredientA);

        CustomerCounter counterB = new CustomerCounter(50, 50);
        IngredientName requiredIngredientB = IngredientName.TOMATO_CUT;
        Customer customerB = new Customer(counterB, requiredIngredientB);

        CustomerCounter counterC = new CustomerCounter(50, 50);
        IngredientName requiredIngredientC = IngredientName.TOMATO_CUT;
        Customer customerC = new Customer(counterC, requiredIngredientC);

        // test condition A

        customerA.orderComplete = true;
        float beforePosX = customerA.getXPos();
        customerA.update();
        Assert.assertNotEquals(beforePosX, customerA.getXPos());

        // test condition B

        customerB.posX = 200f;
        customerB.orderComplete = true;
        int actualNumCustomer = CustomerEngine.getCustomersRemaining();
        customerB.update();
        Assert.assertNotEquals(actualNumCustomer, CustomerEngine.getCustomersRemaining());

        // test condition C

        customerC.posX = 200f;
        customerC.posY = 99f; // arbitrary number not equal to counter's ypos
        beforePosX = customerC.getXPos();
        customerC.update();
        Assert.assertNotEquals(beforePosX, customerC.getXPos());
    }

    **/

}