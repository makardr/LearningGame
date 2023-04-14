package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.LearningGame;

public class LoadingScreen implements Screen {
    private final LearningGame main;
    private final SpriteBatch spriteBatch;

    private TextureAtlas.AtlasRegion loading_circle_grey;
    private TextureAtlas atlas;

    public LoadingScreen(LearningGame main) {
        this.main = main;
        spriteBatch = new SpriteBatch();
        loadAssets();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //  clear the screen
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        spriteBatch.draw(loading_circle_grey, Gdx.graphics.getWidth() / 2 - 25, Gdx.graphics.getHeight() / 2 - 25, 50, 50);
        spriteBatch.end();

        main.changeScreen(LearningGame.MENU);

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
        spriteBatch.dispose();
        atlas.dispose();
    }

    private void loadAssets() {
//        Load loading
        main.b2dAssetManager.queueAddLoadingImages();
        main.b2dAssetManager.manager.finishLoading();
        atlas = main.b2dAssetManager.manager.get("images/loading.atlas");
        loading_circle_grey = atlas.findRegion("loading_circle_grey");
//        Load game
        main.b2dAssetManager.queueAddSkin();
        main.b2dAssetManager.queueAddImages();
        main.b2dAssetManager.queueAddFonts();
        main.b2dAssetManager.manager.finishLoading();
    }
}
