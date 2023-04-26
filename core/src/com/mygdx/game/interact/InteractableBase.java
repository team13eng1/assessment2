package com.mygdx.game.interact;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.ingredient.IngredientMap;
import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.ingredient.IngredientTextures;
import com.mygdx.game.interact.cooking_stations.CookingStation;
import com.mygdx.game.interact.cooking_stations.CuttingStation;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;

/**
 * 
 * The base script for interactable objects
 *
 */

public class InteractableBase {

	private float xPos;
	private float yPos;
	private Rectangle collisionRect;
	private Sprite sprite;

	protected Texture indicatorArrow = new Texture("indicator_arrow.png");

	// Ingredient Information
	private IngredientMap ingredientMap;
	private IngredientName inputIngredient;
	private IngredientName outputIngredient;
	private boolean hasIngredient;

	// Station Information
	private boolean isIngredientStation;
	private float preparationTime;

	private float burnTime;
	private float currentTime;

	/*
		Some stations require a chef to stand by them while preparing
		an ingredient. In these cases, lockChef is set to True,
		and the locked chef is stored in the connectedChef variable.
	 */
	private boolean lockChef;

	public boolean isLocked;

	public int unLockCost;
	private Player connectedChef;
	
	
	//==========================================================\\
	//                     CONSTRUCTORS                         \\
	//==========================================================\\
	
	// Cooking Station Constructor takes a texture, an ingredient map, and a given preparation time
	public InteractableBase(float xPos, float yPos, String texture, IngredientMap ingredientMap, float preparationTime, float burnTime, boolean lockChef, boolean isLocked)
	{
		this.isIngredientStation = false;
		this.xPos = xPos;
		this.yPos = yPos;
		this.sprite = new Sprite(new Texture(texture));
		this.ingredientMap = ingredientMap;
		this.preparationTime = preparationTime;
		this.burnTime = burnTime;
		this.hasIngredient = false;
		this.lockChef = lockChef;
		this.isLocked = isLocked;
		this.unLockCost = 0;
		this.connectedChef = null;
		setUpCollision();

		if (isLocked){
			if (this instanceof CookingStation){
				unLockCost = 30;
			} else if (this instanceof CuttingStation){
				unLockCost = 20;
			}
		}
	}
	
	// Ingredient Station Constructor takes a texture, an output ingredient, and no preparation time
	public InteractableBase(float xPos, float yPos, String texture, IngredientName outputIngredient)
	{
		this.isIngredientStation = true;
		this.xPos = xPos;
		this.yPos = yPos;
		this.sprite = new Sprite(new Texture(texture));
		this.outputIngredient = outputIngredient;
		this.hasIngredient = true;
		setUpCollision();
	}

	/*
	 Special Station Constructor takes a texture only, as they override the handleInteraction method,
	 making other parameters unnecessary.
	 */
	public InteractableBase(float xPos, float yPos, String texture)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.sprite = new Sprite(new Texture(texture));
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
		return xDist <= interactRange && yDist <= interactRange;
	}

	/**
	 Handles interactions between the player and the cooking/ingredient station.
	 @version 1.5
	 */

	public void handleInteraction()
	{
		Player activeChef = PlayerEngine.getActiveChef();


		// INGREDIENT STATION : give the ingredient to the chef
		if(isIngredientStation)
		{
			activeChef.getIngredientStack().push(outputIngredient);
		}

		else if (isLocked){
			if (PlayerEngine.getCoins() >= this.unLockCost) {
				PlayerEngine.loseCoins(unLockCost);
				isLocked = false;
			}
		}
		// COOKING STATION : is an ingredient already being prepared?
		else if(hasIngredient && !isLocked)
		{
			if (this instanceof CookingStation){
				if(currentTime >= preparationTime + burnTime) {
					activeChef.getIngredientStack().push(ingredientMap.getOutputIngredient(outputIngredient));
					hasIngredient = false;

				} else if (currentTime >= preparationTime) {
					activeChef.getIngredientStack().push(outputIngredient);
					hasIngredient = false;
				}
			} else if (currentTime >= preparationTime) {
				activeChef.getIngredientStack().push(outputIngredient);
				hasIngredient = false;
			}
		}

		// COOKING STATION : can the station take the chef's top ingredient?
		else if(ingredientMap.takesIngredient(activeChef.getIngredientStack().peek()) && !isLocked)
		{
			inputIngredient = activeChef.getIngredientStack().peek();
			outputIngredient = ingredientMap.getOutputIngredient(activeChef.getIngredientStack().pop());
			currentTime = 0f;
			hasIngredient = true;

			// Prevent the chef from moving if lockChef is true
			if(lockChef)
			{
				connectedChef = activeChef;
				connectedChef.setMovementEnabled(false);
			}
		}
	}


	//==========================================================\\
	//                         TIMER                            \\
	//==========================================================\\

	// Increments the counter for the station if required
	public void incrementTime(float timeElapsed)
	{
		if(isPreparing())
		{
			currentTime += timeElapsed;
		}
		else if(lockChef && connectedChef != null)
		{
			connectedChef.setMovementEnabled(true);
			connectedChef = null;
		}
	}


	//==========================================================\\
	//                         GETTERS                          \\
	//==========================================================\\

	public float getXPos() { return xPos; }

	public float getYPos() { return yPos; }

	public Sprite getSprite() { return sprite; }


	/**
	 Returns the sprite that represents the current state of the ingredient for this station.
	 If the station does not have the ingredient yet, it returns an arrow indicating the input direction.
	 If the ingredient is being prepared, it returns the sprite of the input ingredient.
	 If the ingredient is ready, it returns the sprite of the output ingredient.
	 If the station is a cooking station and the ingredient is burnt, it returns the sprite of the burnt output ingredient.
	 @return The sprite representing the current state of the ingredient for this station.
	 @version 1.5
	 */

	public Sprite getIngredientSprite() {
		if(!hasIngredient) 						{ return new Sprite(indicatorArrow); }
		else if(currentTime >= preparationTime) {
			if (this instanceof CookingStation) {
				if (currentTime >= preparationTime + burnTime) {
					return new Sprite(IngredientTextures.getTexture(ingredientMap.getOutputIngredient(outputIngredient)));
				}
			}
			return new Sprite(IngredientTextures.getTexture(outputIngredient));
		} else 									{ return new Sprite(IngredientTextures.getTexture(inputIngredient)); }
	}

	public float getCurrentTime() { return currentTime; }

	public float getPreparationTime() { return preparationTime; }

	public boolean isPreparing() { return (hasIngredient && !isIngredientStation && currentTime < preparationTime + burnTime); }

	public Rectangle getCollisionRect() { return collisionRect; }

	public float getBurnTime() { return burnTime;}

	public void setBurntTime(float burnTime) {this.burnTime = burnTime;}
}