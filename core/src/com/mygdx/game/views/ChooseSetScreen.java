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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.LearningGame;
import com.mygdx.game.util.MyDataSet;

import java.util.ArrayList;

public class ChooseSetScreen implements Screen {
    private final String TAG = "ChooseSetScreen";
    private final LearningGame main;
    private ArrayList<MyDataSet> mainArrayList;
    private Stage stage;
    private Skin skin;


    public ChooseSetScreen(LearningGame main) {
        this.main = main;
        skin = main.myAssetManager.manager.get("skin/uiskin.json");
        stage = new Stage(new ScreenViewport());
    }
    @Override
    public void show() {
        Gdx.app.log(TAG, "Show");
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        mainArrayList=main.getPreferences().getArrayList();
        stage.addActor(createTable());
    }

    private Actor createTable() {
        Gdx.app.log(TAG, "Creating new table");
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        Label tooltipLabel = new Label("Choose existing word set to learn or edit your word sets", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(tooltipLabel);
        table.row();
        int id=0;
        for (final MyDataSet wordSet : mainArrayList) {
            TextButton chooseSet = new TextButton(wordSet.getSetName(), skin);
            final int finalId = id;
            chooseSet.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
//                    set screen to id
                    main.setId(finalId);
                    main.changeScreen(LearningGame.GAMESCREEN);
                }
            });
            table.add(chooseSet).fillX().uniformX().uniformY().width(400).height(100).padLeft(10).padBottom(10);

            final TextButton deleteSet = new TextButton("Delete", skin);
            deleteSet.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    main.getPreferences().removeArrayList(finalId);
                    main.changeScreen(LearningGame.CHOOSESET);
                }
            });
            table.add(deleteSet).width(100).height(100).padLeft(10).padBottom(10);
            table.row();
            id+=1;
        }
        final TextButton addSet = new TextButton("Add set", skin);
        addSet.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.changeScreen(LearningGame.EDITSET);
//                Temporary for debug purposes
//                main.getPreferences().createPlaceholderData();
//                main.changeScreen(LearningGame.CHOOSESET);
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
