package com.mygdx.tests;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;

public class Utility {
    // Reset game for fresh tests
    static void initialiseGame(){
        CustomerEngine.initialise(null);
        PlayerEngine.initialise(null);
        InteractEngine.initialise(null);
    }

}
