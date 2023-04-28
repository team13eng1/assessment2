package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.customer.CustomerEngine;
import com.mygdx.game.player.PlayerEngine;

/**
 * Represents the End Screen for the game which displays information about what the user did when the game ends
 */


public class EndScreen extends InputAdapter implements Screen {

    Stage stage;
    boolean didUserWin;
    PiazzaPanic main;
    OrthographicCamera camera;
    Viewport viewport;

    final static float WORLD_WIDTH = 1600;
    final static float WORLD_HEIGHT = 1200;


    public EndScreen(PiazzaPanic main, boolean didUserWin)
    {
        super();
        this.main = main;
        this.didUserWin = didUserWin;
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

        Gdx.input.setInputProcessor(stage);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.WHITE;

        int customersServed = CustomerEngine.customersServed;
        float totalGameTime = PlayerEngine.getMasterTime();
        int roundedTime = Math.round(totalGameTime);

        String labelText;
        if (didUserWin) {
            labelText = "ALL CUSTOMERS SERVED SUCCESSFULLY!\nIT TOOK YOU " + roundedTime + " SECONDS TO SERVE " + customersServed + " CUSTOMERS";
        } else {
            labelText = "ALL CUSTOMERS STORMED OUT OF THE RESTAURANT!\nIT TOOK YOU " + roundedTime + " SECONDS TO SERVE " + customersServed + " CUSTOMERS!";
        }

        Label gameOverLabel = new Label(labelText, labelStyle);
        gameOverLabel.setFontScale(3f);
        gameOverLabel.setAlignment(Align.center);
        gameOverLabel.setPosition((float) Gdx.graphics.getWidth() /2, (float) Gdx.graphics.getHeight() /2);
        stage.addActor(gameOverLabel);

    }


    @Override
    public void render(float delta) {
        // Clear the screen and begin drawing process
        Gdx.gl.glClearColor(0, 0, 0, 0);
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
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
