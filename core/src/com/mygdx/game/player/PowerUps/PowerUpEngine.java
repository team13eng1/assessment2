package com.mygdx.game.player.PowerUps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.interact.InteractableBase;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;

import java.util.ArrayList;
import java.util.Random;

public class PowerUpEngine {

    static SpriteBatch batch;

    public static ArrayList<PowerUpBase> interactables;
    
    public static float interactRange;

    private static float[][] spawnLocationsX;
    private static float[][] spawnLocationsY;

    private static float coolDown;
    public static void initialise(SpriteBatch gameBatch) {
        batch = gameBatch;

        interactables = new ArrayList();


        spawnLocationsX = new float[][] {{120, 460}, {120,460}, {395, 430}, {120, 230}};
        spawnLocationsY = new float[][] {{100, 140}, {300,390}, {100, 390}, {100, 390}};

        coolDown = 3f;
        interactRange = 50f;
        
        
    }

    public static void update() {
        for(PowerUpBase interactable : interactables) {
            interactable.getSprite().draw(batch);
        }

        if (coolDown <= 0){
            Random random = new Random();
            int rowCol = random.nextInt(4);
            float randomSpawnX = (float) (spawnLocationsX[rowCol][0] + Math.random() * (spawnLocationsX[rowCol][1] - spawnLocationsX[rowCol][0]));
            float randomSpawnY = (float) (spawnLocationsY[rowCol][0] + Math.random() * (spawnLocationsY[rowCol][1] - spawnLocationsY[rowCol][0]));
            interactables.add(new NoBurning(randomSpawnX, randomSpawnY));
            coolDown = 10f;
        }
        coolDown -= Gdx.graphics.getDeltaTime();
    }

    public static void interact() {
            Player activeChef = PlayerEngine.getActiveChef();
            float xPos = activeChef.getXPos();
            float yPos = activeChef.getYPos();


            System.out.println("\n==============================\nINTERACTION ATTEMPTED");

            float minDistance = Float.MAX_VALUE;
            PowerUpBase closestInteractable = null;
            for(PowerUpBase interactable : interactables)
            {
                boolean valid = interactable.tryInteraction(xPos, yPos, interactRange);

                if(valid)
                {
                    float xDist = interactable.getXPos() - PlayerEngine.getActiveChef().getXPos();
                    float yDist = interactable.getYPos() - PlayerEngine.getActiveChef().getYPos();
                    float distance = (float)Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
                    if(distance < minDistance)
                    {
                        minDistance = distance;
                        closestInteractable = interactable;
                    }
                }
            }
            if(closestInteractable != null)
            {
                closestInteractable.startInteraction();
                interactables.remove(closestInteractable);
            }

            System.out.println("INTERACTION ENDED");
    }
}
