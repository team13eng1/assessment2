package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * Represents the PauseScreen in the game. It provides the user with option to quit the game and also save it.
 *
 */
public class PauseScreen extends InputAdapter implements Screen {

    Stage stage;
    GameScreen gameScreen;
    SpriteBatch batch;

    OrthographicCamera camera;
    Viewport viewport;

    Texture saveTexture;
    Texture resumeTexture;
    Texture exitTexture;

    BitmapFont font;

    String title = "PAUSED";

    final static float WORLD_WIDTH = 1600;
    final static float WORLD_HEIGHT = 1200;

    public PauseScreen(final GameScreen gameScreen) {
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

            font = new BitmapFont();
            font.getData().setScale(3f); // Set font size
            font.setColor(Color.WHITE); // Set font color

            Texture saveTexture = new Texture(Gdx.files.internal("save.png"));
            Texture resumeTexture = new Texture(Gdx.files.internal("play.png"));
            Texture exitTexture = new Texture(Gdx.files.internal("exit.png"));

            ImageButton saveButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(saveTexture)));
            saveButton.setPosition(265, 200);
            saveButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SaveGame.initialise(gameScreen);
                    SaveGame.saveGame();
                }
            });
            stage.addActor(saveButton);

            ImageButton resumeButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(resumeTexture)));
            resumeButton.setPosition(80, 200);
            resumeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    resumeGame();
                }
            });
            stage.addActor(resumeButton);

            ImageButton exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(exitTexture)));
            exitButton.setPosition(450, 200);
            exitButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.exit();
                }
            });
            stage.addActor(exitButton);


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
        font.draw(batch, title, 234,440);
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

    /**
     * resumeGame is called from the user clicking the resume button
     * @version 1.3
     */

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
        stage.dispose();
        saveTexture.dispose();
        resumeTexture.dispose();
        exitTexture.dispose();
        batch.dispose();
    }
}
