package com.mygdx.game.views;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.LearningGame;

public class MenuScreen implements Screen {
    private final String TAG = "MenuScreen";
    private final LearningGame main;
    private final Stage stage;
    private int multiplier=1;
    private Skin skin;

    public MenuScreen(LearningGame main) {
        this.main = main;
        stage = new Stage(new ScreenViewport());
        if(Gdx.app.getType() == Application.ApplicationType.Android) {
            multiplier=2;
        }
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Show");

        Gdx.input.setInputProcessor(stage);
        skin = main.myAssetManager.manager.get(main.myAssetManager.skin);

        TextButton newGame = new TextButton("Start Game", skin);

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.changeScreen(LearningGame.CHOOSESET);
            }
        });

        TextButton preferences = new TextButton("Settings", skin);
        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.changeScreen(LearningGame.PREFERENCES);
            }
        });

        TextButton exit = new TextButton("Exit", skin);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(LearningGame.DEBUG);
        table.defaults().width(400*multiplier).height(100*multiplier).padBottom(10*multiplier);

        table.add(newGame).fillX().uniformX().uniformY();
        table.row();
        table.add(preferences).fillX().uniformX().uniformY();
        table.row();
        table.add(exit).fillX().uniformX().uniformY();

        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(LearningGame.R, LearningGame.G, LearningGame.B, LearningGame.A);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
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
        stage.dispose();
        skin.dispose();
    }
}
