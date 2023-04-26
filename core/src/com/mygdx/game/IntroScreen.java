package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Represents the Intro Screen for the game which gives the User information about the game
 * Has all the standard properties of the other Screens
 */

public class IntroScreen extends InputAdapter implements Screen {

    Stage stage;
    PiazzaPanic main;
    SpriteBatch batch;

    OrthographicCamera camera;
    Viewport viewport;

    final static float WORLD_WIDTH = 1600;
    final static float WORLD_HEIGHT = 1200;

    public IntroScreen(PiazzaPanic main) {
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
            Texture introScreen = new Texture("introScreen.png");
            Image introScreenImage = new Image(introScreen);
            introScreenImage.setPosition(0,0);
            introScreenImage.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            stage.addActor(introScreenImage);

            Texture nextButton = new Texture("nextButton.png");
            Image nextButtonImage = new Image(nextButton);
            nextButtonImage.setPosition(500,70);

            nextButtonImage.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    main.goToMenu();
                }
            });
            stage.addActor(nextButtonImage);


            Gdx.input.setInputProcessor(stage);

            batch = new SpriteBatch();



        } catch (Exception e) {
            System.out.println(e);
        }
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
