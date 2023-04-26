package com.mygdx.game.player.PowerUps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * The base script for power up objects
 *
 */


public class PowerUpBase {
    private float xPos;
    private float yPos;
    private Rectangle collisionRect;

    private float powerUpTime;

    private Sprite sprite;

    private float startTime;

    public PowerUpBase(float xPos, float yPos, String texture, float powerUpTime, float startTime){
        this.xPos = xPos;
        this.yPos = yPos;
        this.sprite = new Sprite(new Texture(texture));
        this.powerUpTime = powerUpTime;
        this.startTime = startTime;
        setUpCollision();
    }

    public void setUpCollision()
    {
        sprite.setCenter(xPos + sprite.getTexture().getWidth(), yPos + sprite.getTexture().getHeight());
        sprite.setPosition(xPos, yPos);
        collisionRect = new Rectangle(xPos, yPos, this.sprite.getTexture().getWidth(), this.sprite.getTexture().getHeight());
    }

    //==========================================================\\
    //                      INTERACTION                         \\
    //==========================================================\\

    /**

     Checks if a player chef is within the interaction range of this power-up and returns whether interaction is valid.
     @param chefXPos the x-coordinate of the player chef
     @param chefYPos the y-coordinate of the player chef
     @param interactRange the interaction range of the power-up
     @return true if the player chef is within range, false otherwise
     @version 1.3
     */

    public boolean tryInteraction(float chefXPos, float chefYPos, final float interactRange)
    {
        float xDist = Math.abs(chefXPos - xPos);
        float yDist = Math.abs(chefYPos - yPos);

        // If chef is within range, handle the appropriate interaction
        return xDist <= interactRange && yDist <= interactRange;
    }

    public void startInteraction(){}

    public void endInteraction(){}

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getStartTime() {
        return startTime;
    }

    public float getAllowedDuration(){
        return powerUpTime;
    }
}
