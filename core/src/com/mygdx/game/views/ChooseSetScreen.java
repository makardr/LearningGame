package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.LearningGame;

public class ChooseSetScreen implements Screen {
    private final String TAG = "ChooseSetScreen";
    private final LearningGame main;
    private Stage stage;
    private Skin skin;

    private Array<String> wordSet;

    public ChooseSetScreen(LearningGame main) {
        this.main = main;
        skin = main.myAssetManager.manager.get("skin/uiskin.json");
//        Test word set
        wordSet = new Array<String>();
        wordSet.add("Example set 1");
        wordSet.add("Example set 2");
        wordSet.add("Example set 3");
        stage = new Stage(new ScreenViewport());
    }

    private Actor createTable() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        Label tooltipLabel = new Label("Choose existing word set to learn or edit your word sets", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(tooltipLabel);
        table.row();
        for (final String setName : wordSet) {

            TextButton chooseSet = new TextButton(setName, skin);
            chooseSet.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    main.setCurrentSet(setName);
                    main.changeScreen(LearningGame.GAMESCREEN);
                }
            });
            table.add(chooseSet).fillX().uniformX().uniformY().width(400).height(100).padLeft(10).padBottom(10);

            final TextButton deleteSet = new TextButton("Delete", skin);
            deleteSet.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    deleteSet(setName);
                    main.changeScreen(LearningGame.CHOOSESET);
                }
            });
            table.add(deleteSet).width(100).height(100).padLeft(10).padBottom(10);
            table.row();
        }
        final TextButton addSet = new TextButton("Add set", skin);
        addSet.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.changeScreen(LearningGame.EDITSET);
            }
        });
        final TextButton back = new TextButton("Back", skin);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.changeScreen(LearningGame.MENU);
            }
        });
        table.add(addSet).width(400).height(100).padBottom(10).center().padLeft(10);
        table.row();
        table.add(back).width(400).height(100).padBottom(10).center().padLeft(10);
        table.row();
        return table;
    }

    private void deleteSet(String setName) {
        wordSet.pop();
    }


    @Override
    public void show() {
        Gdx.app.log(TAG, "Show");
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        stage.addActor(createTable());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //  clear the screen
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
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
