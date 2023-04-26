package com.mygdx.game.player.PowerUps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * The PowerUpEngine class creates and renders the games' power ups. It also handles
 * some of the power up interaction with the chefs.
 *
 */


public class PowerUpEngine {

    static SpriteBatch batch;

    public static ArrayList<PowerUpBase> interactables;

    public static float interactRange;

    private static float[][] spawnLocationsX;
    private static float[][] spawnLocationsY;

    private static String[] allPowerUps;

    private static float coolDown;

    private static float baseCoolDown;

    public static void initialise(SpriteBatch gameBatch) {
        batch = gameBatch;

        interactables = new ArrayList();


        spawnLocationsX = new float[][]{{120, 460}, {120, 460}, {395, 430}, {120, 230}};
        spawnLocationsY = new float[][]{{100, 140}, {300, 390}, {100, 390}, {100, 390}};


        interactRange = 50f;

        allPowerUps = new String[]{"NoBurning", "IncreasedPatience", "ChefSpeedIncrease", "AutoCompleteDish", "GainRepPoint"};


    }

    public static void update() {
        for (PowerUpBase interactable : interactables) {
            interactable.getSprite().draw(batch);
        }

        if (coolDown <= 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(allPowerUps.length);
            String selectedPowerUp = allPowerUps[randomIndex];

            int rowCol = random.nextInt(4);
            float randomSpawnX = (float) (spawnLocationsX[rowCol][0] + Math.random() * (spawnLocationsX[rowCol][1] - spawnLocationsX[rowCol][0]));
            float randomSpawnY = (float) (spawnLocationsY[rowCol][0] + Math.random() * (spawnLocationsY[rowCol][1] - spawnLocationsY[rowCol][0]));

            if (selectedPowerUp.equals("NoBurning")) {
                interactables.add(new NoBurning(randomSpawnX, randomSpawnY));
            } else if (selectedPowerUp.equals("IncreasedPatience")) {
                interactables.add(new HighPatience(randomSpawnX, randomSpawnY));
            } else if (selectedPowerUp.equals("ChefSpeedIncrease")) {
                interactables.add(new SpeedIncrease(randomSpawnX, randomSpawnY));
            } else if (selectedPowerUp.equals("AutoCompleteDish")) {
                interactables.add(new AutoCompleteDish(randomSpawnX, randomSpawnY));
            } else if (selectedPowerUp.equals("GainRepPoint")) {
                interactables.add(new GainReputationPoint(randomSpawnX, randomSpawnY));
            }
            coolDown = baseCoolDown;
        }

        coolDown -= Gdx.graphics.getDeltaTime();
    }

    /**

     Interacts with the closest interactable object within a certain range of the active chef's position.
     Determines the closest interactable object by calculating the distance between each object and the active chef.
     Removes the interactable object from the list of interactables once it has been interacted with.
     @version 1.3
     */

    public static void interact() {
        Player activeChef = PlayerEngine.getActiveChef();
        float xPos = activeChef.getXPos();
        float yPos = activeChef.getYPos();

        float minDistance = Float.MAX_VALUE;
        PowerUpBase closestInteractable = null;
        for (PowerUpBase interactable : interactables) {
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
            closestInteractable.startInteraction();
            interactables.remove(closestInteractable);
        }

    }

    /**
     *
     * Sets the cooldown of the power ups depending on the games difficulty
     *
     * @param diffScaling A number >= 1 that scales the cooldown on the power ups spawning
     * @version 1.4
     */

    public static void setDifficultyCooldown(float diffScaling) {
        baseCoolDown = 10f * diffScaling;
        coolDown = baseCoolDown;
    }

    public static ArrayList<PowerUpBase> getPowerups() {
        return interactables;
    }

    /**
     *
     * Creates a new power-up object and adds it to the list of interactables
     *
     * @param powerUpType The type of the powerup
     * @param x The x-coordinate of the powerup
     * @param y The y-coordinate of the powerup
     * @version 1.4
     */

    public static void createSavedPowerUp(String powerUpType, float x, float y) {

        PowerUpBase powerUp;
        if (powerUpType.equals("NoBurning")) {
            powerUp = new NoBurning(x, y);
        } else if (powerUpType.equals("AutoCompleteDish")) {
            powerUp = new AutoCompleteDish(x, y);
        } else if (powerUpType.equals("GainReputationPoint")) {
            powerUp = new GainReputationPoint(x, y);
        } else if (powerUpType.equals("HighPatience")) {
            powerUp = new HighPatience(x, y);
        } else if (powerUpType.equals("SpeedIncrease")) {
            powerUp = new SpeedIncrease(x, y);
        } else {
            // Handle invalid powerUpType
            return;
        }
        interactables.add(powerUp);
    }

}
