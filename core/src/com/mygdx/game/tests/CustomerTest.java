package com.mygdx.game.customer;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.special_stations.CustomerCounter;


public class CustomerTest {

    @Test
    public void testConstructor() {
        CustomerCounter counter = new CustomerCounter();
        IngredientName requiredIngredient = IngredientName.BREAD;
        Customer customer = new Customer(counter, requiredIngredient);
        assertEquals(-150f, customer.posX, 0);
        assertEquals(counter.getClass(), customer.posY);
        assertEquals(70f, customer.counterOffset, 0);
        assertFalse(customer.atCounter);
        assertFalse(customer.orderComplete);
        assertEquals(counter, customer.counter);
        assertEquals(requiredIngredient, customer.requiredIngredient);
    }
    
    @Test
    public void testSetAtCounter() {
        CustomerCounter counter = new CustomerCounter();
        IngredientName requiredIngredient = IngredientName.CHEESE;
        Customer customer = new Customer(counter, requiredIngredient);
        assertFalse(customer.atCounter);
        customer.setAtCounter(true);
        assertTrue(customer.atCounter);
    }
    
   
}