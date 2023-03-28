package com.mygdx.tests;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.customer.CustomerEngine;

public class Utility {
    // Reset game for fresh tests
    static void initialiseGame(){
        CustomerEngine.initialise(null);

    }

}
