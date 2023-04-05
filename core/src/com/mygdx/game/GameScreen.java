package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
 * @author Thomas McCarthy
 * 
 * The GameScreen class handles the main rendering and updating of the game.
 *
 */

public class GameScreen extends InputAdapter implements Screen {

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
	public PiazzaPanic main = null;

	public int scenarioNumCust;

	private Label reputationLabel;

	private Image heartImage;

	private Image saveImage;

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
	}

	//==========================================================\\
	//                         START                            \\
	//==========================================================\\
	@Override
	public void show() {
		stage = new Stage();

		SaveGame.initialise(this);
		if (wantsToBeLoaded){
			SaveGame.checkLoadable();
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
		heartImage = new Image(heartTexture);
		heartImage.setPosition(596, Gdx.graphics.getHeight() - 33);
		heartImage.setScale(1.5f);

		Texture saveTexture = new Texture(Gdx.files.internal("reputation_points.png"));
		saveImage = new Image(saveTexture);
		saveImage.setPosition(30, 20);
		saveImage.setScale(1.5f);



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
		stage.addActor(saveImage);

		ClickListener listener = new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SaveGame.saveGame();
				Gdx.app.exit();
			}
		};
		saveImage.addListener(listener);

	}


	//==========================================================\\
	//                        UPDATE                            \\
	//==========================================================\\
	@Override
	public void render(float delta) {

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
			main.endGame("SCENARIO COMPLETED IN\n" + (int) masterTimer + " seconds");
		}
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

	public void setDifficulty(String difficulty) {
		if (difficulty.equals("Easy")) {
			for (Player player : PlayerEngine.getAllChefs()) {
				player.setSpeedDifficulty(1.0f);
			}
			CustomerEngine.setDifficultyRepTime(1.0f);
			PowerUpEngine.setDifficultyCooldown(1.0f);
		} else if (difficulty.equals("Medium")) {
			for (Player player : PlayerEngine.getAllChefs()) {
				player.setSpeedDifficulty(0.8f);
			}
			CustomerEngine.setDifficultyRepTime(0.8f);
			PowerUpEngine.setDifficultyCooldown(0.8f);
		} else {
			for (Player player : PlayerEngine.getAllChefs()) {
				player.setSpeedDifficulty(0.5f);
			}
			CustomerEngine.setDifficultyRepTime(0.5f);
			PowerUpEngine.setDifficultyCooldown(0.5f);
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
		main.loseGame("You have Lost!");
	}

	public void setGameMode(String startGameMode) {
		gameMode = startGameMode;
	}
}
