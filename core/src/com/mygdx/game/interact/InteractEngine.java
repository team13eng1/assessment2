package com.mygdx.game.interact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.cooking_stations.CookingStation;
import com.mygdx.game.interact.cooking_stations.CuttingStation;
import com.mygdx.game.interact.ingredient_stations.*;
import com.mygdx.game.interact.special_stations.Bin;
import com.mygdx.game.interact.special_stations.BuyChefStation;
import com.mygdx.game.interact.special_stations.Counter;
import com.mygdx.game.interact.special_stations.CustomerCounter;
import com.mygdx.game.interact.special_stations.assembly_stations.BurgerStation;
import com.mygdx.game.interact.special_stations.assembly_stations.JacketPotatoStation;
import com.mygdx.game.interact.special_stations.assembly_stations.PizzaRawStation;
import com.mygdx.game.interact.special_stations.assembly_stations.SaladStation;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;

import java.util.LinkedList;

/**
 * 
 * @author Thomas McCarthy
 * 
 * The Interact Engine renders the various stations in the kitchen, alongside
 * the interaction UI and the timers
 *
 */

public final class InteractEngine {

	static SpriteBatch batch;

	// An array of interactable objects on the screen
	static InteractableBase[] interactables;
	static LinkedList<CustomerCounter> customerCounters;

	// Determines how far away the player must be to interact with a station
	static float interactRange;

	// Textures used for rendering the Progress Slider
	static Texture sliderBackground;
	static Texture sliderFill;

	static Texture stationLock;


	//==========================================================\\
	//                      INITIALISER                         \\
	//==========================================================\\

	public static void initialise(SpriteBatch gameBatch) {
		batch = gameBatch;

		interactables = new InteractableBase[]{

				new CustomerCounter(70, 140),
				new CustomerCounter(70, 210),
				new CustomerCounter(70, 280),

				new BuyChefStation(70, 350),

				new Counter(70, 70),
				new Counter(350, 0),

				new CookingStation(140, 420, false),
				new CookingStation(210, 420, false),
				new CookingStation(280, 420, true),

				new CuttingStation(140, 0, false),
				new CuttingStation(210, 0, false),
				new CuttingStation(280, 0, true),

				new BurgerStation(270, 170),
				new SaladStation(270, 240),
				new JacketPotatoStation(340, 170),
				new PizzaRawStation(340, 240),

				new TomatoStation(570, 210),
				new CheeseStation(570, 280),
				new LettuceStation(570, 140),
				new OnionStation(570, 70),
				new PattyStation(420, 420),
				new BunStation(490, 420),
				new PotatoRawStation(490, 0),
				new TunaStation(350, 420),
				new PizzaBaseStation(570, 350),

				new Bin(420, 0),
		};

		interactRange = 85f;

		sliderBackground = new Texture("slider_background.png");
		sliderFill = new Texture("slider_fill.png");

		stationLock = new Texture("lock.png");

		Rectangle[] collisionRects = new Rectangle[interactables.length];
		for (int i = 0; i < interactables.length; i++) {
			collisionRects[i] = interactables[i].getCollisionRect();
		}
		PlayerEngine.setColliders(collisionRects);
	}


	//==========================================================\\
	//                         UPDATE                           \\
	//==========================================================\\

	public static void update() {
		for (InteractableBase interactable : interactables) {
			// Render the interactable and the ingredient on it
			interactable.getSprite().draw(batch);

			if (interactable instanceof BuyChefStation) {
				batch.draw(((BuyChefStation) interactable).chefLockedTexture, interactable.getXPos() + 10, interactable.getYPos() + 10);

			} else if (interactable instanceof CookingStation || interactable instanceof CuttingStation) {
				if (interactable.isLocked) {
					batch.draw(stationLock, interactable.getXPos() + 10, interactable.getYPos() + 10);
				} else {
					Sprite ingredientSprite = interactable.getIngredientSprite();
					ingredientSprite.setPosition(interactable.getXPos(), interactable.getYPos());
					ingredientSprite.draw(batch);

					// Increment the interactable's timer by the time elapsed between now and the last frame render
					interactable.incrementTime(Gdx.graphics.getDeltaTime());

					// Render a progress slider above the element if it is currently preparing
					if (interactable.isPreparing()) {
						int progressWidth = (int) (interactable.getCurrentTime() / interactable.getPreparationTime() * 65);
						batch.draw(sliderBackground, interactable.getXPos(), interactable.getYPos() + 70, 70, 20);
						batch.draw(sliderFill, interactable.getXPos(), interactable.getYPos() + 72.5f, progressWidth, 15);
					}
				}
			} else {
				Sprite ingredientSprite = interactable.getIngredientSprite();
				ingredientSprite.setPosition(interactable.getXPos(), interactable.getYPos());
				ingredientSprite.draw(batch);

				// Increment the interactable's timer by the time elapsed between now and the last frame render
				interactable.incrementTime(Gdx.graphics.getDeltaTime());

				// Render a progress slider above the element if it is currently preparing
				if (interactable.isPreparing()) {
					int progressWidth = (int) (interactable.getCurrentTime() / interactable.getPreparationTime() * 65);
					batch.draw(sliderBackground, interactable.getXPos(), interactable.getYPos() + 70, 70, 20);
					batch.draw(sliderFill, interactable.getXPos(), interactable.getYPos() + 72.5f, progressWidth, 15);
				}
			}
		}
	}


	//==========================================================\\
	//                   INTERACTION CHECK                      \\
	//==========================================================\\

	/*
		Checks for and initiates an interaction if possible.
		This script is NOT responsible for checking for user input.
		User input is handled by the PlayerEngine, which calls this
		method when required.
		Since the PlayerEngine updates before the InteractEngine,
		the interaction is registered on the frame that the key
		is pressed.
		See PlayerEngine > update() for more.
	 */
	public static void interact() {
		Player activeChef = PlayerEngine.getActiveChef();
		float xPos = activeChef.getXPos();
		float yPos = activeChef.getYPos();



		float minDistance = Float.MAX_VALUE;
		InteractableBase closestInteractable = null;
		for (InteractableBase interactable : interactables) {
			boolean valid = interactable.tryInteraction(xPos, yPos, interactRange);

			if (valid) {
				float xDist = interactable.getXPos() - PlayerEngine.getActiveChef().getXPos();
				float yDist = interactable.getYPos() - PlayerEngine.getActiveChef().getYPos();
				float distance = (float) Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
				if (distance < minDistance) {
					minDistance = distance;
					closestInteractable = interactable;
				}
			}
		}
		if (closestInteractable != null) {
			closestInteractable.handleInteraction();
		}

	}

	public static InteractableBase[] getStations() {
		return interactables;
	}

	public static void replaceWithCounter(InteractableBase station) {
		int index = -1;
		for (int i = 0; i < interactables.length; i++) {
			if (interactables[i].equals(station)) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			Counter newCounter = new Counter(station.getXPos(), station.getYPos());
			interactables[index] = newCounter;
		}
	}

	public static void replaceChefCounterWithCounter() {
		BuyChefStation station = null;
		int index = -1;
		for (int i = 0; i < interactables.length; i++) {
			if (interactables[i] instanceof BuyChefStation) {
				station = (BuyChefStation) interactables[i];
				index = i;
				break;
			}
		}
		if (station != null) {
			Counter newCounter = new Counter(station.getXPos(), station.getYPos());
			interactables[index] = newCounter;
		}
	}

	public static void unlockCookingStation() {
		for (int i = 0; i < interactables.length; i++) {
			if (interactables[i].getXPos() == 280 && interactables[i].getYPos() == 0) {
				CookingStation station = (CookingStation) interactables[i];
				station.isLocked = false;
			}
		}

	}

	public static void unlockCuttingStation() {
		for (int i = 0; i < interactables.length; i++) {
			if (interactables[i].getXPos() == 280 && interactables[i].getYPos() == 420) {
				CuttingStation station = (CuttingStation) interactables[i];
				station.isLocked = false;
			}
		}
	}


	public static boolean getChefStationUnlocked() {
		for (int i = 0; i < interactables.length; i++) {
			if (interactables[i] instanceof BuyChefStation) {
				return false;
			}
		}
		return true;
	}

	public static boolean getCookingStationUnlocked() {
		for (int i = 0; i < interactables.length; i++) {
			if (interactables[i] instanceof CookingStation) {
				if (interactables[i].isLocked) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean getCuttingStationUnlocked() {
		for (int i = 0; i < interactables.length; i++) {
			if (interactables[i] instanceof CuttingStation) {
				if (interactables[i].isLocked) {
					return false;
				}
			}
		}
		return true;
	}

	public static CustomerCounter getRequiredStation(float counterNeededYValue) {
		for (int i = 0; i < interactables.length; i++) {
			if (interactables[i] instanceof CustomerCounter) {
				if (interactables[i].getYPos() == counterNeededYValue) {
					return (CustomerCounter) interactables[i];
				}
			}
		}
		return null;
	}

	public static int getJacketCurrentIndex() {
		for (int i = 0; i < interactables.length; i++) {
			if (interactables[i] instanceof JacketPotatoStation) {
				return ((JacketPotatoStation) interactables[i]).assemblyIndex;
			}
		}
		return 0;
	}

	public static int getPizzaCurrentIndex() {
		for (int i = 0; i < interactables.length; i++) {
			if (interactables[i] instanceof PizzaRawStation) {
				return ((PizzaRawStation) interactables[i]).assemblyIndex;
			}
		}
		return 0;
	}

	public static int getBurgerCurrentIndex() {
		for (int i = 0; i < interactables.length; i++) {
			if (interactables[i] instanceof BurgerStation) {
				return ((BurgerStation) interactables[i]).assemblyIndex;
			}
		}
		return 0;
	}

	public static int getSaladCurrentIndex() {
		for (int i = 0; i < interactables.length; i++) {
			if (interactables[i] instanceof SaladStation) {
				return ((SaladStation) interactables[i]).assemblyIndex;
			}
		}
		return 0;
	}

	public static void setStationIndex(String station,int index) {
		for (int i = 0; i < interactables.length; i++) {
			if (station.equals("PizzaRawStation")) {
				if (interactables[i] instanceof PizzaRawStation) {
					((PizzaRawStation) interactables[i]).assemblyIndex = index;
				}
			} else if (station.equals("JacketPotato")) {
				if (interactables[i] instanceof JacketPotatoStation) {
					((JacketPotatoStation) interactables[i]).assemblyIndex = index;;
				}
			} else if (station.equals("BurgerStation")) {
				if (interactables[i] instanceof BurgerStation) {
					((BurgerStation) interactables[i]).assemblyIndex = index;
				}
			} else if (station.equals("SaladStation")) {
				if (interactables[i] instanceof SaladStation) {
					((SaladStation) interactables[i]).assemblyIndex = index;;
				}
			}
		}
	}
}