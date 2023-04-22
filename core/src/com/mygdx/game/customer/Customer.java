package com.mygdx.game.customer;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.special_stations.CustomerCounter;
import com.mygdx.game.player.PlayerEngine;

/**
 * @author Thomas McCarthy
 *
 * A customer is given an order, and brings it to the designated counter.
 */
public class Customer {

    public float posX;
    public float posY;
    float speed = 50f;

    // Float determining how far from the counter the customer stands.
    public float counterOffset;
    public boolean atCounter = false;
    public boolean orderComplete = false;

    public CustomerCounter counter;
    public IngredientName requiredIngredient;

    float startTime;
    float reputationLimitTime;

    boolean finished;

    //==========================================================\\
    //                      CONSTRUCTOR                         \\
    //==========================================================\\
    public Customer(CustomerCounter counter, IngredientName requiredIngredient, float startTime, float reputationLimitTime)
    {
        this.startTime = startTime;
        this.reputationLimitTime = reputationLimitTime;
        this.counter = counter;
        this.requiredIngredient = requiredIngredient;

        posX = -150f;
        posY = counter.getYPos();

        counterOffset = 70f;

        finished = false;
    }


    //==========================================================\\
    //                       ANIMATION                          \\
    //==========================================================\\

    public void update()
    {
        if(orderComplete && getDistanceFromCounter() < 200f)
        {
            posX -= (speed * Gdx.graphics.getDeltaTime());
        }
        else if(orderComplete)
        {
            CustomerEngine.removeCustomer(this);
            if (!isWaitTooLong(PlayerEngine.getMasterTime())){
                PlayerEngine.gainCoins(10);
                CustomerEngine.customersServed ++;
            }
        }
        else if(!atCounter)
        {
            posX += (speed * Gdx.graphics.getDeltaTime());
            if(getDistanceFromCounter() <= counterOffset)
            {
                counter.placeOrder(this, requiredIngredient);
                atCounter = true;
            }
        }
    }


    //==========================================================\\
    //                    GETTERS & SETTERS                     \\
    //==========================================================\\

    public float getXPos() { return posX; }

    public float getYPos() { return posY; }

    public float getDistanceFromCounter() { return Math.abs(counter.getXPos() - posX); }

    public void completeOrder()
    {
        orderComplete = true;
    }

    public boolean isWaitTooLong(float mainTime){
        return (mainTime - startTime > reputationLimitTime);
    }

    public void finishWithThisCustomer() {
        finished = true;
    }

    public CustomerCounter getCounter() {
        return counter;
    }

    public float getTimeRemaining() {
        return startTime;
    }

    public float getReputationLimitTime() {
        return reputationLimitTime;
    }
}
