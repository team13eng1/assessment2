package com.mygdx.tests;

import static org.junit.Assert.*;

import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import org.junit.Assert;
import org.junit.Test;

import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.special_stations.CustomerCounter;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class CustomerTest {

    @Test
    public void testConstructor() {
        Utility.initialiseGame();
        CustomerCounter counter = new CustomerCounter(50, 50);
        IngredientName requiredIngredient = IngredientName.TOMATO_CUT;
        Customer customer = new Customer(counter, requiredIngredient);
        Assert.assertEquals(-150f, customer.posX, 0);
    }
    
    @Test
    public void testSetAtCounter() {
        CustomerCounter counter = new CustomerCounter(25,25);
        IngredientName requiredIngredient = IngredientName.CHEESE;
        Customer customer = new Customer(counter, requiredIngredient);
        Assert.assertFalse(customer.atCounter);
        //customer.setAtCounter(true);
        //assertTrue(customer.atCounter);
    }
    
   
}