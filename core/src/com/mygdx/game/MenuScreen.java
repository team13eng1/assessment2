package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Represents the Main Menu for the game where the user can pick variables to change their experience
 */


public class MenuScreen extends InputAdapter implements Screen {

    Stage stage;
    PiazzaPanic main;
    SpriteBatch batch;

    OrthographicCamera camera;
    Viewport viewport;

    final static float WORLD_WIDTH = 1600;
    final static float WORLD_HEIGHT = 1200;

    Integer customerNumber = null;

    Integer customerNumberTemp = null;
    TextureAtlas buttonAtlas;
    Skin skin;

    String gameMode = null;
    String difficulty = null;

    TextButton easyModeButton;
    TextButton mediumModeButton;
    TextButton hardModeButton;
    Image startGameImage;
    TextField customerNumberTextField;
    Image nextButtonImage;



    /**
     * Creates a new instance of MenuScreen with a given PiazzaPanic game object.
     *
     * @param main the main PiazzaPanic game object
     * @version 1.0
     */


    public MenuScreen(PiazzaPanic main) {
        super();
        this.main = main;
    }


    //==========================================================\\
    //                         START                            \\
    //==========================================================\\

    /**
     * Displays the menu screen with all the different clickable text and images
     *
     * @version 1.0
     */

    @Override
    public void show() {
        stage = new Stage();

        // Set up camera
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH * aspectRatio, WORLD_HEIGHT * aspectRatio);
        viewport.apply();
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);

        BitmapFont font = new BitmapFont();
        font.getData().setScale(2.5f);
        buttonAtlas = new TextureAtlas();
        skin = new Skin();
        skin.addRegions(buttonAtlas);

        loadResetButton();
        loadLoadButton();
        loadGameModeSelection(font);
        loadDifficultyButtons();
        loadCustomerScaler();
        loadNextButton();
        loadStartGame();


        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();
    }

    private void loadResetButton() {
        Texture resetMenuButton = new Texture("resetButton.png");
        Image resetMenuImage = new Image(resetMenuButton);
        resetMenuImage.setSize(128,64);
        resetMenuImage.setPosition(20,410);

        resetMenuImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.goToMenu();
            }
        });
        stage.addActor(resetMenuImage);
        resetMenuImage.setVisible(true);
    }

    private void loadStartGame() {
        Texture startGameButton = new Texture("startGame.png");
        startGameImage = new Image(startGameButton);
        startGameImage.setSize(256,128);

        startGameImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (gameMode == "Scenario"){
                    customerNumber = customerNumberTemp;
                    if (customerNumber == null){
                        customerNumber = 5;
                    }
                } else {
                    customerNumber = -1;
                }
                main.newGame(gameMode, difficulty, customerNumber);
            }
        });
        stage.addActor(startGameImage);
        startGameImage.setVisible(false);
    }

    private void loadNextButton() {
        Texture nextButton = new Texture("nextButton.png");
        nextButtonImage = new Image(nextButton);
        nextButtonImage.setPosition(500,70);

        nextButtonImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                customerNumber = customerNumberTemp;
            }
        });
        stage.addActor(nextButtonImage);
        nextButtonImage.setVisible(false);
    }

    private void loadCustomerScaler() {

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        BitmapFont font1 = new BitmapFont();
        font1.getData().setScale(1.5f);

        textFieldStyle.font = font1;
        textFieldStyle.fontColor = Color.WHITE;
        customerNumberTextField = new TextField("", textFieldStyle);
        customerNumberTextField.setWidth(550);
        customerNumberTextField.setMessageText("Click me, Type Number of Customers, Then Click Start!");
        customerNumberTextField.setPosition(50, 175);
        customerNumberTextField.setAlignment(Align.center);

        // Add listener to update customer number variable

        customerNumberTextField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                // Get the current text in the text field
                String text = textField.getText();
                if (text.length() > 0) {
                    if (c == '\b') { // check if the pressed key is the delete key
                        textField.setText(text.substring(0, text.length() - 1));
                    }

                    // Check if the text contains only digits and has a length of 4 or less
                    else if (text.matches("\\d{0,4}")) {
                        try {
                            // If the text is valid, update the customer number variable
                            customerNumberTemp = Integer.parseInt(text);
                        } catch (NumberFormatException e) {
                            textField.setText("5");
                        }
                    } else {
                        textField.setText("5");
                    }
                } customerNumberTemp = Integer.valueOf(text);
            }
        });
        stage.addActor(customerNumberTextField);
        customerNumberTextField.setVisible(false);
    }

    private void loadGameModeSelection(BitmapFont font){
        final TextButton.TextButtonStyle textButtonStyleEndless = new TextButton.TextButtonStyle();
        textButtonStyleEndless.fontColor = Color.GREEN;
        textButtonStyleEndless.font = font;

        final TextButton.TextButtonStyle textButtonStyleScenario = new TextButton.TextButtonStyle();
        textButtonStyleScenario.fontColor = Color.GREEN;
        textButtonStyleScenario.font = font;

        final TextButton scenarioModeButton = new TextButton("Scenario Mode", textButtonStyleScenario);

        // Create Endless Mode button
        final TextButton endlessModeButton = new TextButton("Endless Mode", textButtonStyleEndless);
        endlessModeButton.setPosition(370,100);
        endlessModeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameMode = "Endless";
                scenarioModeButton.setVisible(false);
                endlessModeButton.setVisible(false);
                easyModeButton.setVisible(true);
                mediumModeButton.setVisible(true);
                hardModeButton.setVisible(true);
            }
        });
        stage.addActor(endlessModeButton);
        endlessModeButton.setVisible(true);


        scenarioModeButton.setPosition(35, 100);
        scenarioModeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameMode = "Scenario";
                scenarioModeButton.setVisible(false);
                endlessModeButton.setVisible(false);
                easyModeButton.setVisible(true);
                mediumModeButton.setVisible(true);
                hardModeButton.setVisible(true);
            }
        });
        stage.addActor(scenarioModeButton);
        scenarioModeButton.setVisible(true);
    }
    
    
    private void loadLoadButton() {

        BitmapFont loadGameFont = new BitmapFont();
        loadGameFont.getData().setScale(2.5f);

        final TextButton.TextButtonStyle textButtonStyleLoad = new TextButton.TextButtonStyle();
        textButtonStyleLoad.fontColor = Color.GREEN;
        textButtonStyleLoad.font = loadGameFont;

        TextButton loadGameButton = new TextButton("Load Game", textButtonStyleLoad);
        loadGameButton.setPosition(410, 430);
        loadGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.loadGame();
            }
        });

        stage.addActor(loadGameButton);
        loadGameButton.setVisible(true);
    }

    /**
     * Loads and configures the difficulty selection buttons using the given font.
     *
     * @version 1.1
     */

    private void loadDifficultyButtons() {
        BitmapFont difficultyFont = new BitmapFont();
        difficultyFont.getData().setScale(4f);

        final TextButton.TextButtonStyle textButtonStyleEasy = new TextButton.TextButtonStyle();
        textButtonStyleEasy.fontColor = Color.GREEN;
        textButtonStyleEasy.font = difficultyFont;

        final TextButton.TextButtonStyle textButtonStyleMedium = new TextButton.TextButtonStyle();
        textButtonStyleMedium.fontColor = Color.ORANGE;
        textButtonStyleMedium.font = difficultyFont;


        final TextButton.TextButtonStyle textButtonStyleHard = new TextButton.TextButtonStyle();
        textButtonStyleHard.fontColor = Color.RED;
        textButtonStyleHard.font = difficultyFont;

        easyModeButton = new TextButton("Easy", textButtonStyleEasy);
        easyModeButton.setPosition(40, 105);
        easyModeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difficulty = "Easy";
                easyModeButton.setVisible(false);
                mediumModeButton.setVisible(false);
                hardModeButton.setVisible(false);
                if (gameMode == "Scenario"){
                    customerNumberTextField.setVisible(true);
                }
                startGameImage.setVisible(true);
                if (gameMode == "Scenario"){
                    startGameImage.setPosition(193,20);
                } else {
                    startGameImage.setPosition(193,70);
                }
            }
        });

        mediumModeButton = new TextButton("Medium", textButtonStyleMedium);
        mediumModeButton.setPosition(230, 105);
        mediumModeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difficulty = "Medium";
                easyModeButton.setVisible(false);
                mediumModeButton.setVisible(false);
                hardModeButton.setVisible(false);
                if (gameMode == "Scenario"){
                    customerNumberTextField.setVisible(true);
                }
                startGameImage.setVisible(true);
                if (gameMode == "Scenario"){
                    startGameImage.setPosition(193,20);
                } else {
                    startGameImage.setPosition(193,70);
                }
            }
        });

        hardModeButton = new TextButton("Hard", textButtonStyleHard);
        hardModeButton.setPosition(492,105) ;
        hardModeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difficulty = "Hard";
                easyModeButton.setVisible(false);
                mediumModeButton.setVisible(false);
                hardModeButton.setVisible(false);
                if (gameMode == "Scenario"){
                    customerNumberTextField.setVisible(true);
                }
                startGameImage.setVisible(true);
                if (gameMode == "Scenario"){
                    startGameImage.setPosition(193,20);
                } else {
                    startGameImage.setPosition(193,70);
                }
            }
        });

        stage.addActor(easyModeButton);
        stage.addActor(mediumModeButton);
        stage.addActor(hardModeButton);

        easyModeButton.setVisible(false);
        mediumModeButton.setVisible(false);
        hardModeButton.setVisible(false);
    }

    @Override
    public void render(float delta) {
        // Clear the screen and begin drawing process
        Gdx.gl.glClearColor(0, 0, 0, 0);
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        stage.draw();

        Texture gameLogo = new Texture("game_logo.png");
        batch.draw(gameLogo, (stage.getWidth() - gameLogo.getWidth()) / 2, (stage.getHeight() / 2) - 75f);

        batch.end();
    }
    @Override
    public void resize(int width, int height) {
        viewport.setWorldSize(WORLD_WIDTH, WORLD_HEIGHT);
        viewport.setScreenSize(width, height);
        viewport.apply();
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        stage.getViewport().update(width, height, true);
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
}
