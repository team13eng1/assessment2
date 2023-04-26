package com.mygdx.game;

import com.badlogic.gdx.Game;

/**
 * The main game class for Piazza Panic.
 *
 * @version 1.0 Initial release
 * @version 1.1 Added newGame method
 * @version 1.2 Added loadGame method
 * @version 1.3 Added winGame method
 * @version 1.4 Added goToMenu method
 * @version 1.5 Added loseGame method
 * @version 1.6 Added goToIntro method
 */

public class PiazzaPanic extends Game {

	// Screens
	GameScreen gameScreen;
	EndScreen endScreen;
	MenuScreen menuScreen;

	IntroScreen introScreen;

	/**
	 * Initializes the game by showing the intro screen.
	 */

	@Override
	public void create() {
		introScreen = new IntroScreen(this);
		setScreen(introScreen);
	}

	/**
	 * Starts a new game with the specified game mode and difficulty.
	 *
	 * @param gameMode   The game mode to play (e.g. "Scenario", "Endless")
	 * @param difficulty The difficulty level (e.g. "Easy", "Medium", "Hard")
	 * @version 1.1
	 */

	public void newGame(String gameMode, String difficulty) {
		gameScreen = new GameScreen(this, gameMode, difficulty);
		gameScreen.scenarioNumCust = menuScreen.customerNumber;
		setScreen(gameScreen);
	}

	/**
	 * Loads a saved game.
	 *
	 * @version 1.2
	 */

	public void loadGame() {
		gameScreen = new GameScreen(this, true);
		setScreen(gameScreen);
	}

	/**
	 * Shows the end screen for a successful game.
	 *
	 * @version 1.3
	 */

	public void winGame() {
		endScreen = new EndScreen(this, true);
		setScreen(endScreen);
	}

	/**
	 * Shows the menu screen.
	 *
	 * @version 1.4
	 */

	public void goToMenu() {
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

	/**
	 * Shows the end screen for a failed game.
	 *
	 * @version 1.5
	 */

	public void loseGame() {
		endScreen = new EndScreen(this, false);
		setScreen(endScreen);
	}
}
