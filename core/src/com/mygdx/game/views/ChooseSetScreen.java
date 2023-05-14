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
import com.badlogic.gdx.utils.Align;
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
    private BitmapFont font;

    public ChooseSetScreen(LearningGame main) {
        this.main = main;
        skin = main.myAssetManager.manager.get(main.myAssetManager.skin);
        stage = new Stage(new ScreenViewport());
        font = main.myAssetManager.manager.get(main.myAssetManager.font);
        font.getData().setScale(1f);
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Show");
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        mainArrayList = main.getPreferences().getArrayList();
        stage.addActor(createTable());
        stage.addActor(createSecondTable());
    }

    private Actor createTable() {
        Gdx.app.log(TAG, "Creating new table");
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(LearningGame.DEBUG);
        table.setBounds(0,200,Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/3*2);
        Label tooltipLabel = new Label("Choose existing word set", new Label.LabelStyle(font, Color.BLACK));
        table.add(tooltipLabel).width(100).height(100).align(Align.left).padLeft(100);
        table.row();
        int id = 0;

        Gdx.app.log(TAG,mainArrayList.toString());
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
            table.add(chooseSet).width(400).height(100).padLeft(10).padBottom(10);

            final TextButton deleteSet = new TextButton("Del.", skin);
            deleteSet.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    main.getPreferences().removeArrayList(finalId);
                    main.changeScreen(LearningGame.CHOOSESET);
                }
            });
            table.add(deleteSet).width(100).height(100).padLeft(10).padBottom(10);
            table.row();
            id += 1;
        }
//        table.add(createSecondTable());
        return table;
    }
    private Actor createSecondTable() {
        Table table = new Table();
        table.setFillParent(false);
        table.setDebug(LearningGame.DEBUG);
        table.setBounds(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/3);
        table.defaults().width(400).height(100).padBottom(10);

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
        table.add(addSet);
        table.row();
        table.add(back);
        table.row();
        return table;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(LearningGame.R, LearningGame.G, LearningGame.B, LearningGame.A);
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
