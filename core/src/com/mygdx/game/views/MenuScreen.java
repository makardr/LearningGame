package com.mygdx.game.views;

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
    private Skin skin;

    public MenuScreen(LearningGame main) {
        this.main = main;
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Show");

        Gdx.input.setInputProcessor(stage);

        main.b2dAssetManager.queueAddSkin();
        main.b2dAssetManager.manager.finishLoading();
        skin = main.b2dAssetManager.manager.get("skin/uiskin.json");

        TextButton newGame = new TextButton("New Game", skin);
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.changeScreen(LearningGame.GAMESCREEN);
            }
        });

        TextButton preferences = new TextButton("Preferences", skin);
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
        table.setDebug(true);

        table.add(newGame).fillX().uniformX().uniformY().width(500).height(150);
        table.row().pad(10, 0, 10, 0);
        table.add(preferences).fillX().uniformX().uniformY();
        table.row();
        table.add(exit).fillX().uniformX().uniformY();

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
//        Clear screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        Gdx.app.log(TAG,"Render");
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
