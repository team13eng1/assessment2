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

    TextButton playButton;
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
        System.out.print(Gdx.graphics.getWidth());
        System.out.print(Gdx.graphics.getHeight());
        stage = new Stage();

        // Set up camera
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH * aspectRatio, WORLD_HEIGHT * aspectRatio);
        viewport.apply();
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);

        try
        {
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
            endlessModeButton.setPosition((stage.getWidth() - endlessModeButton.getWidth()) - 370f , (stage.getHeight() / 2) - 100f);
            endlessModeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y)
                {
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
                public void clicked(InputEvent event, float x, float y)
                {
                    main.startGame("Scenario");
                }
            });
            stage.addActor(scenarioModeButton);
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

        batch.draw(new Texture("logo_1.png"), 0, 0);
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
