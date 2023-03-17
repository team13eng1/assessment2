package com.mygdx.game.player.PowerUps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class PowerUpBase {
    private float xPos;
    private float yPos;
    private Rectangle collisionRect;

    private String texture;

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

    // The active chef can only engage with an interactable if they are within the right range
    public boolean tryInteraction(float chefXPos, float chefYPos, final float interactRange)
    {
        float xDist = Math.abs(chefXPos - xPos);
        float yDist = Math.abs(chefYPos - yPos);

        // If chef is within range, handle the appropriate interaction
        if(xDist <= interactRange && yDist <= interactRange)
        {
            return true;
        }
        return false;
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
