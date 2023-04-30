package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerEngine;
import com.mygdx.game.player.PowerUps.PowerUpEngine;

/**
 * 
 * The GameScreen class handles the main rendering and updating of the game.
 *
 */

public class GameScreen extends InputAdapter implements Screen {

	private boolean shouldCallShow = true;
	private PauseScreen pauseScreen;
	public String difficulty;
	Stage stage;
	SpriteBatch batch;

	String gameMode;
	OrthographicCamera camera;
	Viewport viewport;
	final static float WORLD_WIDTH = 1600;
	final static float WORLD_HEIGHT = 1200;

	// A timer to track how long the screen has been running
	public float masterTimer;
	private Label timerLabel;

	// A reference to the main game file
	public PiazzaPanic main;

	public int scenarioNumCust;

	private Label reputationLabel;


	public boolean wantsToBeLoaded;


	public GameScreen(PiazzaPanic main, String gameMode, String difficulty) {
		super();
		this.main = main;
		wantsToBeLoaded = false;
		this.gameMode = gameMode;

		if (difficulty == null) {
			this.difficulty = "Easy";
		} else {
			this.difficulty = difficulty;
		}
	}

	public GameScreen(PiazzaPanic main, boolean wantsLoaded) {
		super();
		this.main = main;
		wantsToBeLoaded = wantsLoaded;
		SaveGame.initialise(this);
	}

	//==========================================================\\
	//                         START                            \\
	//==========================================================\\

	/**
	 * Initialises all the different components of the game (Engines)
	 * Loads in previous saves if necessary
	 * @version 1.5
	 */

	@Override
	public void show() {
		if (shouldCallShow){
			stage = new Stage();

			if (wantsToBeLoaded){
				if (!SaveGame.checkLoadable()){
					System.out.printf("hello");
				}
				//if its loadable continue
				//Initial loading procedure
				SaveGame.setGameMode();
			}

			// Set up camera
			float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
			camera = new OrthographicCamera();
			viewport = new FitViewport(WORLD_WIDTH * aspectRatio, WORLD_HEIGHT * aspectRatio);
			viewport.apply();
			camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);

			// Create processor to handle user input
			Gdx.input.setInputProcessor(stage);
			batch = new SpriteBatch();

			// Initialise Engine scripts
			PlayerEngine.initialise(batch, this);
			CustomerEngine.initialise(batch, gameMode, scenarioNumCust, this);
			InteractEngine.initialise(batch);
			PowerUpEngine.initialise(batch);


			masterTimer = 0f;

			//full loading procedure
			if (wantsToBeLoaded){
				SaveGame.loadEverythingNew();
			}
			setDifficulty(difficulty);


			Texture heartTexture = new Texture(Gdx.files.internal("reputation_points.png"));
			Image heartImage = new Image(heartTexture);
			heartImage.setPosition(596, Gdx.graphics.getHeight() - 33);
			heartImage.setScale(1.5f);


			Label.LabelStyle labelStyle = new Label.LabelStyle();
			BitmapFont font = new BitmapFont();
			font.getData().setScale(1.5f);
			labelStyle.font = font;
			labelStyle.fontColor = Color.BLACK;

			timerLabel = new Label("0s", labelStyle);
			timerLabel.setPosition(80, Gdx.graphics.getHeight() - 38);
			timerLabel.setAlignment(Align.left);
			stage.addActor(timerLabel);

			reputationLabel = new Label("3", labelStyle);
			reputationLabel.setPosition(580, Gdx.graphics.getHeight() - 38);
			reputationLabel.setAlignment(Align.left);
			stage.addActor(reputationLabel);


			stage.addActor(heartImage);

			shouldCallShow = false;

		}

	}


	//==========================================================\\
	//                        UPDATE                            \\
	//==========================================================\\

	/**
	 *
	 * Updates all the Engines and timer variables
	 *
	 * @param delta The time in seconds since the last render.
	 * @version 1.2
	 */


	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			showPauseScreen();
		}

		// Clear the screen and begin drawing process
		Gdx.gl.glClearColor(1, 1, 1, 0);
		ScreenUtils.clear(1, 1, 1, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		stage.draw();


		// Update the render
		PlayerEngine.update();
		InteractEngine.update();
		CustomerEngine.update();
		PowerUpEngine.update();

		// End the process
		batch.end();


		// Increment the timer and update UI
		masterTimer += Gdx.graphics.getDeltaTime();
		timerLabel.setText((int) masterTimer);

		// check and change rep point counter

		reputationLabel.setText(CustomerEngine.getReputationPointsRemaining());

		//Will only change the variables for endless mode
		if (gameMode.equals("Endless")) {
			if (masterTimer > 30 && masterTimer < 70) {
				CustomerEngine.setEndlessMaxCustomers(2);
			} else if (masterTimer >= 70) {
				CustomerEngine.setEndlessMaxCustomers(3);
			} else {
				CustomerEngine.setEndlessMaxCustomers(1);
			}
		}

		// Check for game over state
		if (CustomerEngine.getCustomersRemaining() == 0 && main != null) {
			main.winGame();
		}
	}

	/**
	 * sets the screen to the Pause screen
	 * @version 1.4
	 */

	public void showPauseScreen(){
		if (pauseScreen == null){
			pauseScreen = new PauseScreen(this);
		}

		main.setScreen(pauseScreen);
		Gdx.input.setInputProcessor(pauseScreen.stage);
		pause();
	}

	/**
	 *
	 * Hides the pause screen and resumes the game
	 *
	 */

	public void hidePauseScreen(){
		main.setScreen(this);
		Gdx.input.setInputProcessor(stage);
		resume();
	}


	//==========================================================\\
	//                 OTHER REQUIRED METHODS                   \\
	//==========================================================\\

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(viewport.getWorldWidth() / 2f, viewport.getWorldHeight() / 2f, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

	}

	/**
	 *
	 * modifies different variables depending on the difficulty the user has chosen to play with
	 *
	 * @param difficulty  a String representing "Easy" "Medium" or "Hard"
	 */

	public void setDifficulty(String difficulty) {
		if (difficulty.equals("Easy")) {
			for (Player player : PlayerEngine.getAllChefs()) {
				player.setSpeedDifficulty(1.0f);
			}
			CustomerEngine.setDifficultyRepTime(1.0f);
			PowerUpEngine.setDifficultyCooldown(0.8f);
		} else if (difficulty.equals("Medium")) {
			for (Player player : PlayerEngine.getAllChefs()) {
				player.setSpeedDifficulty(0.8f);
			}
			CustomerEngine.setDifficultyRepTime(0.8f);
			PowerUpEngine.setDifficultyCooldown(1.2f);
		} else {
			for (Player player : PlayerEngine.getAllChefs()) {
				player.setSpeedDifficulty(0.5f);
			}
			CustomerEngine.setDifficultyRepTime(0.5f);
			PowerUpEngine.setDifficultyCooldown(1.8f);
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

	public void loseGame() {
		main.loseGame();
	}

	public void setGameMode(String startGameMode) {
		gameMode = startGameMode;
	}
}
