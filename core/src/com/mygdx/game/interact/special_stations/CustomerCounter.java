package com.mygdx.game.interact.special_stations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.ingredient.IngredientTextures;

/**
 * An extension of the Counter class. This one can be assigned a customer.
 * This class is accessed by both Interact and Customer engines.
 */
public class CustomerCounter extends Counter {

    public IngredientName requiredIngredient;
    public Customer customer;
    Texture blank = new Texture("_blank.png");


    //==========================================================\\
    //                      CONSTRUCTOR                         \\
    //==========================================================\\

    public CustomerCounter(float xPos, float yPos) {
        super(xPos, yPos);
        requiredIngredient = null;
        customer = null;

        CustomerEngine.addCustomerCounter(this);
    }


    //==========================================================\\
    //                      INTERACTION                         \\
    //==========================================================\\

    @Override
    public void handleInteraction()
    {
        super.handleInteraction();

        if(customer != null && storedIngredient == requiredIngredient)
        {
            customer.completeOrder();
        }
    }


    //==========================================================\\
    //                    GETTERS & SETTERS                     \\
    //==========================================================\\

    @Override
    public Sprite getIngredientSprite() {
        if (customer == null) {
            if (this.storedIngredient == null) {
                return new Sprite(blank);
            } else {
                Sprite sprite = new Sprite(IngredientTextures.getTexture(storedIngredient));
                sprite.setColor(60f, 60f, 60f, 0.4f);
                sprite.setScale(0.8f, 0.8f);
                return sprite;
            }
        } else {
            if (requiredIngredient != null) {
                Sprite sprite = new Sprite(IngredientTextures.getTexture(requiredIngredient));
                sprite.setColor(60f, 60f, 60f, 0.7f);
                sprite.setScale(0.8f, 0.8f);
                return sprite;
            }
        }
        return null;
    }

    public void placeOrder(Customer customer, IngredientName requiredIngredient)
    {
        this.customer = customer;
        this.requiredIngredient = requiredIngredient;
    }

    public IngredientName getRequiredIngredient() {
        return requiredIngredient;
    }
}
