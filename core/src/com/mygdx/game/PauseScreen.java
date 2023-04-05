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

public class PauseScreen extends InputAdapter implements Screen {

    Stage stage;
    GameScreen gameScreen;
    SpriteBatch batch;

    OrthographicCamera camera;
    Viewport viewport;

    final static float WORLD_WIDTH = 1600;
    final static float WORLD_HEIGHT = 1200;

    public PauseScreen(GameScreen gameScreen) {
        super();
        this.gameScreen = gameScreen;

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

            TextButton.TextButtonStyle textButtonStyleResume = new TextButton.TextButtonStyle();
            textButtonStyleResume.fontColor = Color.CYAN;
            textButtonStyleResume.font = font;

            TextButton resumeButton = new TextButton("Resume", textButtonStyleResume);
            resumeButton.setPosition((stage.getWidth() - resumeButton.getWidth() - 70f), (stage.getHeight() / 2) - 90f);
            resumeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    resumeGame();
                }
            });
            stage.addActor(resumeButton);

            Gdx.input.setInputProcessor(stage);

            batch = new SpriteBatch();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //==========================================================\\
    //                         START                            \\
    //==========================================================\\

    @Override
    public void show() {
        // do nothing
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

    public void resumeGame(){
        gameScreen.hidePauseScreen();
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
