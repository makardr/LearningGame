package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.LearningGame;

public class GameScreen implements Screen {
    private final String TAG ="GameScreen";
    private final LearningGame main;
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final Texture img;

    public GameScreen(LearningGame main) {
        this.main=main;
        camera = new OrthographicCamera(50,50);
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void show() {
        Gdx.app.log(TAG,"Screen shown");
    }

    @Override
    public void render(float delta) {
        //Clear the screen (1)
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set ProjectionMatrix of SpriteBatch (2)
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        //Draw image on position 0, 0 with width 25 and height 25 (3)
        batch.draw(img, 0, 0, 25, 25);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
