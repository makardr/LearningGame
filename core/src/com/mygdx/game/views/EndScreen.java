package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.LearningGame;

public class EndScreen implements Screen {
    private final LearningGame main;
    private float elapsed;
    private boolean shouldChangeScreen;

    public EndScreen(LearningGame main) {
        this.main = main;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //  clear the screen
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        elapsed += delta;

        // Render or do whatever you do here

        if (elapsed > 1.0) {
            shouldChangeScreen = true;
            elapsed=0;
        }

        if (shouldChangeScreen) {
            main.changeScreen(main.GAMESCREEN);

//            main.setScreen(new GameScreen(main));
        }

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
