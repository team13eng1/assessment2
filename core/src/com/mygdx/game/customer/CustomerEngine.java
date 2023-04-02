package com.mygdx.game.customer;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameScreen;
import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.special_stations.CustomerCounter;

import java.util.LinkedList;

/**
 *
 * @author Thomas McCarthy
 *
 * The PlayerEngine class creates and renders the games' three chefs. It also handles
 * chef switching and detects interaction input (although the handling of interactions
 * is then done by the InteractEngine)
 *
 */
public final class CustomerEngine {

    static SpriteBatch batch;

    static IngredientName[] recipes;
    static LinkedList<CustomerCounter> customerCounters;
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

    public static void update() {
        if (getReputationPointsRemaining() <= 0){
            mainGameScreen.loseGame();
        }

        // Render the customers
        for (Customer c : customers) {
            c.update();
            batch.draw(customerTexture, c.getXPos(), c.getYPos());
            if (c.isWaitTooLong(mainGameScreen.masterTimer) && !c.finished){
                c.finishWithThisCustomer();
                c.counter.resetCounter();
                removeReputationPoint();
            }
        }
        if (timer <= 0 && customers.size() < maxCustomers && numberOfCustomers != 0) {

            int random = (int) (Math.random() * recipes.length);
            CustomerCounter freeCounter = getFreeCounter();
            Customer customer = new Customer(freeCounter, recipes[random], mainGameScreen.masterTimer, 20f);
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


    public static void removeCustomer(Customer customer) {
        customers.remove(customer);
        numberOfCustomers--;
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
}
