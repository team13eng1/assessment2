package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game._convenience.IngredientStack;
import com.mygdx.game.player.PowerUps.PowerUpBase;

import java.util.ArrayList;

/**
 *
 * The Player class stores all information regarding a chef, and also handles player movement.
 *
 */
public class Player {
	
	// player speed is a hard-coded value, so that it is the same for multiple players
	private float speed;
	private final int id;
	private float posX;
	private float posY;
	private float previousPosX;
	private float previousPosY;
	private final Rectangle collisionRect;
	private final Sprite sprite;
	// The LinkedList is used as an implementation of a stack
	private final IngredientStack carryStack;

	// Determines if the player is able to move
	private boolean movementEnabled;

	private final ArrayList<PowerUpBase> powerUps;


	//==========================================================\\
	//                      CONSTRUCTOR                         \\
	//==========================================================\\
	
	public Player(int id, int startX, int startY, String texture)
	{
		this.id = id;
		this.posX = startX;
		this.posY = startY;
		this.sprite = new Sprite(new Texture(texture));
		this.carryStack = new IngredientStack();
		this.movementEnabled = true;


		previousPosX = startX;
		previousPosY = startY;
		collisionRect = new Rectangle(posX, posY, sprite.getTexture().getWidth() * 0.75f, sprite.getTexture().getHeight() * 0.75f);

		powerUps = new ArrayList<>();
	}
	
	
	//==========================================================\\
	//                    PLAYER MOVEMENT                       \\
	//==========================================================\\
	
	public void handleMovement(Rectangle[] colliders) {
		sprite.setCenter(getXPos() + sprite.getTexture().getWidth() / 2f, getYPos() + sprite.getTexture().getHeight() / 2f);

		if(movementEnabled) {
			// Check for user movement input
			if(Gdx.input.isKeyPressed(Input.Keys.W)) {moveY(1f);}
			if(Gdx.input.isKeyPressed(Input.Keys.S)) {moveY(-1f);}
			if(Gdx.input.isKeyPressed(Input.Keys.A)) {moveX(-1f);}
			if(Gdx.input.isKeyPressed(Input.Keys.D)) {moveX(1f);}

			collisionRect.setPosition(posX, posY);

			for(Rectangle c : colliders)
			{
				if(c.overlaps(collisionRect))
				{
					posX = previousPosX;
					posY = previousPosY;
					collisionRect.setPosition(posX, posY);
				}
			}
		}
	}

	public void handlePowerUps(float masterTime){
		for (PowerUpBase powerUp : powerUps){
			if (masterTime - powerUp.getStartTime() > powerUp.getAllowedDuration()){
				powerUp.endInteraction();
				powerUps.remove(powerUp);
			}
		}
	}
	
	public void moveX(float multiplier) {
		previousPosX = posX;
		if(Gdx.graphics.getDeltaTime() == 0){ // basically for testing purposes
			posX += multiplier * speed;
		}
		else{
			posX += Gdx.graphics.getDeltaTime() * multiplier * speed;
		}
	}
	
	public void moveY(float multiplier) {
		previousPosY = posY;
		if(Gdx.graphics.getDeltaTime() == 0){ // basically for testing purposes
			posY += multiplier * speed;
		}
		else{
			posY += Gdx.graphics.getDeltaTime() * multiplier * speed;
		}
	}
	
	
	//==========================================================\\
	//                    GETTERS & SETTERS                     \\
	//==========================================================\\
	
	public int getID() { return id; }
	
	public float getXPos() { return posX; }
	
	public float getYPos() { return posY; }

	public void setXPos(float x) { posX = x;}
	public void setYPos(float y) { posY = y; }

	public Sprite getSprite() { return sprite; }

	public IngredientStack getIngredientStack() { return carryStack; }

	public void setMovementEnabled(boolean movementEnabled) {
		this.movementEnabled = movementEnabled;
	}

	public void setSpeed(float speed){this.speed = speed;}

	public void setSpeedDifficulty(float diffScaling){
		speed = 150f * diffScaling;
	}

	public float getSpeed() {
		return speed;
	}

	public void setPosition(float x, float y) {
		this.posX = x;
		this.posY = y;
	}
}
