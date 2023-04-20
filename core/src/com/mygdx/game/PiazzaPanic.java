package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class PiazzaPanic extends Game {

	// Screens
	GameScreen gameScreen;
	EndScreen endScreen;
	MenuScreen menuScreen;

	IntroScreen introScreen;

	@Override
	public void create() {
		introScreen = new IntroScreen(this);
		setScreen(introScreen);
	}

	public void newGame(String gameMode, String difficulty) {
		System.out.println("GAME STARTED");
		gameScreen = new GameScreen(this, gameMode, difficulty);
		gameScreen.scenarioNumCust = menuScreen.customerNumber;
		setScreen(gameScreen);
	}

	public void loadGame() {
		gameScreen = new GameScreen(this, true);
		setScreen(gameScreen);
	}

	public void endGame(String displayDetails) {
		System.out.println("GAME ENDED");
		endScreen = new EndScreen(this, displayDetails);
		setScreen(endScreen);
	}

	public void goToMenu() {
		System.out.println("RETURNED TO MAIN MENU");
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

	public void loseGame(String displayDetails) {
		System.out.println("GAME ENDED");
		endScreen = new EndScreen(this, displayDetails);
		setScreen(endScreen);
	}
}
