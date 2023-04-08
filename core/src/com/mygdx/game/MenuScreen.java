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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen extends InputAdapter implements Screen {

    Stage stage;
    PiazzaPanic main;
    SpriteBatch batch;

    OrthographicCamera camera;
    Viewport viewport;

    final static float WORLD_WIDTH = 1600;
    final static float WORLD_HEIGHT = 1200;

    Integer customerNumber;
    TextureAtlas buttonAtlas;
    Skin skin;


    public MenuScreen(PiazzaPanic main)
    {
        super();
        this.main = main;
    }



    //==========================================================\\
    //                         START                            \\
    //==========================================================\\

    @Override
    public void show() {
        stage = new Stage();

        // Set up camera
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH * aspectRatio, WORLD_HEIGHT * aspectRatio);
        viewport.apply();
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);

        try {
            BitmapFont font = new BitmapFont();
            font.getData().setScale(2.5f);
            buttonAtlas = new TextureAtlas();
            skin = new Skin();
            skin.addRegions(buttonAtlas);
            TextButton.TextButtonStyle textButtonStyleEndless = new TextButton.TextButtonStyle();
            textButtonStyleEndless.fontColor = Color.RED;
            textButtonStyleEndless.font = font;

            // Create Endless Mode button
            TextButton endlessModeButton = new TextButton("Endless Mode", textButtonStyleEndless);
            endlessModeButton.setPosition((stage.getWidth() - endlessModeButton.getWidth()) - 370f, (stage.getHeight() / 2) - 100f);
            endlessModeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    customerNumber = 0;
                    main.startGame("Endless");
                }
            });
            stage.addActor(endlessModeButton);

            // Create Scenario Mode button
            TextButton.TextButtonStyle textButtonStyleScenario = new TextButton.TextButtonStyle();
            textButtonStyleScenario.fontColor = Color.CYAN;
            textButtonStyleScenario.font = font;
            TextButton scenarioModeButton = new TextButton("Scenario Mode", textButtonStyleScenario);
            scenarioModeButton.setPosition((stage.getWidth() - scenarioModeButton.getWidth() - 70f), (stage.getHeight() / 2) - 100f);
            scenarioModeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (customerNumber == null){
                        customerNumber = 5;
                    }
                    main.startGame("Scenario");
                }
            });
            stage.addActor(scenarioModeButton);


            TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
            BitmapFont font1 = new BitmapFont();
            font1.getData().setScale(1.5f);


            textFieldStyle.font = font1;
            textFieldStyle.fontColor = Color.WHITE;
            TextField customerNumberTextField = new TextField("", textFieldStyle);
            customerNumberTextField.setWidth(300);
            customerNumberTextField.setMessageText("Click me and Enter Customers");
            customerNumberTextField.setPosition(scenarioModeButton.getX(),
                    scenarioModeButton.getY() - 75f);
            stage.addActor(customerNumberTextField);

            // Add listener to update customer number variable

            customerNumberTextField.setTextFieldListener(new TextField.TextFieldListener() {
                @Override
                public void keyTyped(TextField textField, char c) {
                    // Get the current text in the text field
                    String text = textField.getText();
                    if (text.length() > 0){
                        if (c == '\b' ) { // check if the pressed key is the delete key
                            textField.setText(text.substring(0, text.length() - 1));
                        }

                        // Check if the text contains only digits and has a length of 4 or less
                        else if (text.matches("\\d{0,4}")) {
                            try {
                                // If the text is valid, update the customer number variable
                                customerNumber = Integer.parseInt(text);
                            } catch (NumberFormatException e) {
                                textField.setText("5");
                            }
                        }
                        else {
                            textField.setText("5");
                        }
                    }
                }
            });
        }


        catch(Exception e)
        {
            System.out.println(e);
        }

        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();
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
