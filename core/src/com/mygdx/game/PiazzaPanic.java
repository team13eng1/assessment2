package com.mygdx.game;

import com.badlogic.gdx.Game;

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
		gameScreen = new GameScreen(this, gameMode, difficulty);
		gameScreen.scenarioNumCust = menuScreen.customerNumber;
		setScreen(gameScreen);
	}

	public void loadGame() {
		gameScreen = new GameScreen(this, true);
		setScreen(gameScreen);
	}

	public void winGame() {
		endScreen = new EndScreen(this, true);
		setScreen(endScreen);
	}

	public void goToMenu() {
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

	public void loseGame() {
		endScreen = new EndScreen(this, false);
		setScreen(endScreen);
	}

    public void goToIntro() {
		introScreen = new IntroScreen(this);
		setScreen(introScreen);
    }
}
