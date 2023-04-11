package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.customer.Customer;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.interact.special_stations.CustomerCounter;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;
import com.mygdx.game.player.PowerUps.PowerUpBase;
import com.mygdx.game.player.PowerUps.PowerUpEngine;

import java.util.ArrayList;
import java.util.LinkedList;

public class SaveGame {
    private static final int MAX_INGREDIENTS_PER_CHEF = 4;
    private static final int MAX_CUSTOMERS_AT_ALL_COUNTERS = 4;
    private static final int MAX_POWERUPS_ON_SCREEN = 7;
    private static GameScreen gameScreen;
    private static Preferences prefs;

    public static void initialise(GameScreen screen) {
        gameScreen= screen;
        prefs = Gdx.app.getPreferences("previousSave"); // use a unique name for preferences
    }

    public static void saveGame() {
        prefs.clear();

        prefs.putInteger("reputationPoints", CustomerEngine.getReputationPointsRemaining());
        prefs.putFloat("startTime", gameScreen.masterTimer);
        prefs.putInteger("coins", PlayerEngine.getCoins());
        prefs.putBoolean("chefStationUnlocked", InteractEngine.getChefStationUnlocked());
        prefs.putBoolean("cookingStationUnlocked", InteractEngine.getCookingStationUnlocked());
        prefs.putBoolean("cuttingStationUnlocked", InteractEngine.getCuttingStationUnlocked());
        prefs.putString("difficulty", gameScreen.difficulty);
        prefs.putString("gameMode", gameScreen.gameMode);
        prefs.putInteger("customersRemaining", CustomerEngine.getCustomersRemaining());
        prefs.putInteger("jacketPotatoStationCurrentIngredient", InteractEngine.getJacketCurrentIndex());
        prefs.putInteger("pizzaStationCurrentIngredient", InteractEngine.getPizzaCurrentIndex());
        prefs.putInteger("burgerStationCurrentIngredient", InteractEngine.getBurgerCurrentIndex());
        prefs.putInteger("saladStationCurrentIngredient", InteractEngine.getSaladCurrentIndex());

        // Save chef data
        for (int i = 0; i < PlayerEngine.getAllChefs().size(); i++) {
            Player chef = PlayerEngine.getAllChefs().get(i);
            String chefKey = "chef" + i;
            prefs.putFloat(chefKey + "x", chef.getXPos());
            prefs.putFloat(chefKey + "y", chef.getYPos());

            // Save ingredients for each chef
            for (int j = 0; j < chef.getIngredientStack().getSize(); j++) {
                IngredientName ingredient = chef.getIngredientStack().peekAtDepth(j);
                String ingredientKey = chefKey + "ingredient" + j;
                prefs.putString(ingredientKey, ingredient.toString());
            }
        }

        // Save customers present at the counter
        LinkedList<Customer> customersPresent = CustomerEngine.getCustomers();
        if (customersPresent.size() == 0){
            for (int i = 0; i < customersPresent.size(); i++) {
                Customer customer = customersPresent.get(i);
                String customerKey = "customer" + i;
                prefs.putString(customerKey + "recipe", customer.counter.getRequiredIngredient().toString());
                prefs.putFloat(customerKey + "startTime", customer.getTimeRemaining());
                prefs.putFloat(customerKey + "counterY", customer.getCounter().getYPos());
                prefs.putFloat(customerKey + "reputationLimitTime", customer.getReputationLimitTime());
            }
        }

        // Save powerups present on the screen
        ArrayList<PowerUpBase> powerupsPresent = PowerUpEngine.getPowerups();
        for (int i = 0; i < powerupsPresent.size(); i++) {
            PowerUpBase powerup = powerupsPresent.get(i);
            String powerupKey = "powerup" + i;
            prefs.putString(powerupKey + "type", powerup.getClass().getSimpleName());
            prefs.putFloat(powerupKey + "x", powerup.getXPos());
            prefs.putFloat(powerupKey + "y", powerup.getYPos());
        }




        prefs.flush(); // save changes to preferences immediately
    }

    public static void loadEverythingNew() {

        boolean chefStationUnlocked = prefs.getBoolean("chefStationUnlocked");
        boolean cookingStationUnlocked = prefs.getBoolean("cookingStationUnlocked");
        boolean cuttingStationUnlocked = prefs.getBoolean("cuttingStationUnlocked");

        if (chefStationUnlocked) {
            InteractEngine.replaceChefCounterWithCounter();
        }

        if (cookingStationUnlocked){
            InteractEngine.unlockCookingStation();
        }

        if (cuttingStationUnlocked){
            InteractEngine.unlockCuttingStation();
        }

        int coins = prefs.getInteger("coins");
        int reputationPoints = prefs.getInteger("reputationPoints");
        PlayerEngine.setCoins(coins);
        CustomerEngine.setRepPoints(reputationPoints);

        float startTime = prefs.getFloat("startTime");
        gameScreen.masterTimer = startTime;

        String difficulty = prefs.getString("difficulty");
        gameScreen.difficulty = difficulty;

        int customersRemaining = prefs.getInteger("customersRemaining");
        CustomerEngine.setCustomersRemaining(customersRemaining);

        if (prefs.contains("chef2x")){
            PlayerEngine.addNewChef();
        }
        ;
            // Load chef data
        for (int i = 0; i < PlayerEngine.getAllChefs().size(); i++) {
            Player chef = PlayerEngine.getAllChefs().get(i);
            String chefKey = "chef" + i;
            float x = prefs.getFloat(chefKey + "x", 0);
            float y = prefs.getFloat(chefKey + "y", 0);
            chef.setPosition(x, y);

            // Load ingredients for each chef
            for (int j = 0; j < MAX_INGREDIENTS_PER_CHEF; j++) {
                String ingredientKey = chefKey + "ingredient" + j;
                String ingredientName = prefs.getString(ingredientKey, null);
                if (ingredientName != null) {
                    chef.getIngredientStack().push(IngredientName.valueOf(ingredientName));
                }
            }
        }

        // Load customers present at the counter;
        for (int i = 0; i < MAX_CUSTOMERS_AT_ALL_COUNTERS; i++) {
            String customerKey = "customer" + i;
            String customerIngredient = prefs.getString(customerKey + "recipe", null);;
            if (customerIngredient != null) {
                float chefStartTime = prefs.getFloat(customerKey + "startTime");
                float counterNeededXValue = prefs.getFloat(customerKey + "counterY");
                float reputationLimitTime = prefs.getFloat(customerKey + "reputationLimitTime");

                CustomerCounter counter = InteractEngine.getRequiredStation(counterNeededXValue);


                Customer customer = new Customer(counter,IngredientName.valueOf(customerIngredient), chefStartTime, reputationLimitTime);
                CustomerEngine.getCustomers().add(customer);
            }
        }

        // Load powerups present on the screen;
        for (int i = 0; i < MAX_POWERUPS_ON_SCREEN; i++) {
            String powerupKey = "powerup" + i;
            String powerupType = prefs.getString(powerupKey + "type");
            if (powerupType != null) {
                float x = prefs.getFloat(powerupKey + "x");
                float y = prefs.getFloat(powerupKey + "y");
                PowerUpEngine.createSavedPowerUp(powerupType, x, y);
            }
        }

        int pizzaCurrentIndex = prefs.getInteger("pizzaStationCurrentIngredient");
        int jacketCurrentIndex = prefs.getInteger("jacketPotatoStationCurrentIngredient");
        int burgerCurrentIndex = prefs.getInteger("burgerStationCurrentIngredient");
        int saladCurrentIndex = prefs.getInteger("saladStationCurrentIngredient");

        InteractEngine.setStationIndex("PizzaRawStation",pizzaCurrentIndex);
        InteractEngine.setStationIndex("JacketPotatoStation",jacketCurrentIndex);
        InteractEngine.setStationIndex("BurgerStation",burgerCurrentIndex);
        InteractEngine.setStationIndex("SaladStation",saladCurrentIndex);



    }
    static void checkLoadable() {
        if (!prefs.contains("difficulty") || !prefs.contains("startTime") || !prefs.contains("coins") || !prefs.contains("reputationPoints")) {
            gameScreen.main.goToMenu();
            // Saved game is not loadable so go back to menu
        }
    }

    public static void setGameMode() {
        String startGameMode = prefs.getString("gameMode");
        gameScreen.setGameMode(startGameMode);
    }
}
