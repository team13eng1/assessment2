package com.mygdx.game.customer;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameScreen;
import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.interact.InteractableBase;
import com.mygdx.game.interact.special_stations.CustomerCounter;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * The PlayerEngine class creates and renders the games' three chefs. It also handles
 * chef switching and detects interaction input (although the handling of interactions
 * is then done by the InteractEngine)
 *
 */
public final class CustomerEngine {

    public static int customersServed;
    static SpriteBatch batch;

    static IngredientName[] recipes;
    public static LinkedList<CustomerCounter> customerCounters;
    static LinkedList<Customer> customers;
    static int maxCustomers;
    static float minTimeGap;
    static float maxTimeGap;
    static float timer;

    // This will need to be changed when the customer count can be altered. For endless mode this can be set to -1
    static int numberOfCustomers;

    static Texture customerTexture;

    static GameScreen mainGameScreen;

    static int numReputationPoints;

    static Customer mostRecentCustomer;

    static float repTimeLimit;


    //==========================================================\\
    //                      INITIALISER                         \\
    //==========================================================\\

    public static void initialise(SpriteBatch gameBatch, String startGameMode, int scenarioNumCust, GameScreen gameScreen) {
        mainGameScreen = gameScreen;
        batch = gameBatch;

        // Recipes is the array of items a customer can order
        recipes = new IngredientName[]{
                IngredientName.BURGER,
                IngredientName.SALAD,
                IngredientName.PIZZA,
                IngredientName.JACKET_POTATO_FINISHED
        };

        customerTexture = new Texture("customer.png");
        customerCounters = new LinkedList<>();
        customers = new LinkedList<>();
        minTimeGap = 2f;
        maxTimeGap = 10f;
        timer = 0f;
        numberOfCustomers = 3;
        numReputationPoints = 3;

        if (startGameMode == null){
            gameScreen.main.goToMenu();
        }

        if (startGameMode.equals("Scenario")) {
            maxCustomers = 1;
            numberOfCustomers = scenarioNumCust;
        }
        //Means must be Endless mode
        else {
            numberOfCustomers = -1;
        }
    }



    //==========================================================\\
    //                         UPDATE                           \\
    //==========================================================\\


    /**
     Updates the state of the customer engine by rendering customers, checking for reputation points
     and adding new customers based on a timer.
     If the player's reputation points reach 0, the game is lost.
     @version 1.4
     */

    public static void update() {
        if (getReputationPointsRemaining() <= 0){
            mainGameScreen.loseGame();
        }

        // Create an iterator to traverse the customers collection
        Iterator<Customer> iter = customers.iterator();

        while (iter.hasNext()) {
            Customer c = iter.next();

            // Update the customer
            c.update();
            batch.draw(customerTexture, c.getXPos(), c.getYPos());

            // Check if the customer has waited too long
            if (c.isWaitTooLong(mainGameScreen.masterTimer) && !c.orderComplete){
                c.completeOrder();
                removeReputationPoint();
            }

            // Remove the customer if they have completed their order
            if (c.timeToRemoveMe) {
                iter.remove();
                numberOfCustomers--;
            }
        }

        if (timer <= 0 && customers.size() < maxCustomers && numberOfCustomers != 0 && getFreeCounter() != null) {
            int random = (int) (Math.random() * recipes.length);
            CustomerCounter freeCounter = getFreeCounter();
            Customer customer = new Customer(freeCounter, recipes[random], mainGameScreen.masterTimer, repTimeLimit);
            customers.add(customer);
            mostRecentCustomer = customer;
            timer = minTimeGap + ((float) Math.random() * (maxTimeGap - minTimeGap));
        }

        timer -= Gdx.graphics.getDeltaTime();
    }



    private static CustomerCounter getFreeCounter(){
        for(CustomerCounter counter : customerCounters){
            if (counter.customer == null) {
                return counter;
            }
        }
        return null;
    }


    //==========================================================\\
    //                    GETTERS & SETTERS                     \\
    //==========================================================\\

    public static void addCustomerCounter(CustomerCounter counter) {
        customerCounters.add(counter);
    }

    public static void increasePatience(float patienceBonus) {
        if (customers.size() > 0){
            for (Customer customer : customers){
                customer.reputationLimitTime += patienceBonus;
            }
        }
    }

    public static int getCustomersRemaining() {
        return numberOfCustomers;
    }

    public static void setEndlessMaxCustomers(Integer maxCust) {
        maxCustomers = maxCust;
    }

    public static void removeReputationPoint(){
        numReputationPoints--;
    }

    public static int getReputationPointsRemaining() {
        return numReputationPoints;
    }

    public static Customer getRecentCustomer() {
        return mostRecentCustomer;
    }

    public static void gainRepPoint() {
        numReputationPoints ++;
    }

    public static void setDifficultyRepTime(float diffScaling){
        repTimeLimit = 60f * diffScaling;
    }

    public static void setRepPoints(int repPoints) {
        numReputationPoints = repPoints;
    }

    public static void setCustomersRemaining(int customersRemaining) {
        numberOfCustomers = customersRemaining;
    }

    public static LinkedList<Customer> getCustomers() {
        return customers;
    }

    public static void setRecentCustomer(float mostRecentCustomerCounterY) {
        for (InteractableBase interactable : InteractEngine.getInteractables()) {
            if (interactable instanceof CustomerCounter) {
                if (interactable.getYPos() == mostRecentCustomerCounterY) {
                    mostRecentCustomer = ((CustomerCounter) interactable).customer;
                }
            }
        }
    }
}