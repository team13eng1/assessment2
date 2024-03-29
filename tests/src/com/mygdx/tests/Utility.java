package com.mygdx.tests;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;

public class Utility {
    // Reset game for fresh tests
    public static void initialiseGame(){
        CustomerEngine.initialise(null, "", 3, null);
        PlayerEngine.initialise(null, null);
        InteractEngine.initialise(null);
    }

}
