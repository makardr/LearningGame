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
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.LearningGame;

public class PreferencesScreen implements Screen {
    private final String TAG = "PreferencesScreen";
    private LearningGame main;
    private Stage stage;
    private Skin skin;
    private TextField livesSetting;

    public PreferencesScreen(LearningGame main) {
        this.main = main;
        stage = new Stage(new ScreenViewport());
        skin = main.myAssetManager.manager.get("skin/uiskin.json");

    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Show");
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(createTable());
    }
    private Actor createTable() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);

        BitmapFont font = new BitmapFont();

        Label speedLabel = new Label("Game speed", new Label.LabelStyle(font, Color.WHITE));
        Slider gameSpeedSlider = new Slider(0f, 1f, 0.1f, false, skin);
        gameSpeedSlider.setValue(0);

        Label livesLabel = new Label("Number of lives", new Label.LabelStyle(font, Color.WHITE));
        livesSetting = new TextField("10", skin);



        final TextButton save_settings = new TextButton("Save", skin);
        save_settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                Save settings
            }
        });

        final TextButton back = new TextButton("Back", skin);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.changeScreen(LearningGame.MENU);
            }
        });

        table.add(speedLabel).width(200).height(75).padRight(10);
        table.add(gameSpeedSlider).width(200).height(75);
        table.row();
        table.add(livesLabel).width(200).height(75).padRight(10);
        table.add(livesSetting).width(200).height(75);
        table.row();
        table.add(save_settings).width(200).height(75).padTop(10);
        table.add(back).width(200).height(75).padTop(10);

        return table;
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //  clear the screen
//        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
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
